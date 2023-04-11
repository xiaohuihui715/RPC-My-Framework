package top.hjh.rpc.exception;

import top.hjh.rpc.enumeration.RpcError;

/**
 * @author 韩
 * @version 1.0
 */
public class RpcException extends RuntimeException {

    public RpcException(RpcError error, String detail) {
        super(error.getMessage() + ": " + detail);
    }

    public RpcException(String message, Throwable cause) {
        super(message, cause);
    }

    public RpcException(RpcError error) {
        super(error.getMessage());
    }

}
