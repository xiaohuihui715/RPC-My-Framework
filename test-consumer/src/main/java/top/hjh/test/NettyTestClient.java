package top.hjh.test;

import top.hjh.rpc.api.Hello;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.common.client.RpcClient;
import top.hjh.rpc.common.client.RpcClientProxy;
import top.hjh.rpc.netty.client.NettyClient;
import top.hjh.rpc.serializer.HessianSerializer;

/**
 * @author 韩
 * @version 1.0
 */
public class NettyTestClient {
    public static void main(String[] args) {
        RpcClient client = new NettyClient("127.0.0.1", 9999);
        client.setSerializer(new HessianSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService service = rpcClientProxy.getProxy(HelloService.class);
        String res = service.send(new Hello(1, "hjh"));
        System.out.println(res);
    }
}
