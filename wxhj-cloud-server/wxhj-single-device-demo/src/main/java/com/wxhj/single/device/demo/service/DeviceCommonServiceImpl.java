package com.wxhj.single.device.demo.service;


import com.wxhj.common.device.api.DeviceCommonService;
import com.wxhj.common.device.dto.request.*;
import com.wxhj.common.device.dto.response.*;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.vo.VisitorInfoVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author daxiong
 * @date 2020/4/22 1:30 下午
 */
@Slf4j
@Service
public class DeviceCommonServiceImpl implements DeviceCommonService {
    @Override
    public DeviceRecordResponseDTO uploadDeviceRecord(DeviceRecordRequestDTO deviceRecordRequest) {
        log.info("设备记录上送，收到参数：" + deviceRecordRequest);
        // 自定义方法实现
        DeviceRecordResponseDTO deviceRecordResponseDTO = new DeviceRecordResponseDTO();

        log.info("设备记录上送，返回参数：" + deviceRecordResponseDTO);
        return deviceRecordResponseDTO;
    }

    @Override
    public DeviceHeartbeatResponseDTO deviceHeartbeat(DeviceHeartbeatRequestDTO deviceHearbeatRequest) {
        log.info("设备心跳接口，收到参数：" + deviceHearbeatRequest);
        // 自定义方法实现
        DeviceHeartbeatResponseDTO deviceHeartbeatResponseDTO = new DeviceHeartbeatResponseDTO();

        log.info("设备心跳，返回参数：" + deviceHeartbeatResponseDTO);
        return deviceHeartbeatResponseDTO;
    }

    @Override
    public FaceDataDownloadResponseDTO faceDataDownload(FaceDataDownloadRequestDTO faceDataDownloadRequest) {
        return null;
    }

    @Override
    public void faceDataDownloadExUpload(DeviceFaceExDTO deviceFaceExUpload) throws DeviceCommonException {

    }

    @Override
    public DeviceParameterResponseDTO deviceParameterDownload(DeviceParameterDownloadRequestDTO deviceParameterRequest) {
        return null;
    }

    @Override
    public DeviceAuthorizeResponseDTO deviceAuthorizeDownload(DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest) {
        return null;
    }

    @Override
    public void deviceVersionState(DeviceVersionStateRequestDTO deviceVersionState) {

    }

    @Override
    public List<VisitorInfoVO> visitorInfoPos(DeviceVisitorInfoPosRequestDTO visitorInfoPosRequest) {
        return null;
    }

    @Override
    public DeviceInitializeResponseDTO deviceInitialize(DeviceInitializeRequestDTO deviceInitializeRequest) {
        return null;
    }

    @Override
    public AccountBalanceResponseDTO accountBalance(DeviceCommonIdRequestDTO commonIdRequest) {
        return null;
    }

    @Override
    public DeviceMicroPayResponseDTO wechatQrOnline(WechatQrOnlineRequestDTO wechatQrOnlineRequest) {
        return null;
    }
}
