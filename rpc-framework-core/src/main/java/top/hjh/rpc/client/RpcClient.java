package top.hjh.rpc.client;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.entity.RpcRequest;

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

    public Object sendRequest(RpcRequest request, String host, int port) {
        try (
                Socket socket = new Socket(host, port)
        ) {
            ObjectOutputStream objectOutputStream = new ObjectOutputStream(socket.getOutputStream());
            objectOutputStream.writeObject(request);
            ObjectInputStream objectInputStream = new ObjectInputStream(socket.getInputStream());
            objectOutputStream.flush();
            return objectInputStream.readObject();

        } catch (IOException | ClassNotFoundException e) {
            logger.error("io异常", e.getMessage());
            return null;
        }
    }
}
