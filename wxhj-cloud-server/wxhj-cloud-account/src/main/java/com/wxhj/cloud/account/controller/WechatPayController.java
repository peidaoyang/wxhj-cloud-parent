package com.wxhj.cloud.account.controller;

import com.google.common.base.Charsets;
import com.google.common.base.Preconditions;
import com.google.common.io.CharStreams;
import com.wxhj.cloud.account.config.PayNotifyUrlConfig;
import com.wxhj.cloud.account.domain.WechatH5RechargeDO;
import com.wxhj.cloud.account.service.WechatH5RechargeService;
import com.wxhj.cloud.component.dto.H5OrderQueryRequestDTO;
import com.wxhj.cloud.component.dto.H5OrderQueryResponseDTO;
import com.wxhj.cloud.component.dto.H5UnifiedOrderRequestDTO;
import com.wxhj.cloud.component.dto.H5UnifiedOrderResponseDTO;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.core.utils.IpUtil;
import com.wxhj.cloud.feignClient.account.WechatPayClient;
import com.wxhj.cloud.feignClient.account.request.WechatH5OrderQueryRequestDTO;
import com.wxhj.cloud.feignClient.account.request.WechatH5UnifiedOrderRequestDTO;
import com.wxhj.cloud.feignClient.account.response.WechatH5OrderQueryResponseDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizePayInfoClient;
import com.wxhj.cloud.feignClient.platform.bo.OrganizePayInfoBO;
import com.wxhj.cloud.wechat.WXPay;
import com.wxhj.cloud.wechat.WXPayUtil;
import com.wxhj.cloud.wechat.WeChatPayConfig;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/wechat")
public class WechatPayController implements WechatPayClient {
    @Resource
    HttpServletRequest httpServletRequest;
    @Resource
    OrganizePayInfoClient organizePayInfoClient;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    WechatH5RechargeService wechatH5RechargeService;
    @Resource
    PaymentService paymentService;
    @Resource
    PayNotifyUrlConfig payNotifyUrlConfig;

    @PostMapping("/payResultNotify")
    @ApiOperation("H5支付成功的异步通知")
    public String payResultNotify() {
        //测试代码
        // String ipAddr = IpUtil.getIpAddr(httpServletRequest);
        //
        String bodyStr = null;
        Map<String, String> responseMap = new HashMap<>();
        responseMap.put("return_code", "SUCCESS");
        try {
            //byte[] bodyByte = CommUtil.readFileBytes(httpServletRequest.getInputStream());
            //byte[] bodyByte = ByteStreams.toByteArray(httpServletRequest.getInputStream());
           // bodyStr = new String(bodyByte, Charsets.UTF_8);
            bodyStr= CharStreams.toString(new InputStreamReader(httpServletRequest.getInputStream(),Charsets.UTF_8));
            Map<String, String> payResult = WXPayUtil.xmlToMap(bodyStr);
            String outTradeNo = payResult.get("out_trade_no");
            Preconditions.checkNotNull(outTradeNo);
            WechatH5RechargeDO wechatH5Recharge = wechatH5RechargeService.selectByOutTradeNo(outTradeNo);
            Preconditions.checkNotNull(wechatH5Recharge);
            WeChatPayConfig weChatPayConfig = new WeChatPayConfig(wechatH5Recharge.getAppid(), wechatH5Recharge.getMchId(), wechatH5Recharge.getKey());
            WXPay wxPay = new WXPay(weChatPayConfig);
            boolean isSuccess = wxPay.isPayResultNotifySignatureValid(payResult);
            if (!isSuccess) {
                return WXPayUtil.mapToXml(responseMap);
            }
            if (!wechatH5Recharge.orderResult()) {
                String result_code = payResult.get("result_code");
                Integer isSuccessInt = "SUCCESS".equals(result_code) ? 1 : 2;
                wechatH5RechargeService.updateSuccessMark(outTradeNo, isSuccessInt, payResult.get("err_code_des"));
            }
            responseMap.put("return_msg", "OK");
            return WXPayUtil.mapToXml(responseMap);
        } catch (Exception e) {
            return null;
        }
    }

