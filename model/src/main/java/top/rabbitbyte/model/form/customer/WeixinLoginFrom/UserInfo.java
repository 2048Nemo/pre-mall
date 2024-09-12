package top.rabbitbyte.model.form.customer.WeixinLoginFrom;

import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.form.customer.WeixinLoginFrom
 * @Author: nemo2048
 * @CreateTime: 2024-09-10  00:46
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class UserInfo {
    private String nickName;
    private int gender;
    private String language;
    private String city;
    private String province;
    private String country;
    private String avatarUrl;
}