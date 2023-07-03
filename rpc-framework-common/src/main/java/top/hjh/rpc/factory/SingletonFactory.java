package top.hjh.rpc.factory;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 韩
 * @version 1.0
 * 单例工厂
 */
public class SingletonFactory {

    private static Map<Class, Object> objectMap = new HashMap<>();

    private SingletonFactory() {
    }

    public static <T> T getInstance(Class<T> clazz) {
        Object instance = objectMap.get(clazz);
        synchronized (clazz) {
            if (instance == null) {
                try {
                    instance = clazz.newInstance();
                    objectMap.put(clazz, instance);
                } catch (IllegalAccessException | InstantiationException e) {
                    throw new RuntimeException(e.getMessage(), e);
                }
            }
        }
        return clazz.cast(instance);
    }

}
