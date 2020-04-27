package com.wxhj.cloud.app.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.WechatPayClient;
import com.wxhj.cloud.feignClient.account.request.WechatH5OrderQueryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.WechatH5UnifiedOrderRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@Api(tags = "app微信支付")
@RestController
@RequestMapping("/wechat")
public class WechatPayController {
    @Resource
    WechatPayClient wechatPayClient;

    @ApiOperation("H5的统一下单接口")
    @PostMapping("/hUnifiedOrder")
    @LcnTransaction
    public WebApiReturnResultModel hUnifiedOrder(@RequestBody @Validated WechatH5UnifiedOrderRequestDTO wechatH5UnifiedOrderRequest) {
        return wechatPayClient.h5UnifiedOrder(wechatH5UnifiedOrderRequest);
    }

    @ApiOperation("H5的订单查询接口")
    @PostMapping("/hOrderQuery")
    @LcnTransaction
    public WebApiReturnResultModel hOrderQuery(@RequestBody @Validated WechatH5OrderQueryRequestDTO wechatH5OrderQueryRequest) {
        return wechatPayClient.h5OrderQuery(wechatH5OrderQueryRequest);
    }
}
