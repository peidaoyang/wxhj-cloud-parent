package com.wxhj.cloud.component.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MicroPayRequestDTO extends WechatBasiceRequestDTO {
    //
    //private String subAppid;
    //private String subMchId;

    //
    @NotBlank
    //公众账号ID
    private String appid;
    @NotBlank
    //商户号
    private String mchId;

    @NotBlank
    //设备号
    private String deviceInfo;


    @NotBlank
    //随机字符串
    private String nonceStr;
    @NotBlank
    //商品描述
    private String body;
    //商品详情
    private String detail;
    //附加数据
    private String attach;
    @NotBlank
    //商户订单号
    private String outTradeNo;
    @NotBlank
    //订单金额
    private Integer totalFee;
    //货币类型
    private String feeType;
    //终端IP4
    private String spbillCreateIp;
    //订单优惠标记
    private String goodsTag;
    //指定支付方式	no_credit--指定不能使用信用卡支付
    private String limitPay;
    //电子发票入口开放标识 Y
    private String receipt;
    //付款码
    @NotBlank
    private String authCode;
}
