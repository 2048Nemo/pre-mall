package top.rabbitbyte.model.entity.goods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.math.BigDecimal;
import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
* 
* @TableName goods_sku
*/
@Data
@Schema(description = "GoodsSku")
@TableName("goods_sku")
public class GoodsSku extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * spu id
    */
    @ApiModelProperty("spu id")
    private Long spuId;
    /**
    * 价格
    */
    @ApiModelProperty("价格")
    private BigDecimal price;
    /**
    * 商家id(商家sku)
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("商家id(商家sku)")
    @Length(max= 50,message="编码长度不能超过50")
    private String venderId;
    /**
    * 库存数量
    */
    @ApiModelProperty("库存数量")
    private Integer stock;

}
