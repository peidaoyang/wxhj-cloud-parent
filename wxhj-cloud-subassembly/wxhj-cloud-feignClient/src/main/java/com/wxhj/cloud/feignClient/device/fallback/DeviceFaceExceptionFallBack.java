package com.wxhj.cloud.feignClient.device.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceFaceExceptionClient;
import com.wxhj.cloud.feignClient.device.request.DeviceFaceExListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020/5/7 4:36 下午
 */
@Component
public class DeviceFaceExceptionFallBack implements DeviceFaceExceptionClient {
    @Override
    public WebApiReturnResultModel listDeviceFaceEx(DeviceFaceExListRequestDTO deviceFaceExListRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel ignoreDeviceFaceEx(CommonIdListRequestDTO commonIdListRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
