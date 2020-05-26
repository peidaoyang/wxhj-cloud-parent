package com.wxhj.cloud.account.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Table(name = "wechat_h5_recharge")
public class WechatH5RechargeDO {
    private String organizeId;
    private String nonceStr;
    private String body;
    private String attach;
    @Id
    private String outTradeNo;
    private String feeType;
    private Integer totalFee;
    private String spbillCreateIp;
    private String tradeType;
    private String productId;
    private String openid;
    private String sceneInfo;
    private String appid;
    private String mchId;
    private String apiKey;
    private Integer isSuccessMark=0;
    private  String errMessage;

    public boolean orderResult()
    {
        return  isSuccessMark.equals(1);
    }

}
