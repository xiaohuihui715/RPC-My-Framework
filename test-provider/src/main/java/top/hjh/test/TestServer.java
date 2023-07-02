package top.hjh.test;

import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.registry.DefaultServiceRegistry;
import top.hjh.rpc.registry.ServiceRegistry;
import top.hjh.rpc.server.RpcServer;

/**
 * @author 韩
 * @version 1.0
 */
public class TestServer {
    public static void main(String[] args) {
        HelloService helloService = new HelloServiceImpl();
        ServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(helloService);
        RpcServer rpcServer = new RpcServer(serviceRegistry);
        rpcServer.start(9000);
    }
}
