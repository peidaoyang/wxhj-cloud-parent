/** 
 * @fileName: platformServiceConfig.java  
 * @author: pjf
 * @date: 2019年11月29日 上午11:36:06 
 */

package com.wxhj.cloud.platform.config;

import javax.annotation.Resource;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.rocketmq.config.RocketMqConfig;
import com.wxhj.cloud.rocketmq.def.DefRocketProducerImpl;

/**
 * @className platformServiceConfig.java
 * @author pjf
 * @date 2019年11月29日 上午11:36:06
 */

@SpringBootConfiguration
public class PlatformServiceConfig {

	@Resource
	RocketMqConfig rocketMqConfig;

	@Bean
	public RocketMqProducer configDefRocketProducer() {
		return new DefRocketProducerImpl(rocketMqConfig);
	}
}
