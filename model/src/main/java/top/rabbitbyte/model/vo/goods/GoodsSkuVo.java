package top.rabbitbyte.model.vo.goods;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Size;
import java.math.BigDecimal;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods
 * @Author: nemo2048
 * @CreateTime: 2024-09-18  22:51
 * @Description: TODO
 * @Version: 1.0
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GoodsSkuVo{
    @ApiModelProperty("商品id")
    private Long id;
    /**
     * 商品名称
     */
    @Size(max= 50,message="编码长度不能超过50")
    @ApiModelProperty("商品标题")
    @Length(max= 50,message="编码长度不能超过50")
    private String title;

    @ApiModelProperty("商品价格")
    private BigDecimal price;
    @ApiModelProperty("商品图片")
    private String primaryPicUrl;
}
