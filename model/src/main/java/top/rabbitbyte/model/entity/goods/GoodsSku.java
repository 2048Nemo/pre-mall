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
     * 描述
     */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("描述")
    @Length(max= 255,message="编码长度不能超过255")
    private String description;


    /**
    * 商家id(商家sku)
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("商家id(商家sku)")
    @Length(max= 50,message="编码长度不能超过50")
    private String venderId;

    /**
     * 标题
     */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("标题")
    @Length(max= 255,message="编码长度不能超过255")
    private String title;
    /**
    * 库存数量
    */
    @ApiModelProperty("库存数量")
    private Integer stock;

    /**
     * 照片
     */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("照片")
    @Length(max= 255,message="编码长度不能超过255")
    private String image;

    /**
     * 排序
     */
    @ApiModelProperty("排序")
    private Integer sort;
}
