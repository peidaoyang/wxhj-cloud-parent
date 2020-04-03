/** 
 * @fileName: BusinessServerConfig.java  
 * @author: pjf
 * @date: 2019年12月23日 上午10:46:07 
 */

package com.wxhj.cloud.business.config;

import javax.annotation.Resource;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.rocketmq.config.RocketMqConfig;
import com.wxhj.cloud.rocketmq.def.DefRocketProducerImpl;

/**
 * @className BusinessServerConfig.java
 * @author pjf
 * @date 2019年12月23日 上午10:46:07
 */

@SpringBootConfiguration
public class BusinessServerConfig {
	@Resource
	RocketMqConfig rocketMqConfig;

	@Bean
	public RocketMqProducer configDefRocketProducer() {
		return new DefRocketProducerImpl(rocketMqConfig);
	}

}
