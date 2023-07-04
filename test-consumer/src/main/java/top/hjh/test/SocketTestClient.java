package top.hjh.test;

import top.hjh.rpc.api.ByeService;
import top.hjh.rpc.api.Hello;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.common.client.RpcClientProxy;
import top.hjh.rpc.serializer.CommonSerializer;
import top.hjh.rpc.serializer.KryoSerializer;
import top.hjh.rpc.socket.client.SocketClient;

/**
 * @author 韩
 * @version 1.0
 */
public class SocketTestClient {
    public static void main(String[] args) {
        SocketClient client = new SocketClient(CommonSerializer.KRYO_SERIALIZER);
        RpcClientProxy rpcClientProxy = new RpcClientProxy(client);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        Hello hello = new Hello(14441, "四年三");
        String res = helloService.send(hello);
        System.out.println(res);
        ByeService byeService = rpcClientProxy.getProxy(ByeService.class);
        System.out.println(byeService.bye("Netty"));
    }
}
