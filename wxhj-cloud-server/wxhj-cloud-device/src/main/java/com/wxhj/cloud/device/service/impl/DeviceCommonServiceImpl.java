package com.wxhj.cloud.device.service.impl;

import com.google.common.base.Strings;
import com.wxhj.cloud.component.dto.MicroPayRequestDTO;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.enums.DeviceRecordStateEnum;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.DeviceStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.device.bo.DeviceGlobalParameterScreenBO;
import com.wxhj.cloud.device.config.DeviceServiceConfig;
import com.wxhj.cloud.device.domain.DeviceAuthorizeDO;
import com.wxhj.cloud.device.domain.DeviceGlobalParameterDO;
import com.wxhj.cloud.device.domain.DeviceInfoDO;
import com.wxhj.cloud.device.domain.DeviceParameterDO;
import com.wxhj.cloud.device.domain.DeviceRecordDO;
import com.wxhj.cloud.device.domain.DeviceResourceDO;
import com.wxhj.cloud.device.domain.DeviceStateDO;
import com.wxhj.cloud.device.domain.view.ViewDeviceResourceDO;
import com.wxhj.cloud.device.service.DeviceAuthorizeService;
import com.wxhj.cloud.device.service.DeviceGlobalParameterService;
import com.wxhj.cloud.device.service.DeviceInfoService;
import com.wxhj.cloud.device.service.DeviceParameterService;
import com.wxhj.cloud.device.service.DeviceRecordService;
import com.wxhj.cloud.device.service.DeviceResourceService;
import com.wxhj.cloud.device.service.DeviceStateService;
import com.wxhj.cloud.device.service.ViewDeviceResourceService;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizePayInfoClient;
import com.wxhj.cloud.feignClient.platform.bo.OrganizePayInfoBO;
import com.wxhj.cloud.redis.domain.FaceChangeRecRedisDO;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.wechat.WeChatPayConfig;
import com.wxhj.common.device.api.DeviceCommonService;
import com.wxhj.common.device.bo.DeviceGlobalParameterBO;
import com.wxhj.common.device.bo.ViewDeviceResourceBO;
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
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.DeviceResponseState;
import com.wxhj.common.device.vo.FaceChangeRecRedisVO;
import com.wxhj.common.device.vo.VisitorInfoVO;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/4/22 4:30 下午
 */
@Service
public class DeviceCommonServiceImpl implements DeviceCommonService {

    @Resource
    FileStorageService fileStorageService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    DeviceRecordService deviceRecordService;
    @Resource
    DeviceStateService deviceStateService;
    @Resource
    DeviceParameterService deviceParameterService;
    @Resource
    DeviceGlobalParameterService deviceGlobalParameterService;
    @Resource
    RedisTemplate redisTemplate;
    @Resource
    DeviceAuthorizeService deviceAuthorizeService;
    @Resource
    DeviceServiceConfig deviceServiceConfig;
    @Resource
    RocketMqProducer rocketMqProducer;
    @Resource
    DeviceResourceService deviceResourceService;
    @Resource
    AccountClient accountClient;
    @Resource
    OrganizePayInfoClient organizePayInfoClient;
    @Resource
    PaymentService paymentService;
    @Resource
    ViewDeviceResourceService viewDeviceResourceService;
    @Resource
    VisitorInfoClient visitorInfoClient;
    @Resource
    DeviceInfoService deviceInfoService;
    static Double defMinIndex = (double) 0;
    static Double defMaxIndex = Double.MAX_VALUE;
    static Long dataExpireTime = 7 * 86400L;

    @Override
    public DeviceRecordResponseDTO uploadDeviceRecord(DeviceRecordRequestDTO deviceRecordRequest) throws DeviceCommonException {
        DeviceRecordDO deviceRecord = dozerBeanMapper.map(deviceRecordRequest, DeviceRecordDO.class);
        deviceRecord.initialization();
        if (deviceRecordRequest.getRecordTimeStamp() < (new Date()).getTime() / 1000 - dataExpireTime) {
            throw new DeviceCommonException(DeviceResponseState.DATA_EXPIRE);
        }
        deviceRecordService.insert(deviceRecord);
        DeviceRecordResponseDTO deviceRecordResponse = dozerBeanMapper.map(deviceRecordRequest,
                DeviceRecordResponseDTO.class);

        if (!DeviceRecordStateEnum.NOT_DISTRIBUTE.getState().equals(deviceRecord.getDataState())) {
            return deviceRecordResponse;
        }
        String mqTopic = deviceServiceConfig.getRecordTopicMap().get(deviceRecord.getRecordType());
        if (!Strings.isNullOrEmpty(mqTopic)) {
            DeviceRecordDO deviceRecordTemp = new DeviceRecordDO();
            // deviceRecordTemp.setId(deviceRecord.getId());
            if (rocketMqProducer.sendMessage(mqTopic, deviceRecord.getData())) {
                deviceRecordTemp.setDataState(DeviceRecordStateEnum.IS_DISTRIBUTE.getState());
            } else {
                deviceRecordTemp.setDataState(DeviceRecordStateEnum.ERR_DISTRIBUTE.getState());
            }
            deviceRecordService.updateByDeviceIdAndNoAndStamp(deviceRecordTemp, deviceRecord.getDeviceId(),
                    deviceRecord.getSerialNumber(), deviceRecord.getRecordTimeStamp());
        }
        return deviceRecordResponse;
    }

