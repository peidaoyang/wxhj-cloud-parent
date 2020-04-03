package com.wxhj.cloud.core.config;

import org.dozer.DozerBeanMapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
/**
 * 
 * @className CoreConfig.java
 * @author pjf
 * @date 2019年11月6日 下午1:09:45
 */
@SpringBootConfiguration()
public class CoreConfig {
	@Bean
	public DozerBeanMapper getDozerBeanMapper()
	{
		return new DozerBeanMapper();
	}
}