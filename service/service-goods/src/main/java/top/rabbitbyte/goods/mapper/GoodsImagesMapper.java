package top.rabbitbyte.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;
import top.rabbitbyte.model.entity.goods.GoodsImages;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.GoodsGallery;

import java.util.List;

@Mapper
public interface GoodsImagesMapper  extends BaseMapper<GoodsImages> {

    @Select("select id,img_url,sort" +
            " from goods_images where goods_id = #{goodsid} order by sort asc")
    List<GoodsImages> getImgUrlList(Integer goodsid);
}
