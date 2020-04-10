package com.wxhj.cloud.wechat.bo;

import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
public class MicroPayBO {
    @NotBlank
    //公众账号ID
    private String appid;
    @NotBlank
    //商户号
    private String mchId;
    //设备号
    private String deviceInfo;
    @NotBlank
    //随机字符串
    private String nonceStr;
    @NotBlank
    //签名
    private String sign;
    //HMAC-SHA256和MD5 默认为MD5
    //签名类型
    private String signType;
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
    private String totalFee;
    //货币类型
    private String feeType;
    //终端IP4
    private String spbillCreateIp;
    //订单优惠标记
    private String goodsTag;
    //指定支付方式	no_credit--指定不能使用信用卡支付
    private String limitPay;
    //交易结束时间
    private String timeStart;
    //电子发票入口开放标识 Y
    private String receipt;
    //付款码
    @NotBlank
    private String authCode;
}
