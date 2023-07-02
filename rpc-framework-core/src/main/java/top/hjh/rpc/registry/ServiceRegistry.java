package top.hjh.rpc.registry;

/**
 * @author 韩
 * @version 1.0
 * 服务注册表接口
 */
public interface ServiceRegistry {

    /**
     * 注册服务到注册表中
     * @param service ：注册的实体
     * @param <T>：注册实体类型
     */
    <T> void register(T service);

    /**
     * 获取服务注册表中的服务
     * @param serviceName：通过服务名获取服务
     * @return ：服务实体
     */
    Object getService(String serviceName);
}
