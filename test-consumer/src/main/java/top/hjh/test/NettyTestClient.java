package top.hjh.test;

import top.hjh.rpc.api.ByeService;
import top.hjh.rpc.api.Hello;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.common.client.RpcClient;
import top.hjh.rpc.common.client.RpcClientProxy;
import top.hjh.rpc.netty.client.NettyClient;
import top.hjh.rpc.serializer.CommonSerializer;
import top.hjh.rpc.serializer.HessianSerializer;

import java.util.Random;

/**
 * @author 韩
 * @version 1.0
 */
public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient(CommonSerializer.PROTOBUF_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService service = rpcClientProxy.getProxy(HelloService.class);

        String res = service.send(new Hello(new Random(100).nextInt(), "hello 大利嘉"));
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }
}
