package com.wxhj.cloud.app;

import com.netflix.hystrix.contrib.metrics.eventstream.HystrixMetricsStreamServlet;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import springfox.documentation.swagger2.annotations.EnableSwagger2;


@SpringBootApplication(scanBasePackages = {"com.wxhj.cloud.app", "com.wxhj.cloud.redis", "com.wxhj.cloud.swagger",
        "com.wxhj.cloud.core", "com.wxhj.cloud.feignClient"
//        , "com.wxhj.cloud.component"
})
@EnableSwagger2
@EnableDiscoveryClient
//@EnableCircuitBreaker
@EnableFeignClients({"com.wxhj.cloud.feignClient.*"})
public class AppApplication {
    public static void main(String[] args) {
        SpringApplication.run(AppApplication.class, args);
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
