package top.hjh.test;

import top.hjh.rpc.api.Hello;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.common.client.RpcClientProxy;
import top.hjh.rpc.serializer.KryoSerializer;
import top.hjh.rpc.socket.client.SocketClient;

/**
 * @author 韩
 * @version 1.0
 */
public class SocketTestClient {
    public static void main(String[] args) {
        SocketClient socketClient = new SocketClient();
        socketClient.setSerializer(new KryoSerializer());
        RpcClientProxy rpcClientProxy = new RpcClientProxy(socketClient);
        HelloService helloService = rpcClientProxy.getProxy(HelloService.class);
        String s = helloService.send(new Hello(222, "jack"));
        System.out.println(s);
    }
}
