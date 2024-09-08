package top.rabbitbyte.model.entity.customer;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.entity.customer
 * @Author: nemo2048
 * @CreateTime: 2024-08-27  18:14
 * @Description: TODO
 * @Version: 1.0
 */

@Data
@Schema(description = "CustomerLoginLog")
@TableName("customer_login_log")
public class CustomerLoginLog extends BaseEntity {

    private static final long serialVersionUID = 1L;

    @Schema(description = "客户id")
    private Long customerId;

    @Schema(description = "登录IP地址")
    private String ipaddr;

    @Schema(description = "登录状态（0成功 1失败）")
    private Boolean status;

    @Schema(description = "提示信息")
    private String msg;

}