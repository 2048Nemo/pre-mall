package top.rabbitbyte.goods.service;

import com.baomidou.mybatisplus.extension.service.IService;
import org.springframework.stereotype.Service;
import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsDetailVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsGallery;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsInfoVo;

import java.util.List;

@Service
public interface GoodsService extends IService<GoodsInfo> {
    GoodsInfo getGoodDetailInfo(Integer goodsid);

    List<GoodsGallery> getGoodImages(Integer goodsid);

    Integer getPersonSoldCount(Integer venderid);
}
