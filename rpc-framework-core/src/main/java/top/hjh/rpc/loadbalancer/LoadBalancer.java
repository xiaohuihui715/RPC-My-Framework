package top.hjh.rpc.loadbalancer;

import com.alibaba.nacos.api.naming.pojo.Instance;

import java.util.List;

/**
 * @author 韩
 * @version 1.0
 */
public interface LoadBalancer {

    Instance select(List<Instance> instances);

}
