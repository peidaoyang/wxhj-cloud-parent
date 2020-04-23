package com.wxhj.common.device.api;

import com.wxhj.common.device.dto.request.DeviceAuthorizeDownloadRequestDTO;
import com.wxhj.common.device.dto.request.DeviceCommonIdRequestDTO;
import com.wxhj.common.device.dto.request.DeviceHeartbeatRequestDTO;
import com.wxhj.common.device.dto.request.DeviceInitializeRequestDTO;
import com.wxhj.common.device.dto.request.DeviceParameterDownloadRequestDTO;
import com.wxhj.common.device.dto.request.DeviceRecordRequestDTO;
import com.wxhj.common.device.dto.request.DeviceVersionStateRequestDTO;
import com.wxhj.common.device.dto.request.FaceDataDownloadRequestDTO;
import com.wxhj.common.device.dto.request.VisitorInfoPosRequestDTO;
import com.wxhj.common.device.dto.request.WechatQrOnlineRequestDTO;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.DeviceApiReturnResultModel;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.function.Supplier;

/**
 * @author daxiong
 * @date 2020/4/21 6:10 下午
 */
@RestController
public class DefaultDeviceCommonController implements DeviceCommonControllerInterface {

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
            return DeviceApiReturnResultModel.ofMessage(ex.getCode(), ex.getMessage());
        }
    }

    @Override
    public DeviceApiReturnResultModel uploadDeviceRecord(@Validated @RequestBody DeviceRecordRequestDTO deviceRecordRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.uploadDeviceRecord(deviceRecordRequest)));
    }

    @Override
    public DeviceApiReturnResultModel deviceHeartbeat(
            @Validated @RequestBody DeviceHeartbeatRequestDTO deviceHearbeatRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceHeartbeat(deviceHearbeatRequest)));
    }

    @Override
    public DeviceApiReturnResultModel faceDataDownload(
            @Validated @RequestBody FaceDataDownloadRequestDTO faceDataDownloadRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.faceDataDownload(faceDataDownloadRequest)));
    }

    @Override
    public DeviceApiReturnResultModel deviceParameterDownload(
            @Validated @RequestBody DeviceParameterDownloadRequestDTO deviceParameterRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceParameterDownload(deviceParameterRequest)));
    }

    @Override
    public DeviceApiReturnResultModel deviceAuthorizeDownload(
            @Validated @RequestBody DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceAuthorizeDownload(deviceAuthorizeDownloadRequest)));
    }

    @Override
    public DeviceApiReturnResultModel deviceVersionState(
            @Validated @RequestBody DeviceVersionStateRequestDTO deviceVersionState) {
        deviceCommonService.deviceVersionState(deviceVersionState);
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccess());
    }

    @Override
    public DeviceApiReturnResultModel visitorInfoPos(
            @Validated @RequestBody VisitorInfoPosRequestDTO visitorInfoPosRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.visitorInfoPos(visitorInfoPosRequest)));
    }

    @Override
    public DeviceApiReturnResultModel deviceInitialize(
            @Validated @RequestBody DeviceInitializeRequestDTO deviceInitializeRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.deviceInitialize(deviceInitializeRequest)));
    }

    @Override
    public DeviceApiReturnResultModel accountBalance(
            @RequestBody @Validated DeviceCommonIdRequestDTO commonIdRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.accountBalance(commonIdRequest)));
    }

    @Override
    public DeviceApiReturnResultModel wechatQrOnline(
            @Validated @RequestBody WechatQrOnlineRequestDTO wechatQrOnlineRequest) {
        return exceptionCheck(() -> DeviceApiReturnResultModel.ofSuccessJson(deviceCommonService.wechatQrOnline(wechatQrOnlineRequest)));
    }

}
