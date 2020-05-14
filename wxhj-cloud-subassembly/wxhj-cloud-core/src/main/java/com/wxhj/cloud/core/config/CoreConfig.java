package com.wxhj.cloud.core.config;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author pjf
 * @className CoreConfig.java
 * @date 2019年11月6日 下午1:09:45
 */
@SpringBootConfiguration
public class CoreConfig {
    @Bean
    public Mapper getDozerBeanMapper() {
        return DozerBeanMapperBuilder.buildDefault();
    }


}