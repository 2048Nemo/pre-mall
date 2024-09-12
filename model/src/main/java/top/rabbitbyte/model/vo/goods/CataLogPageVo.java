package top.rabbitbyte.model.vo.goods;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods
 * @Author: nemo2048
 * @CreateTime: 2024-09-11  21:08
 * @Description: TODO
 * @Version: 1.0
 */

@Data
@Schema(description = "商品分类列表")
public class CataLogPageVo {
    /*其他所有和这个同级的分类*/
    private List<CategoryVo> allCategory;

    /*这个分类的所有子分类*/
    private List<CategoryVo> subCategory;
}
