package top.hjh.test;

import top.hjh.rpc.annotation.Service;
import top.hjh.rpc.api.ByeService;

/**
 * @author 韩
 * @version 1.0
 */
@Service
public class ByeServiceImpl implements ByeService {

    @Override
    public String bye(String name) {
        return "bye, " + name;
    }
}
