package top.rabbitbyte.webcustomer.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.customer.client.CustomerInfoFeignClient;
import top.rabbitbyte.goods.client.GoodsInfoFeignClient;
import top.rabbitbyte.model.coverter.GoodsInfoConverter;
import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsDetailVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsInfoVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.SellerInfo;
import top.rabbitbyte.webcustomer.service.WebGoodsService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  16:47
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class WebGoodsServiceImp implements WebGoodsService {
    @Autowired
    private GoodsInfoFeignClient goodsInfoFeignClient;

    @Autowired
    private CustomerInfoFeignClient customerInfoFeignClient;
    @Override
    public GoodsDetailVo getGoodDetailInfo(Integer goodsid) {
        GoodsDetailVo goodsDetailVo = new GoodsDetailVo();

        /**
         *     private GoodsInfoVo info;
         *     private List<GoodsGallery> gallery;
         *     private SellerInfo seller;
         *     private List<Comment> comment;
         *     private List<RelatedGoods> relatedGoods;
         *     private Integer userHasCollect;
         */
        //1.GoodsInfoVo
        GoodsInfo goodsinfo =goodsInfoFeignClient.getGoodDetailInfo(goodsid).getData();
        //手动转换类型
        GoodsInfoVo goodsInfoVo = GoodsInfoConverter.toVo(goodsinfo);
        goodsDetailVo.setInfo(goodsInfoVo);


        //2.List<GoodsGallery>
        goodsDetailVo.setGallery(goodsInfoFeignClient.getGoodImages(goodsid).getData());

        //3.SellerInfo
        SellerInfo seller = customerInfoFeignClient.getSellerInfo(goodsinfo.getVenderId()).getData();
        goodsDetailVo.setSeller(seller);


        //Todo 尝试异步编排执行查询

        return goodsDetailVo;
    }

}
