package top.hjh.rpc.enumeration;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author 韩
 * @version 1.0
 * 字节流中标识序列化和反序列化器
 */
@AllArgsConstructor
@Getter
public enum SerializerCode {

    KRYO(0),
    JSON(1),
    HESSIAN(2),
    PROTOBUF(3);

    private final int code;
}
