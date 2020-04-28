package com.wxhj.common.device.api;

import com.wxhj.common.device.dto.request.*;
import com.wxhj.common.device.dto.response.*;
import com.wxhj.common.device.model.DeviceApiReturnResultModel;
import com.wxhj.common.device.vo.FaceChangeRecRedisVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @author daxiong
 * @date 2020/4/21 6:10 下午
 */
@Api(tags = "设备通信控制器")
@RequestMapping("/deviceComm")
public interface DeviceCommonControllerInterface {

    @ApiOperation(value = "设备记录上送", response = DeviceRecordResponseDTO.class)
    @PostMapping("/uploadDeviceRecord")
    DeviceApiReturnResultModel uploadDeviceRecord(@Validated @RequestBody DeviceRecordRequestDTO deviceRecordRequest);

    @ApiOperation(value = "设备心跳", response = DeviceHeartbeatResponseDTO.class)
    @PostMapping("/deviceHeartbeat")
    DeviceApiReturnResultModel deviceHeartbeat(
            @Validated @RequestBody DeviceHeartbeatRequestDTO deviceHearbeatRequest);

    @ApiOperation(value = "人脸信息下发", response = FaceChangeRecRedisVO.class, responseContainer = "List")
    @PostMapping("/faceDataDownload")
    DeviceApiReturnResultModel faceDataDownload(
            @Validated @RequestBody FaceDataDownloadRequestDTO faceDataDownloadRequest);

    @ApiOperation(value = "设备参数下载", response = DeviceParameterResponseDTO.class)
    @PostMapping("/deviceParameterDownload")
    DeviceApiReturnResultModel deviceParameterDownload(
            @Validated @RequestBody DeviceParameterDownloadRequestDTO deviceParameterRequest);

    @ApiOperation(value = "授权信息获取", response = DeviceAuthorizeResponseDTO.class)
    @PostMapping("/deviceAuthorizeDownload")
    DeviceApiReturnResultModel deviceAuthorizeDownload(
            @Validated @RequestBody DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest);

    @ApiOperation("设备版本下发状态确认")
    @PostMapping("/deviceVersionState")
    DeviceApiReturnResultModel deviceVersionState(
            @Validated @RequestBody DeviceVersionStateRequestDTO deviceVersionState);

    @ApiOperation("访客在线查询")
    @PostMapping("/visitorInfoPos")
    DeviceApiReturnResultModel visitorInfoPos(
            @Validated @RequestBody DeviceVisitorInfoPosRequestDTO visitorInfoPosRequest);

    @ApiOperation(value = "设备初始化", response = DeviceInitializeResponseDTO.class)
    @PostMapping("/deviceInitialize")
    DeviceApiReturnResultModel deviceInitialize(
            @Validated @RequestBody DeviceInitializeRequestDTO deviceInitializeRequest);

    @ApiOperation(value = "账户余额查询", response = AccountBalanceResponseDTO.class)
    @PostMapping("/accountBalance")
    DeviceApiReturnResultModel accountBalance(
            @RequestBody @Validated DeviceCommonIdRequestDTO commonIdRequest);

    @ApiOperation(value = "微信二维码在线认证", response = DeviceMicroPayResponseDTO.class)
    @PostMapping("/wechatQrOnline")
    DeviceApiReturnResultModel wechatQrOnline(
            @Validated @RequestBody WechatQrOnlineRequestDTO wechatQrOnlineRequest);

}
