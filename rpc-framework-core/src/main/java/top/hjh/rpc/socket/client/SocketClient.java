package top.hjh.rpc.socket.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.common.client.RpcClient;
import top.hjh.rpc.entity.RpcRequest;
import top.hjh.rpc.entity.RpcResponse;
import top.hjh.rpc.enumeration.ResponseCode;
import top.hjh.rpc.enumeration.RpcError;
import top.hjh.rpc.exception.RpcException;
import top.hjh.rpc.registry.NacosServiceDiscovery;
import top.hjh.rpc.registry.NacosServiceRegistry;
import top.hjh.rpc.registry.ServiceDiscovery;
import top.hjh.rpc.registry.ServiceRegistry;
import top.hjh.rpc.serializer.CommonSerializer;
import top.hjh.rpc.util.ObjectReader;
import top.hjh.rpc.util.ObjectWriter;
import top.hjh.rpc.util.RpcMessageChecker;

import java.io.*;
import java.net.InetSocketAddress;
import java.net.Socket;

/**
 * @author 韩
 * @version 1.0
 * 原生socket远程调用的客户端
 */
public class SocketClient implements RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClient.class);

    private final CommonSerializer serializer;
    private final ServiceDiscovery serviceDiscovery;

    public SocketClient() {
        this(DEFAULT_SERIALIZER);
    }

    public SocketClient(Integer serializer) {
        this.serviceDiscovery = new NacosServiceDiscovery();
        this.serializer = CommonSerializer.getByCode(serializer);
    }

    @Override
    public Object sendRequest(RpcRequest rpcRequest) {
        if (serializer == null) {
            logger.error("未设置序列化器");
            throw new RpcException(RpcError.SERIALIZER_NOT_FOUND);
        }

        InetSocketAddress inetSocketAddress = serviceDiscovery.lookupService(rpcRequest.getInterfaceName());
        try (
                Socket socket = new Socket()
        ) {
            socket.connect(inetSocketAddress);
            OutputStream outputStream = socket.getOutputStream();
            InputStream inputStream = socket.getInputStream();
            ObjectWriter.writeObject(outputStream, rpcRequest, serializer);
            Object obj = ObjectReader.readObject(inputStream);
            RpcResponse rpcResponse = (RpcResponse) obj;
            if (rpcResponse == null) {
                logger.error("服务调用失败，service：{}", rpcRequest.getInterfaceName());
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            if (rpcResponse.getStatusCode() == null || rpcResponse.getStatusCode() != ResponseCode.SUCCESS.getCode()) {
                logger.error("调用服务失败, service: {}, response:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            RpcMessageChecker.check(rpcRequest, rpcResponse);
            return rpcResponse.getData();
        } catch (IOException e) {
            logger.error("调用时有错误发生：", e);
            throw new RpcException("服务调用失败: ", e);
        }
    }

}
