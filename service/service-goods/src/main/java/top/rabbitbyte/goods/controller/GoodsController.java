package top.rabbitbyte.goods.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.rabbitbyte.comon.utils.result.JWTUser;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.comon.utils.token.injection.JWT;
import top.rabbitbyte.goods.service.GoodsService;
import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsGallery;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.RelatedGoods;

import java.util.List;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.goods.controller
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  12:51
 * @Description: TODO
 * @Version: 1.0
 */
@Slf4j
@Tag(name = "分类接口信息")
@RestController
@RequestMapping("/goods")
@SuppressWarnings({"unchecked", "rawtypes"})
public class GoodsController {

    @Autowired
    private GoodsService goodsService;
    @GetMapping("/detail/{goodsid}")
    public Result<GoodsInfo> getGoodDetailInfo(@PathVariable Integer goodsid ){
        return Result.ok(goodsService.getGoodDetailInfo(goodsid));
    }

    @GetMapping("/detail/images/{goodsid}")
    Result<List<GoodsGallery>> getGoodImages(@PathVariable Integer goodsid){
        return Result.ok(goodsService.getGoodImages(goodsid));
    }
    @GetMapping("/person/soldcount/{venderid}")
    Integer getPersonSoldCount(@PathVariable Integer venderid){
        return goodsService.getPersonSoldCount(venderid);
    }

    @GetMapping("/related/{goodsid}")
    List<RelatedGoods> getRelatedGoods(@PathVariable Long goodsid){
        return goodsService.getRelatedGoods(goodsid);
    }

    @PostMapping("/user/want/{goodsid}/{sellerid}")
    public Result<String> wantGoods(@JWT JWTUser jwtuser, @PathVariable("goodsid") Integer goodsid,
                                    @PathVariable("sellerid") String sellerid) {
        return Result.ok(goodsService.wantGoods(goodsid, sellerid));
    }
}
