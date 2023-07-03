package top.hjh.rpc.common.server;

import top.hjh.rpc.serializer.CommonSerializer;

/**
 * @author 韩
 * @version 1.0
 */
public interface RpcServer {
    void start();

    void setSerializer(CommonSerializer serializer);

    <T> void publishService(Object service, Class<T> serviceClass);
}
