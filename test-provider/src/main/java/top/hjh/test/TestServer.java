package top.hjh.test;

import top.hjh.rpc.server.RpcServer;

/**
 * @author 韩
 * @version 1.0
 */
public class TestServer {
    public static void main(String[] args) {
        HelloServiceImpl service = new HelloServiceImpl();
        RpcServer rpcServer = new RpcServer();
        rpcServer.register(service,9000);
    }
}
