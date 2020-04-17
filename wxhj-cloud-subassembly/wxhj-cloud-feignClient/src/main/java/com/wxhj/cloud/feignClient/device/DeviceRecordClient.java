package com.wxhj.cloud.feignClient.device;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.DeviceRecordClientFallBack;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "deviceServer",fallback= DeviceRecordClientFallBack.class)
public interface DeviceRecordClient {
    @PostMapping("/deviceRecord/deviceInWeek")
    WebApiReturnResultModel deviceInWeek(@RequestBody CommonIdRequestDTO commonIdRequest);
}
