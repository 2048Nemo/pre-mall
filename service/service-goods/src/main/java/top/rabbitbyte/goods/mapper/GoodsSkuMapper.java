package top.rabbitbyte.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;
import top.rabbitbyte.model.entity.goods.GoodsSku;

import java.util.Collection;

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
public interface GoodsSkuMapper extends BaseMapper<GoodsSku> {
    @Select("select * from goods_sku left join goods_spu on goods_spu.id = goods_sku.spu_id where goods_spu.category_id = #{cateid}")
    Collection<GoodsSku> getListByCateid(@Param("cateid")Integer cateid);
}
