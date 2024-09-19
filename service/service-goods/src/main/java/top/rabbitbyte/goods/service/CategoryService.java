package top.rabbitbyte.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import top.rabbitbyte.model.entity.goods.GoodsCategory;
import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;
import top.rabbitbyte.model.vo.goods.GoodsSkuVo;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.goods.service
 * @Author: nemo2048
 * @CreateTime: 2024-09-11  22:01
 * @Description: TODO
 * @Version: 1.0
 */

public interface CategoryService extends IService<GoodsCategory> {
    CataLogPageVo getAllCatalog();

    List<CategoryVo> getCatgoryListById(Integer cataLogId);

    CategoryPageVo getBroCateAndGoodsList(Integer cateid);

    CategoryPageVo  getGoodsList(Integer cateid, Integer page, Integer size);
}
