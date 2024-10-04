package top.rabbitbyte.model.entity.customer;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.entity.coupon
 * @Author: nemo2048
 * @CreateTime: 2024-09-29  20:32
 * @Description: TODO
 * @Version: 1.0
 */

@Data
@Schema(description = "CouponCoupon")
@TableName("customer_collect")
public class CustomerCollect extends BaseEntity {
    private static final long serialVersionUID = 1L;

    @TableField("goods_id")
    private Long goodsId;
}