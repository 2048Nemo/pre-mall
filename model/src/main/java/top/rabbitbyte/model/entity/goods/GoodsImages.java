package top.rabbitbyte.model.entity.goods;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import top.rabbitbyte.model.entity.base.BaseEntity;

import java.math.BigDecimal;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.entity.goods
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  23:35
 * @Description: TODO
 * @Version: 1.0
 */

@Data
@Schema(description = "GoodsDetail")
@TableName("goods_images")
public class GoodsImages extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
     * goods id
     */
    @ApiModelProperty("goods_id")
    private Long goodsId;

    /**
     * image 地址
     */
    private String imgUrl;
    /**
     * 图片排序
     */
    private Integer sort;
}
