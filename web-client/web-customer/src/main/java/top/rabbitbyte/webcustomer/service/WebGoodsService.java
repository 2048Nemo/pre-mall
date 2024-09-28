package top.rabbitbyte.webcustomer.service;

import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsDetailVo;

public interface WebGoodsService {
    GoodsDetailVo getGoodDetailInfo(Integer goodsid);
}
