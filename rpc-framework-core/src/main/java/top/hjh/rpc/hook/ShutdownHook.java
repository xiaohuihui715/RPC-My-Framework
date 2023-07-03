package top.hjh.rpc.hook;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import top.hjh.rpc.factory.ThreadPoolFactory;
import top.hjh.rpc.util.NacosUtil;

import java.util.concurrent.ExecutorService;

/**
 * @author 韩
 * @version 1.0
 */
public class ShutdownHook {

    private static final Logger logger = LoggerFactory.getLogger(ShutdownHook.class);

    private final ExecutorService threadPool = ThreadPoolFactory.createDefaultThreadPool("shutdown-hook");
    private static final ShutdownHook shutdownHook = new ShutdownHook();

    public static ShutdownHook getShutdownHook() {
        return shutdownHook;
    }

    public void addClearAllHook() {
        logger.info("关闭后将自动注销所有服务");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> {
            NacosUtil.clearRegistry();
            threadPool.shutdown();
        }));
    }

}
