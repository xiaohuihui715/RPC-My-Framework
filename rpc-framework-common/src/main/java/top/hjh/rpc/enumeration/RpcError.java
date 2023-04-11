package top.hjh.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 韩
 * @version 1.0
 */
@AllArgsConstructor
@Getter
public enum RpcError {

    SERVICE_INVOCATION_FAILURE("服务调用出现失败"),
    SERVICE_CAN_NOT_BE_NULL("注册的服务不得为空");

    private final String message;

}