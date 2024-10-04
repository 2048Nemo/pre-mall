package top.rabbitbyte.goods.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import top.rabbitbyte.customer.client.CustomerInfoFeignClient;
import top.rabbitbyte.goods.mapper.GoodsCategoryMapper;
import top.rabbitbyte.goods.mapper.GoodsImagesMapper;
import top.rabbitbyte.goods.mapper.GoodsInfoMapper;
import top.rabbitbyte.goods.service.GoodsService;
import top.rabbitbyte.model.coverter.GoodsInfoConverter;
import top.rabbitbyte.model.entity.goods.GoodsCategory;
import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsDetailVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsGallery;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsInfoVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.RelatedGoods;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.goods.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  18:45
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class GoodsServiceImp extends ServiceImpl<GoodsInfoMapper, GoodsInfo> implements GoodsService  {

    @Autowired
    private GoodsInfoMapper goodsInfoMapper;

    @Autowired
    private GoodsImagesMapper goodsImagesMapper;

    @Autowired
    private GoodsCategoryMapper goodsCategoryMapper;


    @Override
    public GoodsInfo getGoodDetailInfo(Integer goodsid) {
        GoodsInfo goodsInfo = goodsInfoMapper.selectById(goodsid);

        return goodsInfo;
    }

    @Override
    public List<GoodsGallery> getGoodImages(Integer goodsid) {
         List<GoodsGallery> result = goodsImagesMapper.getImgUrlList(goodsid).stream().map(item -> {
            GoodsGallery item2 = new GoodsGallery();
            BeanUtils.copyProperties(item, item2);
            return item2;
        }).collect(Collectors.toList());
        return result;
    }

    @Override
    public Integer getPersonSoldCount(Integer venderid) {
        return goodsInfoMapper.getPersonSoldCount(venderid);
    }

    @Override
    public List<RelatedGoods> getRelatedGoods(Long goodsid) {
        return goodsInfoMapper.getRelatedGoodsCategorys(goodsid).stream().map(item -> {
            RelatedGoods relatedGoods = RelatedGoods.fromGoodsInfo(item);
           return relatedGoods;
        }).collect(Collectors.toList());
    }

    @Override
    public String wantGoods(Integer goodsid, String sellerid) {
        return getChatRoomId(goodsid, sellerid);
    }

    public String getChatRoomId(Integer goodsid, String sellerid) {
        return goodsid.toString() + sellerid;
    }
}
