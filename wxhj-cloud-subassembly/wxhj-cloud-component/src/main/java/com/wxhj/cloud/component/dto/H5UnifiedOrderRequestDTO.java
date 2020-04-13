package com.wxhj.cloud.component.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class H5UnifiedOrderRequestDTO extends WechatBasiceRequestDTO {
    @NotBlank
    //公众账号ID
    private String appid;
    @NotBlank
    //商户号
    private String mchId;
    //设备号
    private String deviceInfo;
    //
    private String nonceStr;
    private String body;
    private String detail;
    private String attach;
    private String outTradeNo;
    private String feeType;
    private Integer totalFee;
    private String spbillCreateIp;
    private String timeStart;
    private String timeExpire;
    private String goodsTag;
    private String notifyUrl;
    private String tradeType;
    private String productId;
    private String limitPay;
    private String openid;
    private String receipt;
    private String sceneInfo;
}
