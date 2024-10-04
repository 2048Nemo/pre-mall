package top.rabbitbyte.goods.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import top.rabbitbyte.comon.utils.result.JWTUser;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.comon.utils.token.injection.JWT;
import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsGallery;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsInfoVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.RelatedGoods;

import java.awt.datatransfer.Clipboard;
import java.util.List;

@FeignClient(value = "service-goods")
public interface GoodsInfoFeignClient {
    @GetMapping("/goods/detail/{goodsid}")
    Result<GoodsInfo> getGoodDetailInfo(@PathVariable Integer goodsid);

    @GetMapping("/goods/detail/images/{goodsid}")
    Result<List<GoodsGallery>> getGoodImages(@PathVariable Integer goodsid);

    /**
     * 一定要记得路径写全名
     * @param venderid
     * @return
     */
    @GetMapping("/goods/person/soldcount/{venderid}")
    Integer getPersonSoldCount(@PathVariable Integer venderid);

    @GetMapping("/goods/related/{goodsid}")
    List<RelatedGoods> getRelatedGoods(@PathVariable Long goodsid);

    @PostMapping("/goods/user/want/{goodsid}/{sellerid}")
    Result<String> wantGoods(@JWT JWTUser jwtUser, @PathVariable("goodsid") Integer goodsid,
                             @PathVariable("sellerid") String sellerid);
}
