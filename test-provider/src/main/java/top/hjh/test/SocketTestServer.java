package top.hjh.test;

import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.serializer.KryoSerializer;
import top.hjh.rpc.socket.server.SocketServer;

/**
 * @author 韩
 * @version 1.0
 */
public class SocketTestServer {
    public static void main(String[] args) {
        HelloServiceImpl2 service = new HelloServiceImpl2();
        SocketServer socketServer = new SocketServer("127.0.0.1", 9998);
        socketServer.setSerializer(new KryoSerializer());
        socketServer.publishService(service, HelloService.class);
    }
}
