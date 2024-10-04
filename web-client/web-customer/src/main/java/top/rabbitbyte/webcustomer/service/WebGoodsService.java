package top.rabbitbyte.webcustomer.service;

import top.rabbitbyte.comon.utils.result.JWTUser;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsDetailVo;

public interface WebGoodsService {
    GoodsDetailVo getGoodDetailInfo(Integer goodsid);

    String wantGoods(JWTUser jwtUser,Integer goodsid, String sellerid);
}
