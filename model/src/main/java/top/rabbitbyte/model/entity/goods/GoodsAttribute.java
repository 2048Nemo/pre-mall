package top.rabbitbyte.model.entity.goods;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import javax.validation.constraints.NotNull;

import java.io.Serializable;

import java.util.Date;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.hibernate.validator.constraints.Length;
import top.rabbitbyte.model.entity.base.BaseEntity;

/**
* 
* @TableName goods_attribute
*/
@Data
@Schema(description = "GoodsAttribute")
@TableName("goods_attribute")
public class GoodsAttribute extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
    * 属性名称
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("属性名称")
    @Length(max= 50,message="编码长度不能超过50")
    private String name;
    /**
    * 可选值列表[用值列表隔开]
    */
    @Size(max= 255,message="编码长度不能超过255")
    @ApiModelProperty("可选值列表[用值列表隔开]")
    @Length(max= 255,message="编码长度不能超过255")
    private String options;
    /**
    * 属性类型（0-基本属性，1-销售属性）
    */
    @ApiModelProperty("属性类型（0-基本属性，1-销售属性）")
    private Integer attrType;
    /**
    * 排序，数字越小越好
    */
    @ApiModelProperty("排序，数字越小越好")
    private Integer sort;
    /**
    * 所属类别
    */
    @ApiModelProperty("所属类别")
    private Long categoryId;

}
