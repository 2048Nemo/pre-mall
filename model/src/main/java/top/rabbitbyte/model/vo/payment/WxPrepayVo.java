package top.rabbitbyte.model.vo.payment;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.payment
 * @Author: nemo2048
 * @CreateTime: 2024-08-26  15:07
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class WxPrepayVo {

    @Schema(description = "公众号ID")
    private String appId;

    @Schema(description = "时间戳，自1970年以来的秒数")
    private String timeStamp;

    @Schema(description = "随机串")
    private String nonceStr;

    @Schema(description = "预支付交易会话标识")
    private String packageVal;

    @Schema(description = "微信签名方式")
    private String signType;

    @Schema(description = "微信签名")
    private String paySign;
}
