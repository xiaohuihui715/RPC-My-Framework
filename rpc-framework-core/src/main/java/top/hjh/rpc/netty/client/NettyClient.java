package top.hjh.rpc.netty.client;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.AttributeKey;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.codec.CommonDecoder;
import top.hjh.rpc.codec.CommonEncoder;
import top.hjh.rpc.common.client.RpcClient;
import top.hjh.rpc.entity.RpcRequest;
import top.hjh.rpc.entity.RpcResponse;
import top.hjh.rpc.enumeration.RpcError;
import top.hjh.rpc.exception.RpcException;
import top.hjh.rpc.factory.SingletonFactory;
import top.hjh.rpc.loadbalancer.LoadBalancer;
import top.hjh.rpc.loadbalancer.RandomLoadBalancer;
import top.hjh.rpc.registry.NacosServiceDiscovery;
import top.hjh.rpc.registry.NacosServiceRegistry;
import top.hjh.rpc.registry.ServiceDiscovery;
import top.hjh.rpc.registry.ServiceRegistry;
import top.hjh.rpc.serializer.*;
import top.hjh.rpc.util.RpcMessageChecker;

import java.net.InetSocketAddress;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author 韩
 * @version 1.0
 * NIO方式消费侧客户端类
 */
public class NettyClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(NettyClient.class);


    private static final Bootstrap bootstrap;
    private static final EventLoopGroup group;
    private final ServiceDiscovery serviceDiscovery;
    private final CommonSerializer serializer;
    private final UnprocessedRequests unprocessedRequests;


    public NettyClient() {
        this(DEFAULT_SERIALIZER, new RandomLoadBalancer());
    }

    public NettyClient(LoadBalancer loadBalancer) {
        this(DEFAULT_SERIALIZER, loadBalancer);
    }

    public NettyClient(Integer serializer) {
        this(serializer, new RandomLoadBalancer());
    }

    public NettyClient(Integer serializer, LoadBalancer loadBalancer) {
        this.serviceDiscovery = new NacosServiceDiscovery(loadBalancer);
        this.serializer = CommonSerializer.getByCode(serializer);
        this.unprocessedRequests = SingletonFactory.getInstance(UnprocessedRequests.class);
    }


    static {
        group = new NioEventLoopGroup();
        bootstrap = new Bootstrap();
        bootstrap.group(group)
                .channel(NioSocketChannel.class);
    }

    @Override
    public CompletableFuture<RpcResponse> sendRequest(RpcRequest rpcRequest) {

        if (serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }
        CompletableFuture<RpcResponse> resultFuture = new CompletableFuture<>();
        try {
            InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest.getInterfaceName());
            Channel channel = ChannelProvider.get(inetSocketAddress, serializer);
            if (!channel.isActive()) {
                group.shutdownGracefully();
                return null;
            }
            unprocessedRequests.put(rpcRequest.getRequestId(), resultFuture);
            channel.writeAndFlush(rpcRequest).addListener((ChannelFutureListener) future1 -> {
                if (future1.isSuccess()) {
                    logger.info(String.format("客户端发送消息: %s", rpcRequest.toString()));
                } else {
                    future1.channel().close();
                    resultFuture.completeExceptionally(future1.cause());
                    logger.error("发送消息时有错误发生: ", future1.cause());
                }
            });
        } catch (InterruptedException e) {
            unprocessedRequests.remove(rpcRequest.getRequestId());
            logger.error(e.getMessage(), e);
            Thread.currentThread().interrupt();
        }
        return resultFuture;
    }


}
