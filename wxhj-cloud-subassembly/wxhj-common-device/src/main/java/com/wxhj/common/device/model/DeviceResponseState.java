package com.wxhj.common.device.model;

import lombok.Getter;

/**
 * 错误枚举
 *
 * @className WebResponseState.java
 * @author pjf
 * @date 2019年10月11日 上午9:53:27
 */
@Getter
public enum DeviceResponseState {
    /**
     * 成功返回
     */
    SUCCESS(200, "OK"),
    /**
     * 错误返回
     */
    BAD_REQUEST(400, "Bad Request"),
    /**
     * 地址未找到
     */
    NOT_FOUND(404, "page not found"),
    /**
     * 服务错误及未定义的错误
     */
    INTERNAL_SERVER_ERROR(500, "server internal error"),

    // 数据过期
    DATA_EXPIRE(40023, "data expire"),

    // 数据存在
    DATA_EXIST(40028, "data exist"),

    /**
     * 其他已捕获的错误
     */
    OTHER_ERROR(40001, "other error"),

    //微信返回异常
    WECHAT_ERROR(40100, "wechat error"),



    ;
    private int code;
    private String standardMessage;

    DeviceResponseState(int code, String standardMessage) {
        this.code = code;
        this.standardMessage = standardMessage;
    }

}
