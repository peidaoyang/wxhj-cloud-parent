package com.wxhj.cloud.faceServer.controller;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.faceServer.domian.FaceChangeDO;
import com.wxhj.cloud.faceServer.service.FaceChangeService;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceChangeClient;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author daxiong
 * @date 2020-03-31 14:00
 */
@RestController
@RequestMapping("/faceChange")
@Api(tags = "场景流水号管理")
@Slf4j
public class FaceChangeController implements FaceChangeClient{

    @Resource
    FaceChangeService faceChangeService;

    @PostMapping("/listBySceneId")
    @ApiOperation("获取场景流水号列表")
    @Override
    public WebApiReturnResultModel listBySceneId(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        List<String> idList = commonIdListRequestDTO.getIdList();

        List<FaceChangeDO> faceChanges = faceChangeService.listBySceneId(idList);

        return WebApiReturnResultModel.ofSuccess(faceChanges);
    }

    @PostMapping("/selectBySceneId")
    @ApiOperation("获取场景流水号")
    @Override
    public WebApiReturnResultModel selectBySceneId(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO) {
        String id = commonIdRequestDTO.getId();

        FaceChangeDO faceChangeDO = faceChangeService.selectBySceneId(id);

        return WebApiReturnResultModel.ofSuccess(faceChangeDO);
    }
}
