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
* @TableName goods_spu
*/
@Data
@Schema(description = "GoodsSpu")
@TableName("goods_spu")
public class GoodsSpu extends BaseEntity {

    private static final long serialVersionUID = 1L;

    /**
    * 商品名称
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("商品名称")
    @Length(max= 50,message="编码长度不能超过50")
    private String name;

    /**
    * 商品类别
    */
    @ApiModelProperty("商品类别")
    private Long categoryId;

}