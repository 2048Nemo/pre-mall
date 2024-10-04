package top.rabbitbyte.webcustomer.service.imp;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.comon.utils.result.JWTUser;
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

        //4.relatedGoods 根据传入的货物id 联合货物所属品类的spuid 查找parent spuid 下的同类商品
        //todo 待优化：加入es等智能的搜索
        goodsDetailVo.setRelatedGoods(goodsInfoFeignClient.getRelatedGoods(goodsinfo.getId()));

        //5.userHasCollect 需要添加一个字段在用户表
        goodsDetailVo.setUserHasCollect(customerInfoFeignClient.isCollected(goodsid).getData());

        //Todo 尝试异步编排执行查询
        return goodsDetailVo;
    }

    @Override
    public String wantGoods(JWTUser jwtUser,Integer goodsid, String sellerid) {
        return goodsInfoFeignClient.wantGoods(jwtUser,goodsid,sellerid).getData();
    }

}
