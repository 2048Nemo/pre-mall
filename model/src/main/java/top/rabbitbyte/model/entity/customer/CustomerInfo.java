package top.rabbitbyte.model.entity.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.entity.customer
 * @Author: nemo2048
 * @CreateTime: 2024-08-27  18:12
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@Schema(description = "CustomerInfo")
@TableName("customer_info")
public class CustomerInfo extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @Schema(description = "微信openId")
    private String wxOpenId;

    @Schema(description = "客户昵称")
    private String nickname;

    @Schema(description = "性别")
    private String gender;

    @Schema(description = "头像")
    private String avatarUrl;

    @Schema(description = "电话")
    private String phone;

    @Schema(description = "会员积分")
    private Integer points;

    @Schema(description = "会员等级")
    private Integer level;

    @Schema(description = "1有效，2禁用")
    private Integer status;
}
