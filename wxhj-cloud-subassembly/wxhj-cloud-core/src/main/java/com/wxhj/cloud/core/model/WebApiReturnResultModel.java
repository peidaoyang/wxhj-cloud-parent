/**
 * @fileName: WebApiReturnResultModel.java
 * @author: pjf
 * @date: 2019年10月11日 上午9:52:52
 */

package com.wxhj.cloud.core.model;

import com.alibaba.fastjson.JSON;
import com.wxhj.cloud.core.enums.WebResponseState;
import lombok.Data;

/**
 * @className WebApiReturnResultModel.java
 * @author pjf
 * @date 2019年10月11日 上午9:52:52
 */

@Data
public class WebApiReturnResultModel {
    private int code;
    private String msg;
    private Object data;

    public WebApiReturnResultModel() {
        super();
    }

    public WebApiReturnResultModel(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public Boolean resultSuccess() {
        return code == 200;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }


    public static WebApiReturnResultModel ofSuccess() {
        return ofSuccess(null);
    }

    /**
     * 定义常用静态方法
     */
    public static WebApiReturnResultModel ofSuccess(Object data) {
        return new WebApiReturnResultModel(WebResponseState.SUCCESS.getCode(),
                WebResponseState.SUCCESS.getStandardMessage(), data);
    }

    public static WebApiReturnResultModel ofSuccessJson(Object data) {
        String strJson = JSON.toJSONString(data);
        return new WebApiReturnResultModel(WebResponseState.SUCCESS.getCode(),
                WebResponseState.SUCCESS.getStandardMessage(), strJson);
    }

    public static WebApiReturnResultModel ofSuccessToJson(WebApiReturnResultModel webApiReturnResultModel) {

        String strJson =JSON.toJSONString( webApiReturnResultModel.getData());
        return new WebApiReturnResultModel(WebResponseState.SUCCESS.getCode(),
                WebResponseState.SUCCESS.getStandardMessage(), strJson);
    }

    public static WebApiReturnResultModel ofMessage(int code, String message) {
        return new WebApiReturnResultModel(code, message, null);
    }

    public static WebApiReturnResultModel ofStatus(WebResponseState status) {
        return new WebApiReturnResultModel(status.getCode(), status.getStandardMessage(), null);
    }

    public static WebApiReturnResultModel ofStatus(WebResponseState status, String msg) {
        return new WebApiReturnResultModel(status.getCode(), msg, null);
    }

}
