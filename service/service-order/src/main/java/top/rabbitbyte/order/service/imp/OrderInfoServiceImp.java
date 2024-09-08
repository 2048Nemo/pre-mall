package top.rabbitbyte.order.service.imp;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;
import top.rabbitbyte.model.entity.order.OrderInfo;
import top.rabbitbyte.order.mapper.OrderInfoMapper;
import top.rabbitbyte.order.service.OrderInfoService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.order.service
 * @Author: nemo2048
 * @CreateTime: 2024-09-07  20:10
 * @Description: TODO
 * @Version: 1.0
 */

@Service
@SuppressWarnings({"unchecked", "rawtypes"})
public class OrderInfoServiceImp extends ServiceImpl<OrderInfoMapper, OrderInfo> implements OrderInfoService {
}
