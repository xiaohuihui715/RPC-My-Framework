package top.hjh.rpc.common.client;

import top.hjh.rpc.entity.RpcRequest;

/**
 * @author 韩
 * @version 1.0
 */
public interface RpcClient {
    Object sendRequest(RpcRequest rpcRequest);
}
