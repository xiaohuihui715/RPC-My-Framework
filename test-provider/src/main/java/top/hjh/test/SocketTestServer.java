package top.hjh.test;

import top.hjh.rpc.registry.DefaultServiceRegistry;
import top.hjh.rpc.socket.server.SocketServer;

/**
 * @author 韩
 * @version 1.0
 */
public class SocketTestServer {
    public static void main(String[] args) {
        HelloServiceImpl service = new HelloServiceImpl();
        DefaultServiceRegistry serviceRegistry = new DefaultServiceRegistry();
        serviceRegistry.register(service);
        SocketServer socketServer = new SocketServer(serviceRegistry);
        socketServer.start(9001);
    }
}
