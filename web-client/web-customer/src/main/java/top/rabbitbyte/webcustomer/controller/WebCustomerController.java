package top.rabbitbyte.webcustomer.controller;

import io.swagger.v3.oas.annotations.Operation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.model.form.customer.UpdateWxPhoneForm;
import top.rabbitbyte.model.form.customer.WeixinLoginFrom.UserInfo;
import top.rabbitbyte.model.form.customer.WeixinLoginFrom.WeixinLoginForm;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.model.vo.customer.CustomerLoginVo;
import top.rabbitbyte.webcustomer.service.WebCustomerService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.controller
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  18:38
 * @Description: TODO
 * @Version: 1.0
 */

@RestController
@RequestMapping("/customer/info")
public class WebCustomerController {
    @Autowired
    private WebCustomerService customerInfoService;
    @Operation(summary = "获取客户登录信息")
    @GetMapping("/getCustomerLoginInfo/{customerId}")
    public Result<CustomerLoginVo> getCustomerLoginInfo(@PathVariable Long customerId) {
        CustomerLoginVo customerLoginVo = customerInfoService.getCustomerLoginInfo(customerId);
        return Result.ok(customerLoginVo);
    }

    /**
     * 获取客户个人信息
     * @param customerId
     * @return customerInfoVo
     */
    @Operation(summary = "获取客户登录信息")
    @GetMapping("/getCustomerInfo/{customerId}")
    public Result<CustomerInfoVo> getCustomerInfo(@PathVariable Long customerId) {
        CustomerInfoVo customerInfoVo = customerInfoService.getCustomerInfo(customerId);
        return Result.ok(customerInfoVo);
    }


    //微信小程序登录接口
    @Operation(summary = "小程序授权登录")
    @PostMapping("/loginByWeixin")
    public Result<UserInfo> login(@RequestBody WeixinLoginForm weixinLoginForm) {
        return Result.ok(customerInfoService.login(weixinLoginForm.getCode()));
    }

    @Operation(summary = "更新客户微信手机号码")
    @PostMapping("/updateWxPhoneNumber")
    public Result<Boolean> updateWxPhoneNumber(@RequestBody UpdateWxPhoneForm updateWxPhoneForm) {
        return Result.ok(customerInfoService.updateWxPhoneNumber(updateWxPhoneForm));
    }

    @Operation(summary = "获取客户OpenId")
    @GetMapping("/getCustomerOpenId/{customerId}")
    public Result<String> getCustomerOpenId(@PathVariable Long customerId) {
        return Result.ok(customerInfoService.getCustomerOpenId(customerId));
    }
}
