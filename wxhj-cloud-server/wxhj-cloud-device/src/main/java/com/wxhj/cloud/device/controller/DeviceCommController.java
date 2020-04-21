/**
 * @fileName: DeviceCommController.java
 * @author: pjf
 * @date: 2019年12月4日 下午3:35:36
 */

package com.wxhj.cloud.device.controller;

import com.google.common.base.Strings;
import com.wxhj.cloud.component.dto.MicroPayRequestDTO;
import com.wxhj.cloud.component.dto.MicroPayResponseDTO;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.component.service.PaymentService;
import com.wxhj.cloud.core.enums.DeviceRecordStateEnum;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.statics.DeviceStaticClass;
import com.wxhj.cloud.core.statics.RedisKeyStaticClass;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.device.bo.DeviceGlobalParameterBO;
import com.wxhj.cloud.device.bo.DeviceGlobalParameterScreenBO;
import com.wxhj.cloud.device.bo.ViewDeviceResourceBO;
import com.wxhj.cloud.device.config.DeviceServiceConfig;
import com.wxhj.cloud.device.domain.*;
import com.wxhj.cloud.device.domain.view.ViewDeviceResourceDO;
import com.wxhj.cloud.device.dto.request.*;
import com.wxhj.cloud.device.dto.response.*;
import com.wxhj.cloud.device.service.*;
import com.wxhj.cloud.device.vo.FaceChangeRecRedisVO;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.response.AccountBalanceResponseDTO;
import com.wxhj.cloud.feignClient.business.VisitorInfoClient;
import com.wxhj.cloud.feignClient.business.request.VisitorInfoPosRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizePayInfoClient;
import com.wxhj.cloud.feignClient.platform.bo.OrganizePayInfoBO;
import com.wxhj.cloud.redis.domain.FaceChangeRecRedisDO;
import com.wxhj.cloud.rocketmq.RocketMqProducer;
import com.wxhj.cloud.wechat.WeChatPayConfig;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;
import org.dozer.DozerBeanMapper;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.*;
import java.util.stream.Collectors;

/**
 * @author pjf
 * @className DeviceCommController.java
 * @date 2019年12月4日 下午3:35:36
 */
@Api(tags = "设备通信控制器")
@RestController
@RequestMapping("/deviceComm")
public class DeviceCommController {
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

