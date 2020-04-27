///**
// * @fileName: DeviceServiceConfig.java
// * @author: pjf
// * @date: 2019年12月17日 下午1:16:38
// */
//
//package com.wxhj.cloud.device.config;
//
//import javax.annotation.Resource;
//
//import org.springframework.boot.SpringBootConfiguration;
//import org.springframework.context.annotation.Bean;
//
//import com.wxhj.cloud.rocketmq.RocketMqProducer;
//import com.wxhj.cloud.rocketmq.config.RocketMqConfig;
//import com.wxhj.cloud.rocketmq.def.DefRocketProducerImpl;
//
///**
// * @className DeviceServiceConfig.java
// * @author pjf
// * @date 2019年12月17日 下午1:16:38
// */
//
//@SpringBootConfiguration
//public class DeviceServiceRocketConfig {
//	@Resource
//	RocketMqConfig rocketMqConfig;
//	@Bean
//	public RocketMqProducer configDefRocketProducer() {
//		return new DefRocketProducerImpl(rocketMqConfig);
//	}
//}
