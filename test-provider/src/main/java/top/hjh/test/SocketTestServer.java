package top.hjh.test;

import top.hjh.rpc.annotation.ServiceScan;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.common.server.RpcServer;
import top.hjh.rpc.serializer.CommonSerializer;
import top.hjh.rpc.serializer.KryoSerializer;
import top.hjh.rpc.socket.server.SocketServer;

/**
 * @author 韩
 * @version 1.0
 */
@ServiceScan
public class SocketTestServer {
    public static void main(String[] args) {
        RpcServer server = new SocketServer("127.0.0.1", 9998, CommonSerializer.HESSIAN_SERIALIZER);
        server.start();
    }
}
