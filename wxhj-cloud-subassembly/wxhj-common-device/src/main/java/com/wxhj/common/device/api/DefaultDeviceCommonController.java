package com.wxhj.common.device.api;

import com.wxhj.common.device.dto.request.*;
import com.wxhj.common.device.dto.response.*;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.DeviceApiReturnResultModel;

import com.wxhj.common.device.vo.FaceChangeRecVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * @author daxiong
 * @date 2020/4/21 6:10 下午
 */
@RequestMapping("/deviceComm")
public class DefaultDeviceCommonController {

    @Resource
    DeviceCommonService deviceCommonService;

    /**
     * 检查传入的方法是否有异常
     * @author daxiong
     * @date 2020/4/22 6:28 下午
     * @param supplier
     * @return com.wxhj.common.device.model.DeviceApiReturnResultModel
     */
    private DeviceApiReturnResultModel exceptionCheck(Supplier<DeviceApiReturnResultModel> supplier) {
        try {
            return supplier.get();
        } catch (DeviceCommonException ex) {
            return DeviceApiReturnResultModel.ofMessage(ex.getCode(), ex.getMsg());
        }
    }

    @ApiOperation(value = "设备记录上送", response = DeviceRecordResponseDTO.class)
    @PostMapping("/uploadDeviceRecord")
    public DeviceApiReturnResultModel uploadDeviceRecord(@Validated @RequestBody DeviceRecordRequestDTO deviceRecordRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.uploadDeviceRecord(deviceRecordRequest)));
    }

    @ApiOperation(value = "设备心跳", response = DeviceHeartbeatResponseDTO.class)
    @PostMapping("/deviceHeartbeat")
    public DeviceApiReturnResultModel deviceHeartbeat(
            @Validated @RequestBody DeviceHeartbeatRequestDTO deviceHearbeatRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceHeartbeat(deviceHearbeatRequest)));
    }

    @ApiOperation(value = "人脸信息下发", response = FaceChangeRecVO.class, responseContainer = "List")
    @PostMapping("/faceDataDownload")
    public DeviceApiReturnResultModel faceDataDownload(
            @Validated @RequestBody FaceDataDownloadRequestDTO faceDataDownloadRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.faceDataDownload(faceDataDownloadRequest)));
    }

    @ApiOperation(value = "设备参数下载", response = DeviceParameterResponseDTO.class)
    @PostMapping("/deviceParameterDownload")
    public DeviceApiReturnResultModel deviceParameterDownload(
            @Validated @RequestBody DeviceParameterDownloadRequestDTO deviceParameterRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceParameterDownload(deviceParameterRequest)));
    }

    @ApiOperation(value = "授权信息获取", response = DeviceAuthorizeResponseDTO.class)
    @PostMapping("/deviceAuthorizeDownload")
    public DeviceApiReturnResultModel deviceAuthorizeDownload(
            @Validated @RequestBody DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceAuthorizeDownload(deviceAuthorizeDownloadRequest)));
    }

    @ApiOperation("设备版本下发状态确认")
    @PostMapping("/deviceVersionState")
    public DeviceApiReturnResultModel deviceVersionState(
            @Validated @RequestBody DeviceVersionStateRequestDTO deviceVersionState) {
        deviceCommonService.deviceVersionState(deviceVersionState);
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccess());
    }

    @ApiOperation("访客在线查询")
    @PostMapping("/visitorInfoPos")
    public DeviceApiReturnResultModel visitorInfoPos(
            @Validated @RequestBody DeviceVisitorInfoPosRequestDTO visitorInfoPosRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.visitorInfoPos(visitorInfoPosRequest)));
    }

    @ApiOperation(value = "设备初始化", response = DeviceInitializeResponseDTO.class)
    @PostMapping("/deviceInitialize")
    public DeviceApiReturnResultModel deviceInitialize(
            @Validated @RequestBody DeviceInitializeRequestDTO deviceInitializeRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceInitialize(deviceInitializeRequest)));
    }

    @ApiOperation(value = "账户余额查询", response = AccountBalanceResponseDTO.class)
    @PostMapping("/accountBalance")
    public DeviceApiReturnResultModel accountBalance(
            @RequestBody @Validated DeviceCommonIdRequestDTO commonIdRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.accountBalance(commonIdRequest)));
    }

    @ApiOperation(value = "微信二维码在线认证", response = DeviceMicroPayResponseDTO.class)
    @PostMapping("/wechatQrOnline")
    public DeviceApiReturnResultModel wechatQrOnline(
            @Validated @RequestBody WechatQrOnlineRequestDTO wechatQrOnlineRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.wechatQrOnline(wechatQrOnlineRequest)));
    }

}