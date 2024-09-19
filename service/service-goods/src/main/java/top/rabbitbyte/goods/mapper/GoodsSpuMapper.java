package top.rabbitbyte.goods.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;
import top.rabbitbyte.model.entity.goods.GoodsSpu;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.goods.mapper
 * @Author: nemo2048
 * @CreateTime: 2024-09-14  00:22
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
@Repository
public interface GoodsSpuMapper extends BaseMapper<GoodsSpu> {
}
