package top.rabbitbyte.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.rabbitbyte.model.entity.goods.GoodsCategory;

@Mapper
@Repository
public interface GoodsCategoryMapper extends BaseMapper<GoodsCategory> {
}
