package top.rabbitbyte.model.vo.customer;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.customer
 * @Author: nemo2048
 * @CreateTime: 2024-08-26  14:36
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class CustomerLoginVo {

    @Schema(description = "微信openId")
    private String wxOpenId;

    @Schema(description = "客户昵称")
    private String nickname;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "头像")
    private String avatarUrl;

    @Schema(description = "是否绑定手机号码")
    private Boolean isBindPhone;
}