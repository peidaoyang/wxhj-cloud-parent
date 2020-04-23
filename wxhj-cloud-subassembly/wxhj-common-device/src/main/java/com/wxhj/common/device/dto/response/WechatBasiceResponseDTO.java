package com.wxhj.common.device.dto.response;

import lombok.Data;

@Data
public class WechatBasiceResponseDTO {

    protected String returnCode;
    protected String returnMsg;

    public boolean isSuccess() {

        return "SUCCESS".equals(returnCode) && "SUCCESS".equals(returnMsg);
    }

}
