package com.wxhj.cloud.gateway.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.elasticsearch.annotation.ESDocument;
import com.wxhj.cloud.elasticsearch.base.ElasticSearchBaseEntity;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

/**
 * @author daxiong
 * @date 2020/4/26 4:09 下午
 */
@Data
@ESDocument(index = "controller-log")
public class LogAnnotationDO extends ElasticSearchBaseEntity {
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
    private Date requestTime;
    /**
     * 返回body的json
     */
    private String response;
    /**
     * 响应时间
     */
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    private Date responseTime;
    /**
     * 用户名
     */
    private String username;
}
