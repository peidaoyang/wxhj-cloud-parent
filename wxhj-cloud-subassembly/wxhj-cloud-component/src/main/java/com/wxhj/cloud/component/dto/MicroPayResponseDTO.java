package com.wxhj.cloud.component.dto;

import lombok.Data;

@Data
public class MicroPayResponseDTO extends WechatBasiceResponseDTO {

    //return_code为SUCCESS的时候
    private String appid;
    private String mchId;
    private String sign;

    private String deviceInfo;
    private String nonceStr;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    //return_code 和result_code都为SUCCESS
    private String openid;
    private String isSubscribe;
    private String tradeType;
    private String bankType;
    private String feeType;
    private Integer totalFee;
    private Integer settlementTotalFee;
    private Integer couponFee;
    private String cashFeeType;
    private Integer cashFee;
    private String transactionId;
    private String outTradeNo;
    private String attach;
    private String timeEnd;
    private String promotionDetail;
}
