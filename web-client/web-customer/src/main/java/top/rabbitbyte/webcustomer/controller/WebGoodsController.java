package top.rabbitbyte.webcustomer.controller;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.rabbitbyte.comon.utils.result.JWTUser;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.comon.utils.token.injection.JWT;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsDetailVo;
import top.rabbitbyte.webcustomer.service.WebGoodsService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.controller
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  16:14
 * @Description: TODO
 * @Version: 1.0
 */


@Tag(name = "货物接口管理")
@RestController
@RequestMapping(value="/goods")
@SuppressWarnings({"unchecked", "rawtypes"})
public class WebGoodsController {
    @Autowired
    private WebGoodsService webGoodsService;
    @GetMapping("/detail/{goodsid}")
    public Result<GoodsDetailVo> getGoodDetailInfo(@PathVariable Integer goodsid ){
        return Result.ok(webGoodsService.getGoodDetailInfo(goodsid));
    }
    @PostMapping("/user/want/{goodsid}/{sellerid}")
    public Result<String> wantGoods(@JWT JWTUser jwtuser, @PathVariable("goodsid") Integer goodsid,
                                    @PathVariable("sellerid") String sellerid){
        return Result.ok(webGoodsService.wantGoods(jwtuser,goodsid, sellerid));
    }
}
