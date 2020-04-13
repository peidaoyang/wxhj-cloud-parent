package com.wxhj.cloud.component.dto;

import lombok.Data;

@Data
public class H5OrderQueryRequestDTO extends WechatBasiceRequestDTO {
    private String appid;
    private String mchId;
   // private String transactionId;
    private String outTradeNo;
    private String nonceStr;
}
