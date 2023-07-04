package top.hjh.rpc.provider;

/**
 * @author 韩
 * @version 1.0
 * 保存和提供服务实例对象
 */
public interface ServiceProvider {


    /**
     * 注册服务到注册表中
     * @param service 注册的服务
     * @param serviceName  服务名
     * @param <T>
     */
    <T> void addServiceProvider(T service, String serviceName);

    /**
     * 获取服务注册表中的服务
     * @param serviceName：通过服务名获取服务
     * @return ：服务实体
     */
    Object getServiceProvider(String serviceName);

}