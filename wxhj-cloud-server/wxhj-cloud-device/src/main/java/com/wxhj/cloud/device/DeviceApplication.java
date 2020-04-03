package com.wxhj.cloud.device;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = { "com.wxhj.cloud.device", "com.wxhj.cloud.driud", "com.wxhj.cloud.redis",
		"com.wxhj.cloud.swagger", "com.wxhj.cloud.core", "com.wxhj.cloud.feignClient", 
		"com.wxhj.cloud.rocketmq","com.wxhj.cloud.component" })
@EnableSwagger2
//@EnableCircuitBreaker
@EnableDiscoveryClient
@EnableFeignClients({ "com.wxhj.cloud.feignClient.*" })
public class DeviceApplication {
	public static void main(String[] args) {
		SpringApplication.run(DeviceApplication.class, args);
	}

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
