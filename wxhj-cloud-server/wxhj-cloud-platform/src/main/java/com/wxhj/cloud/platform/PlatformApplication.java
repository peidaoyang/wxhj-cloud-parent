/** 
 * @fileName: PlatformApplication.java  
 * @author: pjf
 * @date: 2019年10月8日 下午2:39:15 
 */

package com.wxhj.cloud.platform;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className PlatformApplication.java
 * @author pjf
 * @date 2019年10月8日 下午2:39:15
 */

@SpringBootApplication(scanBasePackages = { "com.wxhj.cloud.sso", "com.wxhj.cloud.platform", "com.wxhj.cloud.driud",
		"com.wxhj.cloud.redis", "com.wxhj.cloud.swagger", "com.wxhj.cloud.core", "com.wxhj.cloud.feignClient",
		"com.wxhj.cloud.rocketmq","com.wxhj.cloud.component" })
@EnableSwagger2
@EnableDiscoveryClient
//@EnableCircuitBreaker
//@EnableDistributedTransaction
@EnableFeignClients({ "com.wxhj.cloud.feignClient.*" })
public class PlatformApplication {
	public static void main(String[] args) {
		SpringApplication.run(PlatformApplication.class, args);
	}

	/**
	 * 启用hystrix的/actuator/hystrix.stream地址映射
	 * 
	 * @return
	 */
	@Bean
	public ServletRegistrationBean getServlet() {
		HystrixMetricsStreamServlet streamServlet = new HystrixMetricsStreamServlet();
		ServletRegistrationBean registrationBean = new ServletRegistrationBean(streamServlet);
		registrationBean.setLoadOnStartup(1);
		registrationBean.addUrlMappings("/actuator/hystrix.stream");
		registrationBean.setName("HystrixMetricsStreamServlet");
		return registrationBean;
	}

}