    @PostMapping("/h5UnifiedOrder")
    @ApiOperation("H5的统一下单接口")
    @Override
    public WebApiReturnResultModel h5UnifiedOrder(@RequestBody @Validated WechatH5UnifiedOrderRequestDTO wechatH5UnifiedOrderRequest) {
        WechatH5RechargeDO wechatH5Recharge;
        String ipAddr = IpUtil.getIpAddr(httpServletRequest);
        OrganizePayInfoBO organizePayInfo;
        WebApiReturnResultModel webApiReturnResultModel = organizePayInfoClient.organizePayInfo(new CommonOrganizeRequestDTO(wechatH5UnifiedOrderRequest.getOrganizeId()));
        wechatH5Recharge = wechatH5RechargeService.selectByOutTradeNo(wechatH5UnifiedOrderRequest.getOutTradeNo());
        if (wechatH5Recharge == null) {
            try {
                organizePayInfo = FeignUtil.formatClass(webApiReturnResultModel, OrganizePayInfoBO.class);
            } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
                return wuXiHuaJieFeignError.getWebApiReturnResultModel();
            }
            wechatH5Recharge = dozerBeanMapper.map(organizePayInfo, WechatH5RechargeDO.class);
            wechatH5Recharge.setAppid(organizePayInfo.getWxAppid());
            wechatH5Recharge.setMchId(organizePayInfo.getWxMchId());
            wechatH5Recharge.setKey(organizePayInfo.getWxApiKey());
            wechatH5Recharge.setSpbillCreateIp(ipAddr);
            wechatH5RechargeService.insert(wechatH5Recharge);
        }
        try {
            H5UnifiedOrderRequestDTO h5UnifiedOrderRequest = dozerBeanMapper.map(wechatH5Recharge, H5UnifiedOrderRequestDTO.class);
            h5UnifiedOrderRequest.setNotifyUrl(payNotifyUrlConfig.getWechatNotifyUrl());
            WeChatPayConfig weChatPayConfig = new WeChatPayConfig(wechatH5Recharge.getAppid(), wechatH5Recharge.getMchId(), wechatH5Recharge.getKey());
            H5UnifiedOrderResponseDTO h5UnifiedOrderResponse = paymentService.wechatH5UnifiedOrder(weChatPayConfig, h5UnifiedOrderRequest);
            return WebApiReturnResultModel.ofSuccess(h5UnifiedOrderResponse);
        } catch (Exception e) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.OTHER_ERROR, e.getMessage().toString());
        }
    }

    @Override
    @PostMapping("/h5OrderQuery")
    @ApiOperation("H5的订单查询接口")
    public WebApiReturnResultModel h5OrderQuery(@RequestBody @Validated WechatH5OrderQueryRequestDTO wechatH5OrderQueryRequest) {

        WechatH5RechargeDO wechatH5Recharge = wechatH5RechargeService.selectByOutTradeNo(wechatH5OrderQueryRequest.getOutTradeNo());
        WechatH5OrderQueryResponseDTO wechatH5OrderQueryResponse = dozerBeanMapper.map(wechatH5OrderQueryRequest, WechatH5OrderQueryResponseDTO.class);

        if (wechatH5Recharge == null) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ORDER_NOT_EXISTENT);
        }
        if (wechatH5Recharge.getIsSuccessMark().equals(0)) {
            H5OrderQueryRequestDTO h5OrderQueryRequest =
                    dozerBeanMapper.map(wechatH5OrderQueryRequest, H5OrderQueryRequestDTO.class);

            WeChatPayConfig weChatPayConfig = new WeChatPayConfig(wechatH5Recharge.getAppid(), wechatH5Recharge.getMchId(), wechatH5Recharge.getKey());
            H5OrderQueryResponseDTO h5OrderQueryResponse = null;
            try {
                h5OrderQueryResponse = paymentService.wechatH5OrderQuery(weChatPayConfig, h5OrderQueryRequest);
            } catch (Exception e) {
                return WebApiReturnResultModel.ofStatus(WebResponseState.WECHAT_ERROR, e.getMessage().toString());
            }
            if (h5OrderQueryResponse.isSuccess()) {
                if ("SUCCESS".equals(h5OrderQueryResponse.getResultCode())) {
                    wechatH5Recharge.setIsSuccessMark(1);
                } else {
                    wechatH5Recharge.setIsSuccessMark(2);
                    wechatH5Recharge.setErrMessage(h5OrderQueryResponse.getErrCodeDes());
                }
                wechatH5RechargeService.updateSuccessMark(wechatH5OrderQueryRequest.getOutTradeNo(), wechatH5Recharge.getIsSuccessMark(), wechatH5Recharge.getErrMessage());
            } else {
                return WebApiReturnResultModel.ofStatus(WebResponseState.WECHAT_ERROR);
            }
        }
        wechatH5OrderQueryResponse.setSuccess(wechatH5Recharge.orderResult());
        wechatH5OrderQueryResponse.setErrMessage(wechatH5Recharge.getErrMessage());
        return WebApiReturnResultModel.ofSuccess(wechatH5OrderQueryResponse);
    }
}
