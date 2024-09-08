package top.rabbitbyte.order.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import top.rabbitbyte.model.entity.order.OrderInfo;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.order.mapper
 * @Author: nemo2048
 * @CreateTime: 2024-09-07  20:13
 * @Description: TODO
 * @Version: 1.0
 */
@Mapper
public interface OrderInfoMapper  extends BaseMapper<OrderInfo> {
}
