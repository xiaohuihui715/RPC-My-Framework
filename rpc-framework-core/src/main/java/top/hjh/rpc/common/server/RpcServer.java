package top.hjh.rpc.common.server;

import top.hjh.rpc.serializer.CommonSerializer;

/**
 * @author 韩
 * @version 1.0
 */
public interface RpcServer {
    void start(int port);

    void setSerializer(CommonSerializer serializer);
}
