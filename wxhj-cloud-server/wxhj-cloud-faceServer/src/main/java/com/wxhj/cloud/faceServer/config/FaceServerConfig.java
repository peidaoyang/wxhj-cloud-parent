/** 
 * @fileName: FaceServerConfig.java  
 * @author: pjf
 * @date: 2019年11月25日 下午1:59:25 
 */

package com.wxhj.cloud.faceServer.config;

import javax.annotation.Resource;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.rocketmq.config.RocketMqConfig;
import com.wxhj.cloud.rocketmq.def.DefRocketProducerImpl;

import lombok.Data;

/**
 * @className FaceServerConfig.java
 * @author pjf
 * @date 2019年11月25日 下午1:59:25   
*/
/**
 * @className FaceServerConfig.java
 * @author pjf
 * @date 2019年11月25日 下午1:59:25
 */
@SpringBootConfiguration
@Data
public class FaceServerConfig {
	@Resource
	RocketMqConfig rocketMqConfig;

	@Bean
	public RocketMqProducer ConfigDefRocketProducer() {
		return new DefRocketProducerImpl(rocketMqConfig);
	}
}
