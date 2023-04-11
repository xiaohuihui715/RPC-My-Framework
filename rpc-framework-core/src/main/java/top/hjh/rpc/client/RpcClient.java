package top.hjh.rpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.entity.RpcRequest;
import top.hjh.rpc.entity.RpcResponse;
import top.hjh.rpc.enumeration.ResponseCode;
import top.hjh.rpc.enumeration.RpcError;
import top.hjh.rpc.exception.RpcException;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

/**
 * @author 韩
 * @version 1.0
 */
public class RpcClient {
    private static final Logger logger = LoggerFactory.getLogger(RpcClient.class);

    public Object sendRequest(RpcRequest rpcRequest, String host, int port) {
        try (
                Socket socket = new Socket(host, port)
        ) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(rpcRequest);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.flush();
            RpcResponse rpcResponse = (RpcResponse) objectInputStream.readObject();
            if(rpcResponse == null) {
                logger.error("服务调用失败，service：{}", rpcRequest.getInterfaceName());
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            if(rpcResponse.getStatusCode() == null || rpcResponse.getStatusCode() != ResponseCode.SUCCESS.getCode()) {
                logger.error("调用服务失败, service: {}, response:{}", rpcRequest.getInterfaceName(), rpcResponse);
                throw new RpcException(RpcError.SERVICE_INVOCATION_FAILURE, " service:" + rpcRequest.getInterfaceName());
            }
            return rpcResponse.getData();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("调用时有错误发生：", e);
            throw new RpcException("服务调用失败: ", e);
        }
    }
}
