package com.wxhj.cloud.account.config;

import lombok.Data;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

@SpringBootConfiguration
@ConfigurationProperties("wxhj.pay-notify-url")
@Data
public class PayNotifyUrlConfig {
    private String wechatNotifyUrl;
}
