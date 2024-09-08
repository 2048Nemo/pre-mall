package top.rabbitbyte.model.entity.order;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.rabbitbyte.model.entity.base.BaseEntity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
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
    @Schema(description = "会员id")
    @TableField("member_id")
    private Long memberId;                // 会员id

    @Schema(description = "优惠券id")
    @TableField("coupon_id")
    private Long couponId;                // 优惠券id

    @Schema(description = "订单编号")
    @TableField("order_sn")
    private String orderSn;               // 订单编号

    @Schema(description = "用户帐号")
    @TableField("member_username")
    private String memberUsername;        // 用户帐号

    @Schema(description = "订单总金额")
    @TableField("total_amount")
    private BigDecimal totalAmount;       // 订单总金额

    @Schema(description = "应付金额（实际支付金额）")
    @TableField("pay_amount")
    private BigDecimal payAmount;         // 应付金额（实际支付金额）

    @Schema(description = "运费金额")
    @TableField("freight_amount")
    private BigDecimal freightAmount;     // 运费金额

    @Schema(description = "积分抵扣金额")
    @TableField("integration_amount")
    private BigDecimal integrationAmount; // 积分抵扣金额

    @Schema(description = "优惠券抵扣金额")
    @TableField("coupon_amount")
    private BigDecimal couponAmount;      // 优惠券抵扣金额

    @Schema(description = "支付方式：0->未支付；1->支付宝；2->微信")
    @TableField("pay_type")
    private Integer payType;              // 支付方式：0->未支付；1->支付宝；2->微信

    @Schema(description = "订单来源：0->PC订单；1->app订单")
    @TableField("source_type")
    private Integer sourceType;           // 订单来源：0->PC订单；1->app订单

    @Schema(description = "订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单")
    @TableField("status")
    private Integer status;               // 订单状态：0->待付款；1->待发货；2->已发货；3->已完成；4->已关闭；5->无效订单

    @Schema(description = "订单类型：0->正常订单；1->秒杀订单")
    @TableField("order_type")
    private Integer orderType;            // 订单类型：0->正常订单；1->秒杀订单

    @Schema(description = "自动确认时间（天）")
    @TableField("auto_confirm_day")
    private Integer autoConfirmDay;       // 自动确认时间（天）

    @Schema(description = "可以获得的积分")
    @TableField("integration")
    private Integer integration;          // 可以获得的积分

    @Schema(description = "收货人姓名")
    @TableField("receiver_name")
    private String receiverName;          // 收货人姓名

    @Schema(description = "收货人电话")
    @TableField("receiver_phone")
    private String receiverPhone;         // 收货人电话

    @Schema(description = "收货人邮编")
    @TableField("receiver_post_code")
    private String receiverPostCode;      // 收货人邮编

    @Schema(description = "省份/直辖市")
    @TableField("receiver_province")
    private String receiverProvince;      // 省份/直辖市

    @Schema(description = "城市")
    @TableField("receiver_city")
    private String receiverCity;          // 城市

    @Schema(description = "区")
    @TableField("receiver_region")
    private String receiverRegion;        // 区

    @Schema(description = "详细地址")
    @TableField("receiver_detail_address")
    private String receiverDetailAddress; // 详细地址

    @Schema(description = "订单备注")
    @TableField("note")
    private String note;                  // 订单备注

    @Schema(description = "确认收货状态：0->未确认；1->已确认")
    @TableField("confirm_status")
    private Integer confirmStatus;        // 确认收货状态：0->未确认；1->已确认


    @Schema(description = "下单时使用的积分")
    @TableField("use_integration")
    private Integer useIntegration;       // 下单时使用的积分

    @Schema(description = "支付时间")
    @TableField("payment_time")
    private LocalDateTime paymentTime;    // 支付时间

    @Schema(description = "发货时间")
    @TableField("delivery_time")
    private LocalDateTime deliveryTime;   // 发货时间

    @Schema(description = "确认收货时间")
    @TableField("receive_time")
    private LocalDateTime receiveTime;    // 确认收货时间

    @Schema(description = "评价时间")
    @TableField("comment_time")
    private LocalDateTime commentTime;    // 评价时间
}