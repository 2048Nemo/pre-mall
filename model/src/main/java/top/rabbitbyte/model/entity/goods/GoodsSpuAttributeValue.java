package top.rabbitbyte.model.entity.goods;

import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
* 
* @TableName goods_spu_attribute_value
*/
@Data
@Schema(description = "GoodsSpuAttributeValue")
@TableName("goods_spu_attribute_value")
public class GoodsSpuAttributeValue extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * spu id
    */
    @ApiModelProperty("spu id")
    private Long spuId;
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
