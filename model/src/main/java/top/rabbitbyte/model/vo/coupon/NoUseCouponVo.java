package top.rabbitbyte.model.vo.coupon;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.Date;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.coupon
 * @Author: nemo2048
 * @CreateTime: 2024-08-23  17:53
 * @Description: 活动期间领取并没有使用的优惠券
 * @Version: 1.0
 */
@Data
@Schema(description = "NoReceiveCouponVo")
public class NoUseCouponVo {
    private Long id;

    @Schema(description = "优惠卷类型 1 现金券 2 折扣")
    private Integer couponType;

    @Schema(description = "优惠卷名字")
    private String name;

    @Schema(description = "金额")
    private String amount;

    @Schema(description = "折扣：取值[1 到 10]")
    private String discount;

    @Schema(description = "使用门槛 0->没门槛")
    private String conditionAmount;

    @Schema(description = "发行数量")
    private Integer publishCount;

    @Schema(description = "每人限领张数")
    private Integer perLimit;

    @Schema(description = "过期时间")
    private Date expireTime;

    @Schema(description = "兑换需要的积分点数")
    @TableField("need_points")
    private Integer needPoints;

    @Schema(description = "兑换需要的会员等级")
    @TableField("need_level")
    private Integer needLevel;

    @Schema(description = "优惠券描述")
    private String description;
}