package com.wxhj.cloud.component.dto;

import com.wxhj.common.device.dto.response.WechatBasiceResponseDTO;
import lombok.Data;

@Data
public class H5OrderQueryResponseDTO extends WechatBasiceResponseDTO {
    //在return_code为SUCCESS的时候有返回
    private String appid;
    private String mchId;
    private String nonceStr;
    private String sign;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    //在return_code 、result_code、trade_state都为SUCCESS时有返回 ，如trade_state不为 SUCCESS，则只返回out_trade_no（必传）和attach
    private String deviceInfo;
    private String openid;
    private String isSubscribe;
    private String tradeType;
    private String tradeState;
    private String bankType;
    private Integer totalFee;
    private Integer settlementTotalFee;
    private String feeType;
    private Integer cashFee;
    private String cashFeeType;
    private String transactionId;
    private String outTradeNo;
    private String attach;
    private String timeEnd;
    private String tradeStateDesc;
}
