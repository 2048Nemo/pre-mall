package top.rabbitbyte.model.vo.goods.goodsDetailVo;

import lombok.Data;
import top.rabbitbyte.model.entity.goods.GoodsInfo;

import java.math.BigDecimal;

@Data
public class RelatedGoods {
    private Long id;
    private String name;
    private String primaryPicUrl;
    private BigDecimal price;

    // Getters and Setters
    // ...
    public static RelatedGoods fromGoodsInfo(GoodsInfo item) {
        RelatedGoods item2 = new RelatedGoods();
        item2.setId(item.getId());
        item2.setName(item.getName());
        item2.setPrice(item.getPrice());
        item2.setPrimaryPicUrl(item.getImage());
        return item2;
    }
}