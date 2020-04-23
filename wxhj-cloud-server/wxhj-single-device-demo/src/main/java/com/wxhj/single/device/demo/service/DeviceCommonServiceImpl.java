package com.wxhj.single.device.demo.service;

import com.wxhj.common.device.api.DeviceCommonService;
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
import com.wxhj.common.device.dto.response.AccountBalanceResponseDTO;
import com.wxhj.common.device.dto.response.DeviceAuthorizeResponseDTO;
import com.wxhj.common.device.dto.response.DeviceHeartbeatResponseDTO;
import com.wxhj.common.device.dto.response.DeviceInitializeResponseDTO;
import com.wxhj.common.device.dto.response.DeviceParameterResponseDTO;
import com.wxhj.common.device.dto.response.DeviceRecordResponseDTO;
import com.wxhj.common.device.dto.response.FaceDataDownloadResponseDTO;
import com.wxhj.common.device.dto.response.MicroPayResponseDTO;
import com.wxhj.common.device.vo.FaceChangeRecRedisVO;
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
    public List<VisitorInfoVO> visitorInfoPos(VisitorInfoPosRequestDTO visitorInfoPosRequest) {
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
    public MicroPayResponseDTO wechatQrOnline(WechatQrOnlineRequestDTO wechatQrOnlineRequest) {
        return null;
    }
}
