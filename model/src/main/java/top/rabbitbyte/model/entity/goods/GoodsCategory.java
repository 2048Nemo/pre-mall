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
* @TableName goods_category
*/
@Data
@Schema(description = "GoodsCategory")
@TableName("goods_category")
public class GoodsCategory extends BaseEntity {
    private static final long serialVersionUID = 1L;

    /**
    * 类别名称
    */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("类别名称")
    @Length(max= 50,message="编码长度不能超过50")
    private String name;
    /**
    * 上级类别id
    */
    @ApiModelProperty("上级类别id")
    private Integer parentId;
    /**
    * 排序，数字越小越靠前
    */
    @ApiModelProperty("排序，数字越小越靠前")
    private Integer sort;

    @ApiModelProperty("图标")
    private String iconUrl;
    /**
    * 创建时间
    */
    @NotNull(message="[创建时间]不能为空")
    @ApiModelProperty("创建时间")
    private Date createTime;
    /**
    * 更新时间
    */
    @NotNull(message="[更新时间]不能为空")
    @ApiModelProperty("更新时间")
    private Date updateTime;
    /**
    * 删除标记（0:不可用 1:可用）
    */
    @NotNull(message="[删除标记（0:不可用 1:可用）]不能为空")
    @ApiModelProperty("删除标记（0:不可用 1:可用）")
    private Integer isDeleted;

}