    @Override
    public DeviceHeartbeatResponseDTO deviceHeartbeat(DeviceHeartbeatRequestDTO deviceHearbeatRequest) {
        //
        DeviceStateDO deviceState = dozerBeanMapper.map(deviceHearbeatRequest, DeviceStateDO.class);
        deviceState.setLastTime(new Date());
        // 设备信息数据入库
        deviceStateService.replace(deviceState);
        //
        DeviceParameterDO deviceParameter = deviceParameterService.selectByDeviceId(deviceState.getDeviceId());
        //
        List<ViewDeviceResourceDO> viewDeviceResource = viewDeviceResourceService
                .listByPosId(deviceState.getDeviceId());

        DeviceHeartbeatResponseDTO deviceHearbeatResponse = new DeviceHeartbeatResponseDTO();
        deviceHearbeatResponse.setDeviceId(deviceHearbeatRequest.getDeviceId());
        if (deviceParameter == null) {
            deviceHearbeatResponse.setParameterVersion(0L);
            deviceHearbeatResponse.setDeviceName("");
        } else {
            deviceHearbeatResponse.setParameterVersion(deviceParameter.getParameterVersion());
            deviceHearbeatResponse.setDeviceName(deviceParameter.getDeviceName());
        }
        Pair<Long, Long> redisFaceChange = redisFaceChange(deviceHearbeatRequest.getSceneId());
        deviceHearbeatResponse.setFaceMinIndex(redisFaceChange.getLeft());
        deviceHearbeatResponse.setFaceMaxIndex(redisFaceChange.getRight());
        // 全局参数判断
        DeviceGlobalParameterScreenBO deviceGlobalParameterScreen = dozerBeanMapper.map(deviceState,
                DeviceGlobalParameterScreenBO.class);
        //
        List<DeviceGlobalParameterDO> deviceGlobalParameterList1 = deviceGlobalParameterService
                .selectByDeviceState(deviceGlobalParameterScreen);

        List<DeviceGlobalParameterBO> deviceGlobalParameterList2 = deviceGlobalParameterList1.stream().map(q -> {
            DeviceGlobalParameterBO deviceGlobalParameterTemp = dozerBeanMapper.map(q, DeviceGlobalParameterBO.class);
            deviceGlobalParameterTemp.setStartDatetimeStamp(q.getStartDatetime().getTime() / 1000);
            deviceGlobalParameterTemp.setEndDatetimeStamp(q.getEndDatetime().getTime() / 1000);
            deviceGlobalParameterTemp.setParameterFileUrl1(q.getParameterFileUrl());
            return deviceGlobalParameterTemp;
        }).collect(Collectors.toList());

        deviceHearbeatResponse.setDeviceGlobalParameterList(deviceGlobalParameterList2);
        // 以下为资源
        deviceHearbeatResponse.setDeviceResourceList(viewDeviceResource.stream().map(q -> {
            ViewDeviceResourceBO viewDeviceResourceTemp = dozerBeanMapper.map(q, ViewDeviceResourceBO.class);
            viewDeviceResourceTemp.setFileUrl1(fileStorageService.generateFileUrl(q.getFileName()));
            return viewDeviceResourceTemp;
        }).collect(Collectors.toList()));
        return deviceHearbeatResponse;
    }