    @ApiOperation(value = "设备记录上送", response = DeviceRecordResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = DeviceRecordResponseDTO.class)
    // ,response=DeviceRecordResponseDTO.class
    @PostMapping("/uploadDeviceRecord")
    public WebApiReturnResultModel uploadDeviceRecord(
            @Validated @RequestBody DeviceRecordRequestDTO deviceRecordRequest) {
        DeviceRecordDO deviceRecord = dozerBeanMapper.map(deviceRecordRequest, DeviceRecordDO.class);
        deviceRecord.initialization();
        if (deviceRecordRequest.getRecordTimeStamp() < (new Date()).getTime() / 1000 - dataExpireTime) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.DATA_EXPIRE);
        }
        deviceRecordService.insert(deviceRecord);
        DeviceRecordResponseDTO deviceRecordResponse = dozerBeanMapper.map(deviceRecordRequest,
                DeviceRecordResponseDTO.class);

        if (!DeviceRecordStateEnum.NOT_DISTRIBUTE.getState().equals(deviceRecord.getDataState())) {
            return WebApiReturnResultModel.ofSuccessJson(deviceRecordResponse);
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
        return WebApiReturnResultModel.ofSuccessJson(deviceRecordResponse);
    }

    @ApiOperation(value = "设备心跳", response = DeviceHearbeatResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = DeviceHearbeatResponseDTO.class)
    @PostMapping("/deviceHeartbeat")
    public WebApiReturnResultModel deviceHeartbeat(
            @Validated @RequestBody DeviceHearbeatRequestDTO deviceHearbeatRequest) {
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

        DeviceHearbeatResponseDTO deviceHearbeatResponse = new DeviceHearbeatResponseDTO();
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
        return WebApiReturnResultModel.ofSuccessJson(deviceHearbeatResponse);

    }

    @ApiOperation(value = "人脸信息下发", response = FaceChangeRecRedisVO.class, responseContainer = "List")
    @ApiResponse(code = 200, message = "请求成功", response = FaceChangeRecRedisVO.class)
    @PostMapping("/faceDataDownload")
    public WebApiReturnResultModel faceDataDownload(
            @Validated @RequestBody FaceDataDownloadRequestDTO faceDataDownloadRequest) {
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
        return WebApiReturnResultModel.ofSuccessJson(faceDataDownloadResponse);
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

    @ApiOperation(value = "设备参数下载", response = DeviceParameterResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = DeviceParameterResponseDTO.class)
    @PostMapping("/deviceParameterDownload")
    public WebApiReturnResultModel deviceParameterDownload(
            @Validated @RequestBody DeviceParameterDownloadRequestDTO deviceParameterRequest) {
        DeviceParameterDO deviceParameter = deviceParameterService
                .selectByDeviceId(deviceParameterRequest.getDeviceId());

        DeviceParameterResponseDTO deviceParameterResponse = null;
        if (deviceParameter == null) {
            deviceParameter = dozerBeanMapper.map(deviceParameterRequest, DeviceParameterDO.class);
            deviceParameterService.insert(deviceParameter);

            deviceParameterResponse = dozerBeanMapper.map(deviceParameterRequest,
                    DeviceParameterResponseDTO.class);
            deviceParameterResponse.setParameterUrl1(fileStorageService.generateFileUrl(deviceParameter.getParameterUrl()));
            return WebApiReturnResultModel.ofSuccessJson(deviceParameterResponse);
        }
        if (deviceParameter.getParameterVersion() < deviceParameterRequest.getParameterVersion()) {
            deviceParameter = dozerBeanMapper.map(deviceParameterRequest, DeviceParameterDO.class);
            deviceParameterService.update(deviceParameter);

            deviceParameterResponse = dozerBeanMapper.map(deviceParameterRequest,
                    DeviceParameterResponseDTO.class);
            deviceParameterResponse.setParameterUrl1(fileStorageService.generateFileUrl(deviceParameter.getParameterUrl()));
            return WebApiReturnResultModel.ofSuccessJson(deviceParameterResponse);
        } else {

            deviceParameterResponse = dozerBeanMapper.map(deviceParameter,
                    DeviceParameterResponseDTO.class);
            deviceParameterResponse.setParameterUrl1(fileStorageService.generateFileUrl(deviceParameter.getParameterUrl()));
            return WebApiReturnResultModel.ofSuccessJson(deviceParameterResponse);
        }

        // return WebApiReturnResultModel.ofSuccessJson(deviceParameterResponse);
    }

    @ApiOperation(value = "授权信息获取", response = DeviceAuthorizeResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = DeviceAuthorizeResponseDTO.class)
    @PostMapping("/deviceAuthorizeDownload")
    public WebApiReturnResultModel deviceAuthorizeDownload(
            @Validated @RequestBody DeviceAuthorizeDownloadRequestDTO deviceAuthorizeDownloadRequest) {
        DeviceAuthorizeDO deviceAuthorize = deviceAuthorizeService.selectByDeviceIdAndType(
                deviceAuthorizeDownloadRequest.getDeviceId(), deviceAuthorizeDownloadRequest.getAuthorizeType());
        if (deviceAuthorize == null) {
            deviceAuthorize = deviceAuthorizeService
                    .selectByDeviceIdIsNullAndType(deviceAuthorizeDownloadRequest.getAuthorizeType());
            if (deviceAuthorize == null) {
                return WebApiReturnResultModel.ofStatus(WebResponseState.DATA_EXIST);
            }
            deviceAuthorize.setDeviceId(deviceAuthorizeDownloadRequest.getDeviceId());
            deviceAuthorizeService.update(deviceAuthorize);
        }
        DeviceAuthorizeResponseDTO deviceAuthorizeResponse = dozerBeanMapper.map(deviceAuthorize,
                DeviceAuthorizeResponseDTO.class);
        return WebApiReturnResultModel.ofSuccessJson(deviceAuthorizeResponse);
    }

    @ApiOperation("设备版本下发状态确认")
    @PostMapping("/deviceVersionState")
    public WebApiReturnResultModel deviceVersionState(
            @Validated @RequestBody DeviceVersionStateRequestDTO deviceVersionState) {
        DeviceResourceDO deviceResourceDO = new DeviceResourceDO();
        deviceResourceDO.setPosId(deviceVersionState.getDeviceId());
        deviceResourceDO.setId(deviceVersionState.getId());
        deviceResourceDO.setSentState(1);
        deviceResourceService.update(deviceResourceDO);
        return WebApiReturnResultModel.ofSuccess();
    }


    @PostMapping("/visitorInfoPos")
    @ApiOperation("访客在线查询")
    public WebApiReturnResultModel visitorInfoPos(
            @Validated @RequestBody VisitorInfoPosRequestDTO visitorInfoPosRequest) {
        return visitorInfoClient.visitorInfoPos(visitorInfoPosRequest);
    }

    @PostMapping("/deviceInitialize")
    @ApiOperation(value = "设备初始化", response = DeviceInitializeResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = DeviceInitializeResponseDTO.class)
    public WebApiReturnResultModel deviceInitialize(
            @Validated @RequestBody DeviceInitializeRequestDTO deviceInitializeRequest) {

        DeviceInitializeResponseDTO deviceInitializeResponse = dozerBeanMapper.map(deviceInitializeRequest,
                DeviceInitializeResponseDTO.class);
        if (!Strings.isNullOrEmpty(deviceInitializeRequest.getId())
                && deviceInitializeRequest.getId().indexOf(DeviceStaticClass.PREFIX_DEVICE) >= 0) {
            return WebApiReturnResultModel.ofSuccessJson(deviceInitializeResponse);
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
        return WebApiReturnResultModel.ofSuccessJson(deviceInitializeResponse);
    }

    @PostMapping("/accountBalance")
    @ApiOperation(value = "账户余额查询", response = AccountBalanceResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = AccountBalanceResponseDTO.class)
    public WebApiReturnResultModel accountBalance(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        WebApiReturnResultModel webApiReturnResultModel = accountClient.accountBalance(commonIdRequest);

        return WebApiReturnResultModel.ofSuccessToJson(webApiReturnResultModel);
    }

    @PostMapping("/wechatQrOnline")
    @ApiOperation(value = "微信二维码在线认证", response = MicroPayResponseDTO.class)
    @ApiResponse(code = 200, message = "请求成功", response = MicroPayResponseDTO.class)
    public WebApiReturnResultModel wechatQrOnline(@Validated @RequestBody WechatQrOnlineRequestDTO wechatQrOnlineRequest) {
        MicroPayRequestDTO microPayRequest = dozerBeanMapper.map(wechatQrOnlineRequest, MicroPayRequestDTO.class);
        WebApiReturnResultModel webApiReturnResultModel = organizePayInfoClient.organizePayInfo(new CommonOrganizeRequestDTO(wechatQrOnlineRequest.getOrganizeId()));
        OrganizePayInfoBO organizePayInfo = null;
        try {
            organizePayInfo = FeignUtil.formatClass(webApiReturnResultModel, OrganizePayInfoBO.class);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }
        //
        MicroPayResponseDTO microPayResponse = null;
        try {
            microPayResponse = paymentService.wechatQrCodePayment(new WeChatPayConfig(organizePayInfo.getWxAppid(), organizePayInfo.getWxMchId(), organizePayInfo.getWxApiKey()), microPayRequest);
        } catch (Exception e) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.OTHER_ERROR, e.getMessage());
        }
        if (microPayResponse.isSuccess()) {
            return WebApiReturnResultModel.ofSuccessJson(microPayResponse);

        } else {
            return WebApiReturnResultModel.ofStatus(WebResponseState.WECHAT_ERROR, microPayResponse.getReturnMsg());
        }
    }

}
