package top.hjh.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 韩
 * @version 1.0
 * 字节流中标识数据类型：0-请求包；1-响应包
 */
@AllArgsConstructor
@Getter
public enum PackageType {
    REQUEST_PACK(0),
    RESPONSE_PACK(1);

    private final int code;
}
