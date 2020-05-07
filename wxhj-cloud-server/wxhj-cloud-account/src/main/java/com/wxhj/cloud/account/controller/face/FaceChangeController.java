package com.wxhj.cloud.account.controller.face;

import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.account.request.ListFaceChangeRecRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("/faceChange")
public class FaceChangeController implements FaceChangeClient {
    @Resource
    FaceChangeService faceChangeService;
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    FaceChangeRecService faceChangeRecService;

    @Override
    @PostMapping("/listFaceChange")
    public WebApiReturnResultModel listFaceChange(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
        List<FaceChangeDO> faceChangeList =
                faceChangeService.listBySceneIdList(commonIdListRequest.getIdList());

        return WebApiReturnResultModel.ofSuccess(faceChangeList);
    }

    @Override
    @PostMapping("/listFaceChangeRec")
    public WebApiReturnResultModel listFaceChangeRec(@RequestBody @Validated ListFaceChangeRecRequestDTO listFaceChangeRecRequest) {
        List<FaceChangeRecDO> faceChangeRecList = faceChangeRecService.listBySceneAndMaxIdAndMinId(listFaceChangeRecRequest.getSceneId(),
                listFaceChangeRecRequest.getMaxCurrent(), listFaceChangeRecRequest.getMinCurrent());
        return WebApiReturnResultModel.ofSuccess(faceChangeRecList);
    }


}
