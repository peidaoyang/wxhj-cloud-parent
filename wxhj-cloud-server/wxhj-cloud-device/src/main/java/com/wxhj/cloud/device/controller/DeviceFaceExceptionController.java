package com.wxhj.cloud.device.controller;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.device.domain.DeviceFaceExceptionDO;
import com.wxhj.cloud.device.service.DeviceFaceExceptionService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.device.DeviceFaceExceptionClient;
import com.wxhj.cloud.feignClient.device.request.DeviceFaceExListRequestDTO;
import com.wxhj.cloud.feignClient.device.vo.DeviceFaceExVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * (DeviceFaceException)表控制层
 *
 * @author makejava
 * @since 2020-05-07 11:53:32
 */
@RestController
@RequestMapping("/deviceFaceEx")
@Api(tags = "人脸下发异常记录管理")
public class DeviceFaceExceptionController implements DeviceFaceExceptionClient {

    @Resource
    Mapper dozerBeanMapper;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    DeviceFaceExceptionService deviceFaceExceptionService;

    @Override
    @ApiOperation("人脸下发异常记录忽略")
    @PostMapping("/ignoreDeviceFaceEx")
    public WebApiReturnResultModel ignoreDeviceFaceEx(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
        deviceFaceExceptionService.batchIgnore(commonIdListRequest.getIdList());
        return WebApiReturnResultModel.ofSuccess();
    }

    @Override
    @ApiOperation(value = "人脸下发异常记录列表", response = DeviceFaceExVO.class)
    @PostMapping("/listDeviceFaceEx")
    public WebApiReturnResultModel listDeviceFaceEx(@RequestBody @Validated DeviceFaceExListRequestDTO deviceFaceExListRequest) {
        String organizeId = deviceFaceExListRequest.getOrganizeId();
        String sceneId = deviceFaceExListRequest.getSceneId();
        PageInfo<DeviceFaceExceptionDO> deviceFaceExPageInfo = deviceFaceExceptionService.listByOrganizedAndSceneId(deviceFaceExListRequest, organizeId, sceneId);
        List<DeviceFaceExVO> deviceFaceExVOList = deviceFaceExPageInfo.getList().stream().
                map(q -> dozerBeanMapper.map(q, DeviceFaceExVO.class)).collect(Collectors.toList());
        try {
            deviceFaceExVOList = (List<DeviceFaceExVO>) accessedRemotelyService.accessedOrganizeSceneList(deviceFaceExVOList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        IPageResponseModel pageResponseModel = PageUtil.initPageResponseModel(deviceFaceExPageInfo, deviceFaceExVOList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageResponseModel);
    }

}