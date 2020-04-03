/** 
 * @fileName: RocketMQConfig.java  
 * @author: pjf
 * @date: 2019年10月17日 下午3:20:52 
 */

package com.wxhj.cloud.rocketmq.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @className RocketMQConfig.java
 * @author pjf
 * @date 2019年10月17日 下午3:20:52
 */

@SpringBootConfiguration
@ConfigurationProperties("wxhj.rocket")
@Data
public class RocketMqConfig {
	private String rocketServers;
	
	private String rocketGroup;
}
