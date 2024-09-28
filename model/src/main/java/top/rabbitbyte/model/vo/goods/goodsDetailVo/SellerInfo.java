package top.rabbitbyte.model.vo.goods.goodsDetailVo;

import lombok.Data;
import top.rabbitbyte.model.entity.customer.CustomerInfo;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods.goodsDetailVo
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  17:50
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class SellerInfo {
    private String openId;
    private String nickName;
    private String avatarUrl;
    private String registerTime;
    private int soldCount;

    public static SellerInfo CustomerInfoConvert(CustomerInfo customerInfo) {
        SellerInfo sellerInfo = new SellerInfo();
        sellerInfo.setOpenId(customerInfo.getWxOpenId());
        sellerInfo.setNickName(customerInfo.getNickname());
        sellerInfo.setAvatarUrl(customerInfo.getAvatarUrl());
        sellerInfo.setRegisterTime(customerInfo.getCreateTime().toString());
        return sellerInfo;
    }
}