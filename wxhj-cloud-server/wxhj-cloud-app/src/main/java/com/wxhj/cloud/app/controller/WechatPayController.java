package com.wxhj.cloud.app.controller;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.WechatPayClient;
import com.wxhj.cloud.feignClient.account.request.WechatH5OrderQueryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.WechatH5UnifiedOrderRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/wechat")
@Api(tags = "微信支付")
public class WechatPayController {
    @Resource
    WechatPayClient wechatPayClient;

    @PostMapping("/h5UnifiedOrder")
    @ApiOperation("H5的统一下单接口")
    public WebApiReturnResultModel h5UnifiedOrder(@RequestBody @Validated WechatH5UnifiedOrderRequestDTO wechatH5UnifiedOrderRequest) {
        return wechatPayClient.h5UnifiedOrder(wechatH5UnifiedOrderRequest);
    }

    @PostMapping("/h5OrderQuery")
    @ApiOperation("H5的订单查询接口")
    public WebApiReturnResultModel h5OrderQuery(@RequestBody @Validated WechatH5OrderQueryRequestDTO wechatH5OrderQueryRequest) {
        return wechatPayClient.h5OrderQuery(wechatH5OrderQueryRequest);
    }
}
