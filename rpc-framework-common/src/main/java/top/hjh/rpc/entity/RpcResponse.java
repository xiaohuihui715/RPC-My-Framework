package top.hjh.rpc.entity;

import lombok.Data;
import top.hjh.rpc.enumeration.ResponseCode;

import java.io.Serializable;

/**
 * @author 韩
 * @version 1.0
 * 响应消息体
 */
@Data
public class RpcResponse<T> implements Serializable {

    /**
     * 响应对应的请求号
     */
    private String requestId;
    /**
     * 响应状态码
     */
    private Integer statusCode;
    /**
     * 响应状态补充信息
     */
    private String message;
    /**
     * 响应数据
     */
    private T data;

    public RpcResponse() {
    }

    /**
     * 调用成功生成响应对象
     * @param data
     * @param requestId
     * @param <T>
     * @return
     */
    public static <T> RpcResponse<T> success(T data, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setStatusCode(ResponseCode.SUCCESS.getCode());
        response.setData(data);
        return response;
    }

    /**
     * 调用失败生成响应对象
     * @param code
     * @param requestId
     * @param <T>
     * @return
     */
    public static <T> RpcResponse<T> fail(ResponseCode code, String requestId) {
        RpcResponse<T> response = new RpcResponse<>();
        response.setRequestId(requestId);
        response.setStatusCode(code.getCode());
        response.setMessage(code.getMessage());
        return response;
    }
}
