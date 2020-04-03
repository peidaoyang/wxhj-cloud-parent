package com.wxhj.cloud.component.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

@SpringBootConfiguration
@ConfigurationProperties("wxhj.baidu")
@Data
public class BaiduConfig {
	private String fileBucketName = "burcket-test1";
}
