package com.wxhj.cloud.platform.controller.device;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.DeviceFaceExceptionClient;
import com.wxhj.cloud.feignClient.device.request.DeviceFaceExListRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.DeviceFaceExVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author daxiong
 * @date 2020/5/7 4:34 下午
 */
@RestController
@RequestMapping("/device/deviceFaceEx")
@Api(tags = "设备人脸下发异常上送接口")
public class DeviceFaceExceptionController {

    @Resource
    DeviceFaceExceptionClient deviceFaceExceptionClient;

    @ApiOperation(value = "人脸下发异常记录列表", response = DeviceFaceExVO.class)
    @PostMapping("/listDeviceFaceEx")
    public WebApiReturnResultModel listDeviceFaceEx(DeviceFaceExListRequestDTO deviceFaceExListRequest) {
        return deviceFaceExceptionClient.listDeviceFaceEx(deviceFaceExListRequest);
    }

    @ApiOperation("人脸下发异常记录忽略")
    @PostMapping("/ignoreDeviceFaceEx")
    public WebApiReturnResultModel ignoreDeviceFaceEx(CommonIdListRequestDTO commonIdListRequest) {
        return deviceFaceExceptionClient.ignoreDeviceFaceEx(commonIdListRequest);
    }
}
