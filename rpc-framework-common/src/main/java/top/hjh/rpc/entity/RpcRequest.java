package top.hjh.rpc.entity;

import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 韩
 * @version 1.0
 */
@Data
@Builder
public class RpcRequest implements Serializable {
    /**
     * 封装调用的接口名
     */
    private String interfaceName;
    /**
     * 待调用方法名称
     */
    private String methodName;
    /**
     * 调用方法的参数
     */
    private Object[] parameters;
    /**
     * 调用方法的参数类型
     */
    private Class<?>[] paramTypes;
}