    private Pair<Long, Long> redisFaceChange(String sceneId) {
        String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(sceneId);
        if (!redisTemplate.hasKey(redisKey)) {
            return MutablePair.of(0L, 0L);
        } else {
            Set<FaceChangeRecRedisDO> faceChangeRecSet = (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate
                    .opsForZSet().rangeByScore(redisKey, defMinIndex, defMaxIndex, 0, 1);
            Long min = faceChangeRecSet.iterator().next().getCurrentIndex();

            faceChangeRecSet = (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate.opsForZSet()
                    .reverseRangeByScore(redisKey, defMinIndex, defMaxIndex, 0, 1);
            Long max = faceChangeRecSet.iterator().next().getCurrentIndex();
            return MutablePair.of(min, max);
        }
    }

    @Override
    public FaceDataDownloadResponseDTO faceDataDownload(FaceDataDownloadRequestDTO faceDataDownloadRequest) {
        Pair<Long, Long> redisFaceChange = redisFaceChange(faceDataDownloadRequest.getSceneId());
        List<FaceChangeRecRedisVO> faceDataList = new ArrayList<>();
        FaceDataDownloadResponseDTO faceDataDownloadResponse = dozerBeanMapper.map(faceDataDownloadRequest,
                FaceDataDownloadResponseDTO.class);
        faceDataDownloadResponse.setFaceMinIndex(redisFaceChange.getLeft());
        faceDataDownloadResponse.setFaceMaxIndex(redisFaceChange.getRight());
        String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(faceDataDownloadRequest.getSceneId());
        if (redisTemplate.hasKey(redisKey)) {
            Set<FaceChangeRecRedisDO> faceChangeRecSet = (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate
                    .opsForZSet().rangeByScore(redisKey, faceDataDownloadRequest.getStartIndex(),
                            faceDataDownloadRequest.getEndIndex());
            //
            faceDataList = faceChangeRecSet.stream().map(q -> {
                FaceChangeRecRedisVO faceChangeRecRedisTemp = dozerBeanMapper.map(q, FaceChangeRecRedisVO.class);
                faceChangeRecRedisTemp.setImageUrl1(fileStorageService.generateFileUrl(q.getImageUrl()));
                return faceChangeRecRedisTemp;
            }).collect(Collectors.toList());

            //
            // faceDataList = new ArrayList<>(faceChangeRecSet);
        }
        faceDataDownloadResponse.setFaceDataList(faceDataList);
        return faceDataDownloadResponse;

    }

    @Override
    public DeviceParameterResponseDTO deviceParameterDownload(DeviceParameterDownloadRequestDTO deviceParameterRequest) {
        DeviceParameterDO deviceParameter = deviceParameterService
                .selectByDeviceId(deviceParameterRequest.getDeviceId());

        DeviceParameterResponseDTO deviceParameterResponse = null;
        if (deviceParameter == null) {
            deviceParameter = dozerBeanMapper.map(deviceParameterRequest, DeviceParameterDO.class);
            deviceParameterService.insert(deviceParameter);

            deviceParameterResponse = dozerBeanMapper.map(deviceParameterRequest,
                    DeviceParameterResponseDTO.class);
            deviceParameterResponse.setParameterUrl1(fileStorageService.generateFileUrl(deviceParameter.getParameterUrl()));
            return deviceParameterResponse;
        }
        if (deviceParameter.getParameterVersion() < deviceParameterRequest.getParameterVersion()) {
            deviceParameter = dozerBeanMapper.map(deviceParameterRequest, DeviceParameterDO.class);
            deviceParameterService.update(deviceParameter);

            deviceParameterResponse = dozerBeanMapper.map(deviceParameterRequest,
                    DeviceParameterResponseDTO.class);
            deviceParameterResponse.setParameterUrl1(fileStorageService.generateFileUrl(deviceParameter.getParameterUrl()));
            return deviceParameterResponse;
        } else {

            deviceParameterResponse = dozerBeanMapper.map(deviceParameter,
                    DeviceParameterResponseDTO.class);
            deviceParameterResponse.setParameterUrl1(fileStorageService.generateFileUrl(deviceParameter.getParameterUrl()));
            return deviceParameterResponse;
        }
    }

    @Override
    public DeviceAuthorizeResponseDTO deviceAuthorizeDownload(DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest) throws DeviceCommonException {
        DeviceAuthorizeDO deviceAuthorize = deviceAuthorizeService.selectByDeviceIdAndType(
                deviceAuthorizeDownloadRequest.getDeviceId(), deviceAuthorizeDownloadRequest.getAuthorizeType());
        if (deviceAuthorize == null) {
            deviceAuthorize = deviceAuthorizeService
                    .selectByDeviceIdIsNullAndType(deviceAuthorizeDownloadRequest.getAuthorizeType());
            if (deviceAuthorize == null) {
                throw new DeviceCommonException(DeviceResponseState.DATA_EXIST);
            }
            deviceAuthorize.setDeviceId(deviceAuthorizeDownloadRequest.getDeviceId());
            deviceAuthorizeService.update(deviceAuthorize);
        }
        DeviceAuthorizeResponseDTO deviceAuthorizeResponse = dozerBeanMapper.map(deviceAuthorize,
                DeviceAuthorizeResponseDTO.class);
        return deviceAuthorizeResponse;

    }

    @Override
    public void deviceVersionState(DeviceVersionStateRequestDTO deviceVersionState) {
        DeviceResourceDO deviceResourceDO = new DeviceResourceDO();
        deviceResourceDO.setPosId(deviceVersionState.getDeviceId());
        deviceResourceDO.setId(deviceVersionState.getId());
        deviceResourceDO.setSentState(1);
        deviceResourceService.update(deviceResourceDO);
        return;
    }

    @Override
    public List<VisitorInfoVO> visitorInfoPos(VisitorInfoPosRequestDTO visitorInfoPosRequest) {
        WebApiReturnResultModel webApiReturnResultModel = visitorInfoClient.visitorInfoPos(visitorInfoPosRequest);
        List<VisitorInfoVO> visitorInfos = null;
        try {
            if (WebResponseState.SUCCESS.getCode() == webApiReturnResultModel.getCode()) {
                visitorInfos = FeignUtil.formatArrayClass(webApiReturnResultModel, VisitorInfoVO.class);
            } else {
                throw new DeviceCommonException(webApiReturnResultModel.getCode(), webApiReturnResultModel.getMsg());
            }
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            wuXiHuaJieFeignError.printStackTrace();
        }
        return visitorInfos;
    }

    @Override
    public DeviceInitializeResponseDTO deviceInitialize(DeviceInitializeRequestDTO deviceInitializeRequest) {
        DeviceInitializeResponseDTO deviceInitializeResponse = dozerBeanMapper.map(deviceInitializeRequest,
                DeviceInitializeResponseDTO.class);
        if (!Strings.isNullOrEmpty(deviceInitializeRequest.getId())
                && deviceInitializeRequest.getId().indexOf(DeviceStaticClass.PREFIX_DEVICE) >= 0) {
            return deviceInitializeResponse;
        }
        String deviceId = deviceInitializeResponse.getDeviceId();
        DeviceInfoDO deviceInfo = deviceInfoService.selectByDeviceId(deviceInitializeResponse.getDeviceId());
        if (deviceInfo == null) {
            deviceInfo = dozerBeanMapper.map(deviceInitializeRequest, DeviceInfoDO.class);
            String insert = deviceInfoService.insertCascade(deviceInfo);
            deviceInitializeResponse.setDeviceId(insert);
        } else {
            deviceInitializeResponse.setDeviceId(deviceInfo.getId());
        }
        return deviceInitializeResponse;

    }

    @Override
    public AccountBalanceResponseDTO accountBalance(DeviceCommonIdRequestDTO deviceCommonIdRequestDTO) {
        CommonIdRequestDTO commonIdRequest = new CommonIdRequestDTO();
        dozerBeanMapper.map(deviceCommonIdRequestDTO, commonIdRequest);

        WebApiReturnResultModel webApiReturnResultModel = accountClient.accountBalance(commonIdRequest);
        AccountBalanceResponseDTO accountBalanceResponseDTO = null;
        try {
            if (webApiReturnResultModel.getCode() == WebResponseState.SUCCESS.getCode()) {
                accountBalanceResponseDTO = FeignUtil.formatClass(webApiReturnResultModel, AccountBalanceResponseDTO.class);
            } else {
                throw new DeviceCommonException(webApiReturnResultModel.getCode(), webApiReturnResultModel.getMsg());
            }
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            wuXiHuaJieFeignError.printStackTrace();
        }
        return accountBalanceResponseDTO;
    }

    @Override
    public MicroPayResponseDTO wechatQrOnline(WechatQrOnlineRequestDTO wechatQrOnlineRequest) throws DeviceCommonException {
        MicroPayRequestDTO microPayRequest = dozerBeanMapper.map(wechatQrOnlineRequest, MicroPayRequestDTO.class);
        WebApiReturnResultModel webApiReturnResultModel = organizePayInfoClient.organizePayInfo(new CommonOrganizeRequestDTO(wechatQrOnlineRequest.getOrganizeId()));
        OrganizePayInfoBO organizePayInfo = null;
        try {
            if (webApiReturnResultModel.getCode() == WebResponseState.SUCCESS.getCode()) {
                organizePayInfo = FeignUtil.formatClass(webApiReturnResultModel, OrganizePayInfoBO.class);
            } else {
                throw new DeviceCommonException(webApiReturnResultModel.getCode(), webApiReturnResultModel.getMsg());
            }
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            wuXiHuaJieFeignError.printStackTrace();
        }
        //
        MicroPayResponseDTO microPayResponse = null;
        try {
            microPayResponse = paymentService.wechatQrCodePayment(new WeChatPayConfig(organizePayInfo.getWxAppid(), organizePayInfo.getWxMchId(), organizePayInfo.getWxApiKey()), microPayRequest);
        } catch (Exception e) {
            throw new DeviceCommonException(DeviceResponseState.OTHER_ERROR);
        }
        if (microPayResponse.isSuccess()) {
            return microPayResponse;
        } else {
            throw new DeviceCommonException(DeviceResponseState.WECHAT_ERROR);
        }
    }
}
