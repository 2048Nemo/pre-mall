package top.rabbitbyte.model.entity.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.rabbitbyte.model.entity.base.BaseEntity;

import java.util.Date;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.entity.order
 * @Author: nemo2048
 * @CreateTime: 2024-08-22  22:54
 * @Description: TODO 占位用的order模块，仅用于测试
 * @Version: 1.0
 */
@Data
@Schema(description = "OrderInfo")
@TableName("Order_Info")
public class OrderInfo extends BaseEntity {
    private static final  long serialVersionUID = 1L;
    //生成一些order模块的字段
    @Schema(description = "订单状态")
    @TableField("order_status")
    private Integer orderStatus;
    @Schema(description = "订单金额")
    @TableField("order_money")
    private Integer orderMoney;
}
