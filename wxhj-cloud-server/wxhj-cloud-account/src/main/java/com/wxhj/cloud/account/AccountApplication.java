package com.wxhj.cloud.account;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication(scanBasePackages = {"com.wxhj.cloud.account", "com.wxhj.cloud.sso", "com.wxhj.cloud.driud",
        "com.wxhj.cloud.feignClient", "com.wxhj.cloud.redis", "com.wxhj.cloud.core", "com.wxhj.cloud.swagger",
        "com.wxhj.cloud.rocketmq", "com.wxhj.cloud.component"})
@EnableDiscoveryClient
@EnableSwagger2
//@EnableCircuitBreaker
//@EnableDistributedTransaction
@EnableFeignClients({"com.wxhj.cloud.feignClient.*"})
public class AccountApplication {
    public static void main(String[] args) {
        SpringApplication.run(AccountApplication.class, args);
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
