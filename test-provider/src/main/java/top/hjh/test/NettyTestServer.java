package top.hjh.test;

import top.hjh.rpc.annotation.ServiceScan;
import top.hjh.rpc.api.HelloService;
import top.hjh.rpc.common.server.RpcServer;
import top.hjh.rpc.netty.server.NettyServer;
import top.hjh.rpc.serializer.CommonSerializer;

/**
 * @author 韩
 * @version 1.0
 */
@ServiceScan
public class NettyTestServer {
    public static void main(String[] args) {
        RpcServer server = new NettyServer("127.0.0.1", 9999, CommonSerializer.PROTOBUF_SERIALIZER);
        server.start();
    }
}
