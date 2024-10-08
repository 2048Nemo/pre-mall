package top.rabbitbyte.customer.service.imp;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaJscode2SessionResult;
import cn.binarywang.wx.miniapp.bean.WxMaPhoneNumberInfo;
import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import top.rabbitbyte.customer.mapper.CustomerCollectMapper;
import top.rabbitbyte.customer.mapper.CustomerInfoMapper;
import top.rabbitbyte.customer.mapper.CustomerLoginLogMapper;
import top.rabbitbyte.customer.service.CustomerInfoService;
import top.rabbitbyte.goods.client.GoodsInfoFeignClient;
import top.rabbitbyte.model.entity.customer.CustomerCollect;
import top.rabbitbyte.model.entity.customer.CustomerInfo;
import top.rabbitbyte.model.entity.customer.CustomerLoginLog;
import top.rabbitbyte.model.form.customer.UpdateWxPhoneForm;
import top.rabbitbyte.model.form.customer.WeixinLoginFrom.UserInfo;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.model.vo.customer.CustomerLoginVo;
import top.rabbitbyte.model.vo.goods.goodsDetailVo.SellerInfo;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.customer.service.imp
 * @Author: nemo2048
 * @CreateTime: 2024-08-27  19:03
 * @Description: TODO
 * @Version: 1.0
 */
@Service
public class CustomerInfoServiceimp extends ServiceImpl<CustomerInfoMapper, CustomerInfo> implements CustomerInfoService {

    @Autowired
    private  CustomerInfoMapper customerInfoMapper;

    @Autowired
    private WxMaService wxMaService;

    @Autowired
    private CustomerLoginLogMapper customerLoginLogMapper;

    @Autowired
    private GoodsInfoFeignClient goodsInfoFeignClient;

    @Autowired
    private CustomerCollectMapper customerCollectMapper;
    @Override
    public UserInfo login(String code) {
        UserInfo userInfo = new UserInfo();

        String openId = null;

        try {
            WxMaJscode2SessionResult sessionInfo = wxMaService.getUserService().getSessionInfo(code);
            openId = sessionInfo.getOpenid();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

        //根据openid查询用户信息,也即是wxopenid 字段
        LambdaQueryWrapper<CustomerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerInfo::getWxOpenId,openId);
        CustomerInfo customerInfo =  customerInfoMapper.selectOne(queryWrapper);

        //第一次登陆
        if (customerInfo == null){
            customerInfo = new CustomerInfo();
            customerInfo.setNickname(String.valueOf(System.currentTimeMillis()));
            customerInfo.setAvatarUrl("https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
            customerInfo.setWxOpenId(openId);
            customerInfoMapper.insert(customerInfo);
        }
        //再将数据查询出来
        CustomerInfo customer2User =  customerInfoMapper.selectOne(queryWrapper);
        BeanUtils.copyProperties(customer2User,userInfo);

        //写入日志
        CustomerLoginLog customerLoginLog = new CustomerLoginLog();
        customerLoginLog.setCustomerId(customerInfo.getId());
        customerLoginLog.setMsg("小程序登陆");
        customerLoginLogMapper.insert(customerLoginLog);
        return userInfo;
    }

    @Override
    public CustomerLoginVo getCustomerLoginInfo(Long customerId) {
        // 获取用户信息
        CustomerInfo customerInfo = customerInfoMapper.selectById(customerId);
        //创建返回结果
        CustomerLoginVo customerInfoVo = new CustomerLoginVo();

        //使用bean util复制属性
        BeanUtils.copyProperties(customerInfo,customerInfoVo);

        String phone = customerInfo.getPhone();
        boolean isBindPhone = StringUtils.hasText(phone);
        customerInfoVo.setIsBindPhone(isBindPhone);

        return customerInfoVo;
    }

    @Override
    public CustomerInfoVo getCustomerInfo(Long customerId) {
        // 获取用户信息
        CustomerInfo customerInfo = customerInfoMapper.selectById(customerId);
        //创建返回结果
        CustomerInfoVo customerInfoVo = new CustomerInfoVo();

        //使用bean util复制属性
        BeanUtils.copyProperties(customerInfo,customerInfoVo);

        return customerInfoVo;
    }

    @Override
    public Boolean updateWxPhoneNumber(UpdateWxPhoneForm updateWxPhoneForm) {
        if (updateWxPhoneForm.getCustomerId() == null|| updateWxPhoneForm.getCode()==null){
            return false;
        }

        try {
            //获取手机号
            WxMaPhoneNumberInfo phoneNoInfo = wxMaService.getUserService().getPhoneNoInfo(updateWxPhoneForm.getCode());
            String phoneNumber =  phoneNoInfo.getPhoneNumber();
            //开始更新手机号
            LambdaQueryWrapper<CustomerInfo> queryWrapper = new LambdaQueryWrapper<>();
            queryWrapper.eq(CustomerInfo::getId,updateWxPhoneForm.getCustomerId());
            customerInfoMapper.selectOne(queryWrapper).setPhone(phoneNumber);
            return true;
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public String getCustomerOpenId(Long customerId) {
        LambdaQueryWrapper<CustomerInfo> queryWrapper = new LambdaQueryWrapper<>();
        queryWrapper.eq(CustomerInfo::getId,customerId);
        CustomerInfo customerInfo = customerInfoMapper.selectOne(queryWrapper);
        return customerInfo.getWxOpenId();
    }

    //由于涉及到另一张表soldCount参数需要跨微服务调用查询了
    @Override
    public SellerInfo getSellerInfo(Integer venderId) {
        SellerInfo sellerInfo = SellerInfo.CustomerInfoConvert(customerInfoMapper.selectById(venderId));
        sellerInfo.setSoldCount(goodsInfoFeignClient.getPersonSoldCount(venderId));
        return sellerInfo;
    }

    @Override
    public Boolean isCollected(Integer goodsid) {
        CustomerCollect customerCollect  = customerCollectMapper.selectOne(new LambdaQueryWrapper<CustomerCollect>().eq(CustomerCollect::getGoodsId,goodsid));
        return customerCollect != null;
    }
}
