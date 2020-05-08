package com.wxhj.cloud.device.service.impl;

import com.google.common.base.Strings;
import com.wxhj.cloud.component.dto.MicroPayRequestDTO;
import com.wxhj.cloud.component.dto.MicroPayResponseDTO;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.enums.DeviceRecordStateEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.DeviceStaticClass;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.device.bo.DeviceGlobalParameterScreenBO;
import com.wxhj.cloud.device.config.DeviceServiceConfig;
import com.wxhj.cloud.device.domain.*;
import com.wxhj.cloud.device.domain.view.ViewDeviceResourceDO;
import com.wxhj.cloud.device.service.*;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.account.request.ListFaceChangeRecRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.FaceChangeVO;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoPosRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizePayInfoClient;
import com.wxhj.cloud.feignClient.platform.bo.OrganizePayInfoBO;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.wechat.WeChatPayConfig;
import com.wxhj.common.device.api.DeviceCommonService;
import com.wxhj.common.device.bo.DeviceGlobalParameterBO;
import com.wxhj.common.device.bo.ViewDeviceResourceBO;
import com.wxhj.common.device.dto.request.*;
import com.wxhj.common.device.dto.response.*;
import com.wxhj.common.device.exception.DeviceCommonException;
import com.wxhj.common.device.model.DeviceResponseState;
import com.wxhj.common.device.vo.FaceChangeRecVO;
import com.wxhj.common.device.vo.VisitorInfoVO;
import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;
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
    //@Resource
   // RedisTemplate redisTemplate;
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
    @Resource
    FaceChangeClient faceChangeClient;

    //static Double defMinIndex = (double) 0;
    // static Double defMaxIndex = Double.MAX_VALUE;
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
    public DeviceHeartbeatResponseDTO deviceHeartbeat(DeviceHeartbeatRequestDTO deviceHearbeatRequest)
            throws DeviceCommonException {
        //
        DeviceStateDO deviceState = dozerBeanMapper.map(deviceHearbeatRequest, DeviceStateDO.class);
        deviceState.setLastTime(new Date());
        // 设备信息数据入库
        deviceStateService.replace(deviceState);
        //
        DeviceParameterDO deviceParameter = deviceParameterService.selectByDeviceId(deviceState.getDeviceId());
        //
        List<ViewDeviceResourceDO> viewDeviceResource = viewDeviceResourceService
                .listByDeviceId(deviceState.getDeviceId());

        DeviceHeartbeatResponseDTO deviceHearbeatResponse = new DeviceHeartbeatResponseDTO();
        deviceHearbeatResponse.setDeviceId(deviceHearbeatRequest.getDeviceId());
        if (deviceParameter == null) {
            deviceHearbeatResponse.setParameterVersion(0L);
            deviceHearbeatResponse.setDeviceName("");
        } else {
            deviceHearbeatResponse.setParameterVersion(deviceParameter.getParameterVersion());
            deviceHearbeatResponse.setDeviceName(deviceParameter.getDeviceName());
        }

//        Optional.of(getFaceChange(deviceHearbeatRequest.getSceneId()))
//                .ifPresent((q) -> {
//                    deviceHearbeatResponse.setFaceMinIndex(q.getMinIndex());
//                    deviceHearbeatResponse.setFaceMinIndex(q.getMaxIndex());
//                });

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

    private FaceChangeVO getFaceChange(String sceneId) {
        WebApiReturnResultModel webApiReturnResultModel =
                faceChangeClient.listFaceChange(
                        new CommonIdListRequestDTO(Arrays.asList(sceneId)));
        try {
            List<FaceChangeVO> faceChanges = FeignUtil.formatArrayClass(webApiReturnResultModel, FaceChangeVO.class);
            if (faceChanges.size() > 0) {
                return faceChanges.get(0);
            }
            return null;
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            throw getDeviceCommonException(wuXiHuaJieFeignError);
        }
    }

    private List<FaceChangeRecVO> getFaceChangeRec(String sceneId, Long maxCurrent, Long minCurrent) {
        WebApiReturnResultModel webApiReturnResultModel = faceChangeClient
                .listFaceChangeRec(new ListFaceChangeRecRequestDTO(sceneId, maxCurrent, minCurrent));
        try {
            return FeignUtil.formatArrayClass(webApiReturnResultModel, FaceChangeRecVO.class);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            throw getDeviceCommonException(wuXiHuaJieFeignError);
        }
    }

//    private Pair<Long, Long> redisFaceChange(String sceneId) {
//        String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(sceneId);
//        if (!redisTemplate.hasKey(redisKey)) {
//            return MutablePair.of(0L, 0L);
//        } else {
//            Set<FaceChangeRecRedisDO> faceChangeRecSet = (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate
//                    .opsForZSet().rangeByScore(redisKey, defMinIndex, defMaxIndex, 0, 1);
//            Long min = faceChangeRecSet.iterator().next().getCurrentIndex();
//
//            faceChangeRecSet = (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate.opsForZSet()
//                    .reverseRangeByScore(redisKey, defMinIndex, defMaxIndex, 0, 1);
//            Long max = faceChangeRecSet.iterator().next().getCurrentIndex();
//            return MutablePair.of(min, max);
//        }
//    }

    @Override
    public FaceDataDownloadResponseDTO faceDataDownload(FaceDataDownloadRequestDTO faceDataDownloadRequest) throws DeviceCommonException {


        FaceDataDownloadResponseDTO faceDataDownloadResponse = dozerBeanMapper.map(faceDataDownloadRequest,
                FaceDataDownloadResponseDTO.class);

        Optional.of(getFaceChange(faceDataDownloadRequest.getSceneId()))
                .ifPresent((q) -> {
                    faceDataDownloadResponse.setFaceMinIndex(q.getMinIndex());
                    faceDataDownloadResponse.setFaceMinIndex(q.getMaxIndex());
                });

//        String redisKey = RedisKeyStaticClass.FACE_CHANGE_REDIS_KEY.concat(faceDataDownloadRequest.getSceneId());
//        if (redisTemplate.hasKey(redisKey)) {
//            Set<FaceChangeRecRedisDO> faceChangeRecSet =
//                    (LinkedHashSet<FaceChangeRecRedisDO>) redisTemplate
//                            .opsForZSet().rangeByScore(redisKey, faceDataDownloadRequest.getStartIndex(),
//                                    faceDataDownloadRequest.getEndIndex());
//            //
//            faceDataList = faceChangeRecSet.stream().map(q -> {
//                FaceChangeRecRedisVO faceChangeRecRedisTemp = dozerBeanMapper.map(q, FaceChangeRecRedisVO.class);
//                faceChangeRecRedisTemp.setImageUrl1(fileStorageService.generateFileUrl(q.getImageName()));
//                return faceChangeRecRedisTemp;
//            }).collect(Collectors.toList());
//
//            //
//            // faceDataList = new ArrayList<>(faceChangeRecSet);
//        }

        List<FaceChangeRecVO> faceDataList = getFaceChangeRec(faceDataDownloadRequest.getSceneId(), faceDataDownloadRequest.getEndIndex(), faceDataDownloadRequest.getStartIndex());
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
        deviceResourceDO.setDeviceId(deviceVersionState.getDeviceId());
        deviceResourceDO.setId(deviceVersionState.getId());
        deviceResourceDO.setSentState(1);
        deviceResourceService.update(deviceResourceDO);
        return;
    }

    @Override
    public List<VisitorInfoVO> visitorInfoPos(DeviceVisitorInfoPosRequestDTO deviceVisitorInfoPosRequest) {
        VisitorInfoPosRequestDTO visitorInfoPosRequest = dozerBeanMapper.map(deviceVisitorInfoPosRequest, VisitorInfoPosRequestDTO.class);
        List<VisitorInfoVO> visitorInfos = null;
        try {
            WebApiReturnResultModel webApiReturnResultModel = visitorInfoClient.visitorInfoPos(visitorInfoPosRequest);
            visitorInfos = FeignUtil.formatArrayClass(webApiReturnResultModel, VisitorInfoVO.class);
            return visitorInfos;
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            throw getDeviceCommonException(wuXiHuaJieFeignError);
        }

    }

    private DeviceCommonException getDeviceCommonException(WuXiHuaJieFeignError wuXiHuaJieFeignError) {
        return new DeviceCommonException(wuXiHuaJieFeignError.getWebApiReturnResultModel().getCode(), wuXiHuaJieFeignError.getWebApiReturnResultModel().getMsg());
    }

    @Override
    public DeviceInitializeResponseDTO deviceInitialize(DeviceInitializeRequestDTO deviceInitializeRequest) {
        DeviceInitializeResponseDTO deviceInitializeResponse = dozerBeanMapper.map(deviceInitializeRequest,
                DeviceInitializeResponseDTO.class);
        if (!Strings.isNullOrEmpty(deviceInitializeRequest.getId())
                && deviceInitializeRequest.getId().indexOf(DeviceStaticClass.PREFIX_DEVICE) >= 0) {
            return deviceInitializeResponse;
        }
        //String deviceId = deviceInitializeResponse.getDeviceId();
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
    public AccountBalanceResponseDTO accountBalance(DeviceCommonIdRequestDTO deviceCommonIdRequestDTO) throws DeviceCommonException {
        CommonIdRequestDTO commonIdRequest = new CommonIdRequestDTO();
        dozerBeanMapper.map(deviceCommonIdRequestDTO, commonIdRequest);

        WebApiReturnResultModel webApiReturnResultModel = accountClient.accountBalance(commonIdRequest);
        AccountBalanceResponseDTO accountBalanceResponse = new AccountBalanceResponseDTO(deviceCommonIdRequestDTO.getId(), 0.0);
        try {
            Double balance = FeignUtil.formatClass(webApiReturnResultModel, Double.class);
            accountBalanceResponse.setBalance(balance);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            throw getDeviceCommonException(wuXiHuaJieFeignError);
        }
        return accountBalanceResponse;
    }

    @Override
    public DeviceMicroPayResponseDTO wechatQrOnline(WechatQrOnlineRequestDTO wechatQrOnlineRequest) throws DeviceCommonException {
        MicroPayRequestDTO microPayRequest = dozerBeanMapper.map(wechatQrOnlineRequest, MicroPayRequestDTO.class);
        WebApiReturnResultModel webApiReturnResultModel = organizePayInfoClient.organizePayInfo(new CommonOrganizeRequestDTO(wechatQrOnlineRequest.getOrganizeId()));
        OrganizePayInfoBO organizePayInfo = null;
        try {

            organizePayInfo = FeignUtil.formatClass(webApiReturnResultModel, OrganizePayInfoBO.class);

        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            throw getDeviceCommonException(wuXiHuaJieFeignError);
        }
        //
        MicroPayResponseDTO microPayResponse = null;
        try {

            microPayResponse = paymentService.wechatQrCodePayment(
                    new WeChatPayConfig(organizePayInfo.getWxAppid(),
                            organizePayInfo.getWxMchId(),
                            organizePayInfo.getWxApiKey()),
                    microPayRequest);
        } catch (Exception e) {
            throw new DeviceCommonException(DeviceResponseState.OTHER_ERROR);
        }
        if (microPayResponse.isSuccess()) {
            return dozerBeanMapper.map(microPayResponse, DeviceMicroPayResponseDTO.class);
            //return microPayResponse;
        } else {
            throw new DeviceCommonException(DeviceResponseState.WECHAT_ERROR, microPayResponse.getReturnMsg());
        }
    }
}
