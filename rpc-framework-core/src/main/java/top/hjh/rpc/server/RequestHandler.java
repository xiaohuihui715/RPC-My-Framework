package top.hjh.rpc.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.entity.RpcRequest;
import top.hjh.rpc.entity.RpcResponse;
import top.hjh.rpc.enumeration.ResponseCode;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.Socket;

/**
 * @author 韩
 * @version 1.0
 * 工作线程
 */
public class RequestHandler implements Runnable {

    private static final Logger logger = LoggerFactory.getLogger(RequestHandler.class);

    private Socket socket;
    private Object service;

    public RequestHandler(Socket socket, Object service) {
        this.socket = socket;
        this.service = service;
    }

    /**
     * 工作线程获取请求信息后使用反射调用提供者的方法，将返回数据封装重新发给消费者
     */
    @Override
    public void run() {
        try (
                ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream())
        ) {
            RpcRequest rpcRequest = (RpcRequest) objectInputStream.readObject();
            Object returnObject = invokeMethod(rpcRequest);
            objectOutputStream.writeObject(RpcResponse.success(returnObject));
            objectOutputStream.flush();
        } catch (IOException | ClassNotFoundException | IllegalAccessException | InvocationTargetException e) {
            logger.error("调用或发送时有错误发生：", e);
        }


    }

    private Object invokeMethod(RpcRequest rpcRequest) throws IllegalAccessException, InvocationTargetException, ClassNotFoundException {
        Class<?> clazz = Class.forName(rpcRequest.getInterfaceName());
        if (!clazz.isAssignableFrom(service.getClass())) {
            return RpcResponse.fail(ResponseCode.CLASS_NOT_FOUND);
        }
        Method method;
        try {
            method = service.getClass().getMethod(rpcRequest.getMethodName(), rpcRequest.getParamTypes());
        } catch (NoSuchMethodException e) {
            return RpcResponse.fail(ResponseCode.METHOD_NOT_FOUND);
        }
        return method.invoke(service, rpcRequest.getParameters());
    }
}
