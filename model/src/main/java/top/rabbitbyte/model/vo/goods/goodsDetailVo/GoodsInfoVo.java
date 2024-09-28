package top.rabbitbyte.model.vo.goods.goodsDetailVo;

import lombok.Data;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods.goodsDetailVo
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  17:47
 * @Description: TODO
 * @Version: 1.0
 */
@Data
public class GoodsInfoVo {
    private Long id;
    private String name;
    private BigDecimal price;
    private BigDecimal marketPrice;
    private String desc;
    private Date lastEdit;
    private String region;
    private Integer isDelete;
    private Integer isSelling;
    private Integer ableExpress;
    private Integer ableMeet;
    private Integer ableSelfTake;
    private BigDecimal postage;
    private Integer wantCount;
    private Integer browseCount;
}