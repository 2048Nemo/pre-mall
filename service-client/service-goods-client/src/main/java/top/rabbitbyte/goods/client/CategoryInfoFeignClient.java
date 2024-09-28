package top.rabbitbyte.goods.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;

import java.util.List;

@FeignClient(value ="service-goods")
public interface CategoryInfoFeignClient {
    /**
     * 分类目录，获取全部分类数据接口
     * @return
     */
    @GetMapping("/goods/category/catalog/index")
    public Result<CataLogPageVo> getAllCataLog();

    /**
     * 分类目录，获取当前分类的数据接口
     * @param cataLogId
     * @return
     */
    @GetMapping("/goods/category/catalog/{cataLogId}")
    public Result<List<CategoryVo>> getCurrentCataLog(@PathVariable Integer cataLogId);

    /**
     * 获得兄弟分类数据以及当前分类类子spu
     * @param cateid
     * @return
     */
    @GetMapping("/goods/category/index/{cateid}")
    public Result<CategoryPageVo> getBrotherCategoryAndCurrentGoodsList(@PathVariable Integer cateid);

    /**
     * 获得兄弟分类数据以及当前分类类子spu
     * @param cateid
     * @param page
     * @param size
     * @return
     */
    @GetMapping("/{cateid}")
    public Result<CategoryPageVo> getGoodsList(@PathVariable Integer cateid,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size
    );
}
