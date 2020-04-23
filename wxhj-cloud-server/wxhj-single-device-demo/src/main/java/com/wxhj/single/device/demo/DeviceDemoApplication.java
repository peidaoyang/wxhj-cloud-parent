package com.wxhj.single.device.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@EnableSwagger2
@ComponentScan({"com.wxhj.common.device", "com.wxhj.single.device.demo"})
public class DeviceDemoApplication {

    public static void main(String[] args) {
        SpringApplication.run(DeviceDemoApplication.class, args);
    }

}
