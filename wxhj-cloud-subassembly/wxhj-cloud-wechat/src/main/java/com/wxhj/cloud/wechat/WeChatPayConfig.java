package com.wxhj.cloud.wechat;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.InputStream;

@Data
//@NoArgsConstructor
public class WeChatPayConfig extends WXPayConfig {

    //wxc6e8024c5fc01f2c
    private String appID;
    //1377624502
    private String mchID;
    //9ce46665edbe4f5dbb93f84e975111d9
    private String key;

    private InputStream certStream;

    private IWXPayDomain wXPayDomain = new WechatPayDomain();
    public WeChatPayConfig(){}
    public WeChatPayConfig(String appID, String mchID, String key) {
        this.appID = appID;
        this.mchID = mchID;
        this.key = key;
    }


}
