package com.wxhj.cloud.core.exception;

import com.wxhj.cloud.core.enums.WebResponseState;

/**
 * @author daxiong
 * @date 2020/5/15 1:34 下午
 */
public class WuXiHuaJieCommonException extends RuntimeException {
    private int code;
    private String msg;
    private WebResponseState webResponseState;

    public WuXiHuaJieCommonException(WebResponseState webResponseState) {
        this.webResponseState = webResponseState;
        this.code = webResponseState.getCode();
        this.msg = webResponseState.getStandardMessage();
    }
    public WuXiHuaJieCommonException(WebResponseState webResponseState, String msg) {
        this.webResponseState = webResponseState;
        this.code = webResponseState.getCode();
        this.msg = msg;
    }
    public WuXiHuaJieCommonException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
