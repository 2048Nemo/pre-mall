package top.rabbitbyte.webcustomer.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import top.rabbitbyte.comon.utils.result.Result;
import top.rabbitbyte.model.entity.coupon.CouponInfo;
import top.rabbitbyte.model.vo.base.PageVo;
import top.rabbitbyte.model.vo.coupon.NoReceiveCouponVo;
import top.rabbitbyte.webcustomer.service.WebCouponService;

/**
 * @BelongsProject: pre-mall
 * @BelongsPackage: top.rabbitbyte.webcustomer.controller
 * @Author: nemo2048
 * @CreateTime: 2024-09-27  18:46
 * @Description:
 * @Version: 1.0
 */

@RestController
@RequestMapping(value ="/coupon/info")
public class WebCouponController {

    @Autowired
    private WebCouponService couponInfoService;
    @Operation(summary = "查询未领取优惠券分页列表")
    @GetMapping("findNoReceivePage/{customerId}/{page}/{limit}")
    public Result<PageVo<NoReceiveCouponVo>> findNoReceivePage(
            @Parameter(name = "customerId", description = "乘客id", required = true)
            @PathVariable Long customerId,
            @Parameter(name = "page",description = "当前页码",required = true)
            @PathVariable Long page,
            @Parameter(name = "limit", description = "每页记录数", required = true)
            @PathVariable Long limit){
        Page<CouponInfo> pageParam = new Page<>(page,limit);
        PageVo<NoReceiveCouponVo> pageVo = couponInfoService.findNoReceivePage(pageParam,customerId);
        pageVo.setPage(page);
        pageVo.setLimit(limit);
        return Result.ok(pageVo);
    }
//
//    @Operation(summary = "全部优惠券分页列表")
//    @GetMapping("findAllPage/{page}/{limit}")
//    public Result<PageVo<NoUseCouponVo>> findAllPage(
//            @Parameter(name = "page",description = "当前页码",required = true)
//            @PathVariable Long page,
//            @Parameter(name = "limit", description = "每页记录数", required = true)
//            @PathVariable Long limit){
//        Page<CouponInfo> pageParam = new Page<>(page,limit);
//        PageVo<NoUseCouponVo>pageVo = couponInfoService.findAllPage(pageParam);
//    }


    @Operation(description = "用户领取优惠券")
    @GetMapping("/receive/{customerId}/{couponId}")
    public Result<Boolean> recive(@PathVariable Long customerId, @PathVariable Long couponId){
        //接口需要特殊判断一下
        if (couponInfoService.receive(customerId,couponId)){
            return Result.ok(true);
        }
        return Result.fail(false);
    }
}
