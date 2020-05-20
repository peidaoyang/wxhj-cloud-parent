package com.wxhj.cloud.school;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;
import tk.mybatis.spring.annotation.MapperScan;

/**
 * @author wxpjf
 * @date 2020/5/12 11:19
 */
@SpringBootApplication(
        scanBasePackages = {
                "com.wxhj.cloud.school", "com.wxhj.cloud.redis",
                "com.wxhj.cloud.swagger", "com.wxhj.cloud.core",
                "com.wxhj.cloud.rocketmq", "com.wxhj.cloud.driud",
                "com.wxhj.cloud.component"})
@EnableSwagger2
@EnableDiscoveryClient
@EnableFeignClients({"com.wxhj.cloud.feignClient.*"})
@MapperScan(basePackages = "com.wxhj.cloud.school.mapper")
public class SchoolApplication {
    public static void main(String[] args) {
        SpringApplication.run(SchoolApplication.class, args);
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
