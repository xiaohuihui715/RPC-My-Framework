package top.hjh.rpc.common.server;

import top.hjh.rpc.serializer.CommonSerializer;

/**
 * @author 韩
 * @version 1.0
 */
public interface RpcServer {
    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    void start();

    <T> void publishService(T service, Class<T> serviceClass);
}
