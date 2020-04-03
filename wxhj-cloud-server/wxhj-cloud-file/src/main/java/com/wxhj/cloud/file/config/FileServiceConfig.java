/** 
 * @fileName: FileServiceConfig.java  
 * @author: pjf
 * @date: 2019年10月23日 下午2:32:08 
 */

package com.wxhj.cloud.file.config;

import javax.annotation.Resource;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.wxhj.cloud.core.statics.RocketMqTopicStaticClass;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.rocketmq.config.RocketMqConfig;
import com.wxhj.cloud.rocketmq.def.DefRocketProducerImpl;

/**
 * @className FileServiceConfig.java
 * @author pjf
 * @date 2019年10月23日 下午2:32:08   
*/
/** 
 * @className FileServiceConfig.java
 * @author pjf
 * @date 2019年10月23日 下午2:32:08 
*/
@SpringBootConfiguration
public class FileServiceConfig {
	@Resource
	RocketMqConfig rocketMqConfig;
	
	@Bean
	public  RocketMqProducer configDefRocketProducer()
	{
		return new DefRocketProducerImpl(rocketMqConfig);
	}
}
