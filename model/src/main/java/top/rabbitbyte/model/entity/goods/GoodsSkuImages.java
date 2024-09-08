package top.rabbitbyte.model.entity.goods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
* 
* @TableName goods_sku_images
*/
@Data
@Schema(description = "GoodsSkuImages")
@TableName("goods_sku_images")
public class GoodsSkuImages extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
    * sku id
    */
    @ApiModelProperty("sku id")
    private Long skuId;
    /**
    * sku展示的主图
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("sku展示的主图")
    @Length(max= 255,message="编码长度不能超过255")
    private String imageMain;
    /**
    * 排序组图的顺序
    */
    @NotNull(message="[排序组图的顺序]不能为空")
    @ApiModelProperty("排序组图的顺序")
    private Integer sort;


}
