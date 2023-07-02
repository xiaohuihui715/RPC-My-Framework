package top.hjh.test;

import top.hjh.rpc.netty.server.NettyServer;
import top.hjh.rpc.registry.DefaultServiceRegistry;

/**
 * @author 韩
 * @version 1.0
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloServiceImpl service = new HelloServiceImpl();
        DefaultServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(service);
        NettyServer server = new NettyServer();
        server.start(9999);
    }
}
