package com.wxhj.common.device.model;

import com.alibaba.fastjson.JSON;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/4/21 9:18 下午
 */
@Data
public class DeviceApiReturnResultModel {
    private int code;
    private String msg;
    private Object data;

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

    @Override
    public String toString() {
        return JSON.toJSONString(this);
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
        String strJson = JSON.toJSONString(data);
        return new DeviceApiReturnResultModel(DeviceResponseState.SUCCESS.getCode(),
                DeviceResponseState.SUCCESS.getStandardMessage(), strJson);
    }

    public static DeviceApiReturnResultModel ofSuccessToJson(DeviceApiReturnResultModel DeviceApiReturnResultModel) {

        String strJson =JSON.toJSONString( DeviceApiReturnResultModel.getData());
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
