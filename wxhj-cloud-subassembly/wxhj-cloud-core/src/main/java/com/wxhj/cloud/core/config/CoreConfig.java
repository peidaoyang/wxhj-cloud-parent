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


//    @Bean
//    public Formatter<LocalDate> localDateFormatter() {
//        return new Formatter<LocalDate>() {
//            @Override
//            public @Nullable
//            String print(@Nullable LocalDate object, @Nullable Locale locale) {
//                if (Objects.isNull(object)){
//                    return null;
//                }
//                return object.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            }
//
//            @Override
//            public @Nullable LocalDate parse(@Nullable String text, @Nullable Locale locale) {
//                if (!StringUtils.hasText(text)) {
//                    return null;
//                }
//                return LocalDate.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//            }
//        };
//    }
//
//    @Bean
//    public Formatter<LocalDateTime> localDateTimeFormatter() {
//        return new Formatter<LocalDateTime>() {
//            @Override
//            public @Nullable String print(@Nullable LocalDateTime object, @Nullable Locale locale) {
//                if (Objects.isNull(object)){
//                    return null;
//                }
//                return object.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            }
//
//            @Override
//            public @Nullable LocalDateTime parse(@Nullable String text, @Nullable Locale locale) {
//                if (!StringUtils.hasText(text)) {
//                    return null;
//                }
//                return LocalDateTime.parse(text, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//            }
//        };
//    }
//
//    @Bean
//    public Formatter<LocalTime> localTimeFormatter() {
//        return new Formatter<LocalTime>() {
//            @Override
//            public @Nullable String print(@Nullable LocalTime object, @Nullable Locale locale) {
//                if (Objects.isNull(object)){
//                    return null;
//                }
//                return object.format(DateTimeFormatter.ofPattern("HH:mm:ss"));
//            }
//
//            @Override
//            public @Nullable LocalTime parse(@Nullable String text, @Nullable Locale locale) {
//                if (!StringUtils.hasText(text)) {
//                    return null;
//                }
//                return LocalTime.parse(text, DateTimeFormatter.ofPattern("HH:mm:ss"));
//            }
//        };
//    }


}