package com.wxhj.cloud.core.config;


import com.github.dozermapper.core.DozerBeanMapperBuilder;
import com.github.dozermapper.core.Mapper;
import com.github.dozermapper.core.loader.api.BeanMappingBuilder;
import com.wxhj.cloud.core.dozer.LocalDateToDateConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

/**
 * @author pjf
 * @className CoreConfig.java
 * @date 2019年11月6日 下午1:09:45
 */
@SpringBootConfiguration()
public class CoreConfig {


    @Bean
    public Mapper getDozerBeanMapper() {
        //  Mapper dozerBeanMapper = DozerBeanMapperBuilder.buildDefault();
//        BeanMappingBuilder beanMappingBuilder = new BeanMappingBuilder() {
//            @Override
//            protected void configure() {
//                mapping(LocalDate.class, Date.class)
//                        .fields("localDate", "date",
//                                FieldsMappingOptions
//                                        .customConverter(LocalDateToDateConverter.class));
//            }
//        };


        Mapper dozerBeanMapper = DozerBeanMapperBuilder.create()

                .withCustomConverter(new LocalDateToDateConverter())
                .build();

        return dozerBeanMapper;

    }


}