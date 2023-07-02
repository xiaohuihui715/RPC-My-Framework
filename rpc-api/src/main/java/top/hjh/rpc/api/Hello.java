package top.hjh.rpc.api;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author 韩
 * @version 1.0
 * 因为要传输对象，所以要序列化
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Hello implements Serializable {
    private Integer id;
    private String name;
}
