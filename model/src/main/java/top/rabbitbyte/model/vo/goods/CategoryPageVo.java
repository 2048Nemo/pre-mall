package top.rabbitbyte.model.vo.goods;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import top.rabbitbyte.model.entity.goods.GoodsCategory;
import top.rabbitbyte.model.entity.goods.GoodsSpu;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.model.vo.goods
 * @Author: nemo2048
 * @CreateTime: 2024-09-13  14:33
 * @Description: TODO
 * @Version: 1.0
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class CategoryPageVo {

    /*同一个父分类下的兄弟分类*/
    private List<GoodsCategory> brotherCategory;

    /*当前分类的商品列表*/
    private List<GoodsSkuVo> goodsList;
}