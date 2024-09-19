package top.rabbitbyte.customer.client;


import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.model.form.customer.UpdateWxPhoneForm;
import top.rabbitbyte.model.vo.customer.CustomerInfoVo;
import top.rabbitbyte.model.vo.customer.CustomerLoginVo;

@FeignClient(value = "service-customer")
public interface CustomerInfoFeignClient {

    @GetMapping("/customer/info/login/{code}")
    public Result<Long> login(@PathVariable String code);

    @GetMapping("/customer/info/getCustomerLoginInfo/{customerId}")
    Result<CustomerLoginVo> getCustomerLoginInfo(@PathVariable("customerId") Long customerId);

    /**
     * @Operation(summary = "获取客户信息")
     * @param customerId
     * @return
     */
    @GetMapping("/customer/info/getCustomerInfo/{customerId}")
    Result<CustomerInfoVo> getCustomerInfo(@PathVariable Long customerId);
    /**
     * 更新客户微信手机号码
     * @param updateWxPhoneForm
     * @return
     */
    @PostMapping("/customer/info/updateWxPhoneNumber")
    Result<Boolean> updateWxPhoneNumber(@RequestBody UpdateWxPhoneForm updateWxPhoneForm);

    /**
     * 获取客户OpenId
     * @param customerId
     * @return
     */
    @GetMapping("/customer/info/getCustomerOpenId/{customerId}")
    Result<String> getCustomerOpenId(@PathVariable("customerId") Long customerId);
}