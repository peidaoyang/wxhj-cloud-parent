package com.wxhj.cloud.redis.annotation.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


/**
 * @author daxiong
 * @date 2020/4/24 10:16 上午
 */
@Data
@Builder
public class MethodInfo {
    /**
     * 主键id
     */
    private String id;
    /**
     * 服务标志
     */
    private String serverName;
    /**
     * 方法描述
     */
    private String value;
    /**
     * 方法名称
     */
    private String name;
    /**
     * 请求body的json
     */
    private String request;
    /**
     * 请求时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime requestTime;
    /**
     * 返回body的json
     */
    private String response;
    /**
     * 响应时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private LocalDateTime responseTime;
    /**
     * 用户名
     */
    private String username;
}
