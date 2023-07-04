package top.hjh.rpc.common.client;

import top.hjh.rpc.entity.RpcRequest;
import top.hjh.rpc.serializer.CommonSerializer;

/**
 * @author 韩
 * @version 1.0
 */
public interface RpcClient {
    int DEFAULT_SERIALIZER = CommonSerializer.KRYO_SERIALIZER;

    Object sendRequest(RpcRequest rpcRequest);
}
