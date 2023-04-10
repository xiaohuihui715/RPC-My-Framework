package top.hjh.test;

import top.hjh.rpc.api.Hello;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.client.RpcClientProxy;

/**
 * @author 韩
 * @version 1.0
 */
public class TestClient {
    public static void main(String[] args) {
        RpcClientProxy proxy = new RpcClientProxy("127.0.0.1", 9000);
        HelloService service = proxy.getProxy(HelloService.class);
        String res = service.send(new Hello(1, "hjh"));
        System.out.println(res);
    }
}
