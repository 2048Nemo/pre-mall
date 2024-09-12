package top.rabbitbyte.model.vo.goods;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods
 * @Author: nemo2048
 * @CreateTime: 2024-09-11  21:25
 * @Description: TODO
 * @Version: 1.0
 */

@Data
@Schema(name = "CategoryVo", description = "商品分类")
public class CategoryVo {
    private Integer id;
    private String name;
    private Integer parentId;
    private String iconUrl;
    private Integer sort;
}