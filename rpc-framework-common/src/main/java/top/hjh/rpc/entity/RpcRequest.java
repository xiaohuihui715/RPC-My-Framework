package top.hjh.rpc.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author 韩
 * @version 1.0
 * 请求消息体
 */
@Data
@Builder
@AllArgsConstructor
public class RpcRequest implements Serializable {

    /**
     * 请求号
     */
    private String requestId;
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

    /**
     * 是否是心跳包
     */
    private Boolean heartBeat;

    public RpcRequest() {
    }
}
