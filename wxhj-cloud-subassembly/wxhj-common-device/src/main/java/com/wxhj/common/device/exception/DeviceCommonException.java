package com.wxhj.common.device.exception;

import com.wxhj.common.device.model.DeviceResponseState;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/4/22 4:55 下午
 */
@Data
public class DeviceCommonException extends RuntimeException {
    private int code;
    private String msg;
    private DeviceResponseState deviceResponseState;

    public DeviceCommonException(DeviceResponseState deviceResponseState) {
        this.deviceResponseState = deviceResponseState;
        this.code = deviceResponseState.getCode();
        this.msg = deviceResponseState.getStandardMessage();
    }
    public DeviceCommonException(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }
}
