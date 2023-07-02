package top.hjh.rpc.exception;

/**
 * @author 韩
 * @version 1.0
 * 序列化异常
 */
public class SerializeException extends RuntimeException {
    public SerializeException(String msg) {
        super(msg);
    }
}
