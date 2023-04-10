package top.hjh.test;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.api.Hello;
import top.hjh.rpc.api.HelloService;

/**
 * @author 韩
 * @version 1.0
 */
public class HelloServiceImpl implements HelloService {
    private static final Logger logger = LoggerFactory.getLogger(HelloServiceImpl.class);

    @Override
    public String send(Hello hello) {
        logger.info("接收到：{}", hello.getName());
        return "这是返回值，id=" + hello.getId();
    }
}