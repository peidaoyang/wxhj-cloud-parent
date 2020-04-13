package com.wxhj.cloud.component.dto;

import lombok.Data;

@Data
public class H5UnifiedOrderResponseDTO extends WechatBasiceResponseDTO {

    //return_code为SUCCESS
    private String appid;
    private String mchId;
    private String deviceInfo;
    private String nonceStr;
    private String sign;
    private String resultCode;
    private String errCode;
    private String errCodeDes;
    //return_code 和result_code都为SUCCESS
    private String tradeType;
    private String prepayId;
    private String mwebUrl;
}
