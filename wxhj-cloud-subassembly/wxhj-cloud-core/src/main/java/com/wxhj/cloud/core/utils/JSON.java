package com.wxhj.cloud.core.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * @author wxpjf
 * @date 2020/5/17 11:56
 */
@Slf4j
public class JSON {
    private static final ObjectMapper objectMapper = new ObjectMapper();

    static {
        JSON.config(objectMapper);
    }

    private JSON() {

    }

    public static void config(ObjectMapper objectMapper) {
        // 属性为Null的不进行序列化，只对pojo起作用，对map和list不起作用
        //objectMapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        // json进行换行缩进等操作
        //objectMapper.enable(SerializationFeature.INDENT_OUTPUT);
        // json不进行换行缩进等操作  默认就是不进行操作，写了这行和没写的效果一样
        //objectMapper.disable(SerializationFeature.INDENT_OUTPUT);
        // json是否允许属性名没有引号 ，默认是false
        //objectMapper.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
        // json是否允许属性名为单引号 ，默认是false
        //objectMapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
        // 遇到未知属性是否抛出异常 ，默认是抛出异常的
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        //
        JavaTimeModule timeModule = new JavaTimeModule();
//            timeModule.addDeserializer(LocalDateTime.class, LocalDateTimeDeserializer.INSTANCE);
//            objectMapper.registerModule(timeModule);
        timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
        timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        timeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));

        objectMapper.registerModule(timeModule);
        //return objectMapper;

    }


    public static <T> T parseObject(String jsonStr, Class<T> tClass) {
        try {
            return objectMapper.readValue(jsonStr, tClass);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static String toJSONString(Object obj) {
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> List<T> parseArray(String text, Class<T> clazz) {
        JavaType javaType = objectMapper.getTypeFactory().constructCollectionType(List.class, clazz);
        try {
            return objectMapper.readValue(text, javaType);
        } catch (IOException e) {
            log.error(e.getMessage());
            return null;
        }
    }

    public static <T> T toJavaObject(Object obj, Class<T> tClass) {
        return objectMapper.convertValue(obj, tClass);
    }

}
