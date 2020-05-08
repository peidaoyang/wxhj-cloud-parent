package com.wxhj.common.device.api;

import com.wxhj.common.device.dto.request.*;
import com.wxhj.common.device.dto.response.*;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.vo.VisitorInfoVO;
import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

/**
 * @author daxiong
 * @date 2020/4/22 1:24 下午
 */
@Service
public interface DeviceCommonService {
    /**
     * 设备记录上送
     *
     * @param deviceRecordRequest
     * @return com.wxhj.common.device.dto.response.DeviceRecordResponseDTO
     * @author daxiong
     * @date 2020/4/22 2:36 下午
     */
    DeviceRecordResponseDTO uploadDeviceRecord(
            @Validated @RequestBody DeviceRecordRequestDTO deviceRecordRequest) throws DeviceCommonException;

    /**
     * 设备心跳
     *
     * @param deviceHearbeatRequest
     * @return com.wxhj.common.device.dto.response.DeviceHeartbeatResponseDTO
     * @author daxiong
     * @date 2020/4/22 2:43 下午
     */
    DeviceHeartbeatResponseDTO deviceHeartbeat(
            @Validated @RequestBody DeviceHeartbeatRequestDTO deviceHearbeatRequest) throws DeviceCommonException;

    /**
     * 人脸信息下发
     *
     * @param faceDataDownloadRequest
     * @return com.wxhj.common.device.dto.response.FaceDataDownloadResponseDTO
     * @author daxiong
     * @date 2020/4/22 4:45 下午
     */
    FaceDataDownloadResponseDTO faceDataDownload(
            @Validated @RequestBody FaceDataDownloadRequestDTO faceDataDownloadRequest) throws DeviceCommonException;

    /**
     * 人脸信息下发-异常上送
     *
     * @param deviceFaceExUpload
     * @return
     * @throws DeviceCommonException
     * @author daxiong
     * @date 2020/5/7 11:12 上午
     */
    void faceDataDownloadExUpload(
            @Validated @RequestBody DeviceFaceExDTO deviceFaceExUpload) throws DeviceCommonException;

    /**
     * 设备参数下载
     *
     * @param deviceParameterRequest
     * @return com.wxhj.common.device.dto.response.DeviceParameterResponseDTO
     * @author daxiong
     * @date 2020/4/22 2:48 下午
     */
    DeviceParameterResponseDTO deviceParameterDownload(
            @Validated @RequestBody DeviceParameterDownloadRequestDTO deviceParameterRequest) throws DeviceCommonException;

    /**
     * 授权信息获取
     *
     * @param deviceAuthorizeDownloadRequest
     * @return com.wxhj.common.device.dto.response.DeviceAuthorizeResponseDTO
     * @author daxiong
     * @date 2020/4/22 2:49 下午
     */
    DeviceAuthorizeResponseDTO deviceAuthorizeDownload(
            @Validated @RequestBody DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest) throws DeviceCommonException;

    /**
     * 设备版本下发状态确认
     *
     * @param deviceVersionState
     * @return void
     * @author daxiong
     * @date 2020/4/22 2:51 下午
     */
    void deviceVersionState(@Validated @RequestBody DeviceVersionStateRequestDTO deviceVersionState) throws DeviceCommonException;

    /**
     * 访客在线查询
     *
     * @param deviceVisitorInfoPosRequestDTO
     * @return java.util.List<com.wxhj.common.device.vo.VisitorInfoVO>
     * @author daxiong
     * @date 2020/4/22 3:18 下午
     */
    List<VisitorInfoVO> visitorInfoPos(
            @Validated @RequestBody DeviceVisitorInfoPosRequestDTO deviceVisitorInfoPosRequestDTO) throws DeviceCommonException;

    /**
     * 设备初始化
     *
     * @param deviceInitializeRequest
     * @return com.wxhj.common.device.dto.response.DeviceInitializeResponseDTO
     * @author daxiong
     * @date 2020/4/22 3:03 下午
     */
    DeviceInitializeResponseDTO deviceInitialize(
            @Validated @RequestBody DeviceInitializeRequestDTO deviceInitializeRequest) throws DeviceCommonException;

    /**
     * 账户余额查询
     *
     * @param commonIdRequest
     * @return com.wxhj.common.device.dto.response.AccountBalanceResponseDTO
     * @author daxiong
     * @date 2020/4/22 3:22 下午
     */
    AccountBalanceResponseDTO accountBalance(
            @RequestBody @Validated DeviceCommonIdRequestDTO commonIdRequest) throws DeviceCommonException;

    /**
     * 微信二维码在线认证
     *
     * @param wechatQrOnlineRequest
     * @return com.wxhj.cloud.component.dto.MicroPayResponseDTO
     * @author daxiong
     * @date 2020/4/22 3:06 下午
     */
    DeviceMicroPayResponseDTO wechatQrOnline(
            @Validated @RequestBody WechatQrOnlineRequestDTO wechatQrOnlineRequest) throws DeviceCommonException;
}
