package top.rabbitbyte.webcustomer.service;


import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;

import java.util.List;

public interface WebCategoryService {
    CataLogPageVo getAllCatalog();


    CategoryPageVo getBroCateAndGoodsList(Integer cateid);

    CategoryPageVo getGoodsList(Integer cateid, Integer page, Integer size);

    List<CategoryVo> getCurrentCataLog(Integer cataLogId);
}
