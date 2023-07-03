package top.hjh.test;

import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.netty.server.NettyServer;
import top.hjh.rpc.serializer.HessianSerializer;

/**
 * @author 韩
 * @version 1.0
 */
public class NettyTestServer {
    public static void main(String[] args) {
        HelloServiceImpl service = new HelloServiceImpl();
        NettyServer server = new NettyServer("127.0.0.1", 9999);
        server.setSerializer(new HessianSerializer());
        server.publishService(service, HelloService.class);
    }
}
