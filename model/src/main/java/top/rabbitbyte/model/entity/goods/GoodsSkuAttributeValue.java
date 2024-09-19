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

/**
* 
* @TableName goods_sku_attribute_value
*/
@Data
@Schema(description = "GoodsSkuAttributeValue")
@TableName("goods_sku_attribute_value")
public class GoodsSkuAttributeValue implements Serializable {

    /**
    * sku id
    */
    @ApiModelProperty("sku id")
    private Long skuId;
    /**
    * 属性id
    */
    @ApiModelProperty("属性id")
    private Long attrId;
    /**
    * 属性值
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("属性值")
    @Length(max= 255,message="编码长度不能超过255")
    private String attrValue;

}
