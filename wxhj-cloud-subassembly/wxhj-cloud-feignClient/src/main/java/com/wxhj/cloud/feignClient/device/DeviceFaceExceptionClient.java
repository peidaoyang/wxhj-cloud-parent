package com.wxhj.cloud.feignClient.device;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceAuthorizeClientFallBack;
import com.wxhj.cloud.feignClient.device.request.DeviceFaceExListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020/5/7 4:35 下午
 */
@Component
@FeignClient(name = "deviceServer", fallback= DeviceAuthorizeClientFallBack.class)
public interface DeviceFaceExceptionClient {

    @PostMapping("/deviceFaceEx/listDeviceFaceEx")
    WebApiReturnResultModel listDeviceFaceEx(@RequestBody @Validated DeviceFaceExListRequestDTO deviceFaceExListRequest);

    @PostMapping("/deviceFaceEx/ignoreDeviceFaceEx")
    WebApiReturnResultModel ignoreDeviceFaceEx(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest);
}
