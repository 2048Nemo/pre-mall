package top.rabbitbyte.comon.utils.result;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.comon.utils.result
 * @Author: nemo2048
 * @CreateTime: 2024-10-03  21:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class JWTUser {
    private String openId;
    private String nickName;
    private String avatarUrl;
}
