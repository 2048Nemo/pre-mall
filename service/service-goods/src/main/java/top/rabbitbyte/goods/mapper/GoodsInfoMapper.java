package top.rabbitbyte.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.rabbitbyte.model.entity.goods.GoodsInfo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.RelatedGoods;

import java.util.Collection;
import java.util.List;
import java.util.ServiceLoader;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.goods.mapper
 * @Author: nemo2048
 * @CreateTime: 2024-09-19  10:33
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
@Repository
public interface GoodsInfoMapper extends BaseMapper<GoodsInfo> {
    @Select("select * from goods_info left join goods_spu on goods_spu.id = goods_info.spu_id where goods_spu.category_id = #{cateid}")
    Collection<GoodsInfo> getListByCateid(@Param("cateid")Integer cateid);

    @Select("select COUNT(*) from goods_info where  goods_info.is_selling = 0 and goods_info.vender_id = #{venderid}")
    Integer getPersonSoldCount(@Param("venderid")Integer venderid);

    @Select("SELECT id,`name`,image,price from goods_info join (\n" +
            "\tselect goods_info.spu_id \n" +
            "\tfrom goods_info \n" +
            "\twhere goods_info.id = #{goodsid}\n" +
            " \t) AS spuNumber ON goods_info.spu_id = spuNumber.spu_id AND goods_info.id != #{goodsid};")
    List<GoodsInfo> getRelatedGoodsCategorys(@Param("goodsid") Long goodsid);
}
