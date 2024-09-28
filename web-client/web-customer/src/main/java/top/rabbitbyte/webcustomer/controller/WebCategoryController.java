package top.rabbitbyte.webcustomer.controller;

import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.model.vo.goods.CataLogPageVo;
import top.rabbitbyte.model.vo.goods.CategoryPageVo;
import top.rabbitbyte.model.vo.goods.CategoryVo;
import top.rabbitbyte.webcustomer.service.WebCategoryService;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.controller
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  17:59
 * @Description: TODO
 * @Version: 1.0
 */

@Slf4j
@Tag(name = "分类接口信息")
@RestController
@RequestMapping("/goods/category")
public class WebCategoryController {

    @Autowired
    private WebCategoryService webCategoryService;
    @Schema(description = "分类目录，获取全部分类数据接口")
    @GetMapping("/catalog/index")
    public Result<CataLogPageVo> getAllCataLog(){
        return Result.ok(webCategoryService.getAllCatalog());
    }
    @Schema(description = "分类目录，获取当前分类的数据接口")
    @GetMapping("/catalog/{cataLogId}")
    public Result<List<CategoryVo>> getCurrentCataLog(@PathVariable Integer cataLogId){
        return Result.ok(webCategoryService.getCurrentCataLog(cataLogId));
    }

    @Schema(description = "获得兄弟分类数据以及当前分类类子spu")
    @GetMapping("/index/{cateid}")
    public Result<CategoryPageVo> getBrotherCategoryAndCurrentGoodsList(@PathVariable Integer cateid){
        return Result.ok(webCategoryService.getBroCateAndGoodsList(cateid));
    }
    @Schema(description = "获得兄弟分类数据以及当前分类类子spu")
    @GetMapping("/{cateid}")
    public Result<CategoryPageVo> getGoodsList(@PathVariable Integer cateid,
                                               @RequestParam(value = "page", defaultValue = "1") Integer page,
                                               @RequestParam(value = "size", defaultValue = "10") Integer size
    ){
        return Result.ok(webCategoryService.getGoodsList(cateid,page,size));
    }
}
