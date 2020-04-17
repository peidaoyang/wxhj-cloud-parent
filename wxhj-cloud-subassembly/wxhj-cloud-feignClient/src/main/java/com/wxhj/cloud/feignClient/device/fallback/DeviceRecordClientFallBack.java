package com.wxhj.cloud.feignClient.device.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceRecordClient;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class DeviceRecordClientFallBack implements DeviceRecordClient {
    @Override
    public WebApiReturnResultModel deviceInWeek(CommonIdRequestDTO commonIdRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
