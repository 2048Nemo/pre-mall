package top.rabbitbyte.webcustomer.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.goods.client.CategoryInfoFeignClient;
import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;
import top.rabbitbyte.webcustomer.service.WebCategoryService;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  18:05
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class WebCategoryServiceImp implements WebCategoryService {

    @Autowired
    private CategoryInfoFeignClient categoryInfoFeignClient;
    @Override
    public CataLogPageVo getAllCatalog() {
        return categoryInfoFeignClient.getAllCataLog().getData();
    }

    @Override
    public List<CategoryVo> getCurrentCataLog(Integer cataLogId) {
        return categoryInfoFeignClient.getCurrentCataLog(cataLogId).getData();
    }

    @Override
    public CategoryPageVo getBroCateAndGoodsList(Integer cateid) {
        return categoryInfoFeignClient.getBrotherCategoryAndCurrentGoodsList(cateid).getData();
    }

    @Override
    public CategoryPageVo getGoodsList(Integer cateid, Integer page, Integer size) {
        return categoryInfoFeignClient.getGoodsList(cateid,page,size).getData();
    }
}
