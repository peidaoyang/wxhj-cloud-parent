package com.wxhj.common.device.model;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalTimeDeserializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalDateTimeSerializer;
import com.fasterxml.jackson.datatype.jsr310.ser.LocalTimeSerializer;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.function.Supplier;

/**
 * @author daxiong
 * @date 2020/4/21 9:18 下午
 */
//@Data
public class DeviceApiReturnResultModel {
    private int code;
    private String msg;
    private Object data;
    private static ObjectMapper objectMapper=new Supplier<ObjectMapper>() {
        @Override
        public ObjectMapper get() {
            ObjectMapper objectMapper = new ObjectMapper();
            // 遇到未知属性是否抛出异常 ，默认是抛出异常的
            objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
            //
            JavaTimeModule timeModule = new JavaTimeModule();
            timeModule.addSerializer(LocalDateTime.class, new LocalDateTimeSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            timeModule.addSerializer(LocalDate.class, new LocalDateSerializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            timeModule.addSerializer(LocalTime.class, new LocalTimeSerializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            timeModule.addDeserializer(LocalDateTime.class, new LocalDateTimeDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
            timeModule.addDeserializer(LocalDate.class, new LocalDateDeserializer(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
            timeModule.addDeserializer(LocalTime.class, new LocalTimeDeserializer(DateTimeFormatter.ofPattern("HH:mm:ss")));
            objectMapper.registerModule(timeModule);
            //
            return objectMapper;
        }
    }.get();
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public DeviceApiReturnResultModel() {
        super();
    }

    public DeviceApiReturnResultModel(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Boolean resultSuccess() {
        return code == 200;
    }

    private static String toJSONString(Object obj){
        try {
            return objectMapper.writeValueAsString(obj);
        } catch (JsonProcessingException e) {
            return "";
        }
    }
    @Override
    public String toString() {
        return
                toJSONString(this);
    }


    public static DeviceApiReturnResultModel ofSuccess() {
        return ofSuccess(null);
    }

    /**
     * 定义常用静态方法
     */
    public static DeviceApiReturnResultModel ofSuccess(Object data) {
        return new DeviceApiReturnResultModel(DeviceResponseState.SUCCESS.getCode(),
                DeviceResponseState.SUCCESS.getStandardMessage(), data);
    }

    public static DeviceApiReturnResultModel ofSuccessJson(Object data) {
        String strJson = toJSONString(data);
        return new DeviceApiReturnResultModel(DeviceResponseState.SUCCESS.getCode(),
                DeviceResponseState.SUCCESS.getStandardMessage(), strJson);
    }

    public static DeviceApiReturnResultModel ofSuccessToJson(DeviceApiReturnResultModel DeviceApiReturnResultModel) {

        String strJson =toJSONString( DeviceApiReturnResultModel.getData());
        return new DeviceApiReturnResultModel(DeviceResponseState.SUCCESS.getCode(),
                DeviceResponseState.SUCCESS.getStandardMessage(), strJson);
    }

    public static DeviceApiReturnResultModel ofMessage(int code, String msg) {
        return new DeviceApiReturnResultModel(code, msg, null);
    }

    public static DeviceApiReturnResultModel ofStatus(DeviceResponseState status) {
        return new DeviceApiReturnResultModel(status.getCode(), status.getStandardMessage(), null);
    }

    public static DeviceApiReturnResultModel ofStatus(DeviceResponseState status, String msg) {
        return new DeviceApiReturnResultModel(status.getCode(), msg, null);
    }


}
