package com.wxhj.cloud.component.dto;

import lombok.Data;

@Data
public class WechatBasiceResponseDTO {

    protected String returnCode;
    protected String returnMsg;

    public boolean isSuccess() {

        return "SUCCESS".equals(returnCode) && "OK".equals(returnMsg);
    }

}
