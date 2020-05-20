package com.wxhj.cloud.account.controller.face;

import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.FaceChangeRecDO;
import com.wxhj.cloud.account.service.FaceChangeRecService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.account.request.ListFaceChangeRecRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.FaceChangeVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/faceChange")
public class FaceChangeController implements FaceChangeClient {
    @Resource
    FaceChangeService faceChangeService;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    FaceChangeRecService faceChangeRecService;
    @Resource
    MapListenListService mapListenListService;

    @Override
    @PostMapping("/listFaceChange")
    public WebApiReturnResultModel listFaceChange(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
        List<FaceChangeDO> faceChangeList =
                faceChangeService.listBySceneIdList(commonIdListRequest.getIdList());

        return WebApiReturnResultModel.ofSuccess(faceChangeList);
    }

    @PostMapping("/maxIndex")
    public WebApiReturnResultModel maxIndex(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest) {
        List<FaceChangeVO> responseList = faceChangeService.listBySceneIdList(commonIdListRequest.getIdList()).stream().map(q -> dozerBeanMapper.map(q, FaceChangeVO.class)).collect(Collectors.toList());
        responseList.forEach(q ->{
            q.setNeedDownPeople(mapListenListService.selectBySceneIdList(q.getId()));
        });
        return WebApiReturnResultModel.ofSuccess(responseList);
    }

    @PostMapping("/listFaceChangeRec")
    @Override
    public WebApiReturnResultModel listFaceChangeRec(@RequestBody @Validated ListFaceChangeRecRequestDTO listFaceChangeRecRequest) {
        List<FaceChangeRecDO> faceChangeRecList = faceChangeRecService.listBySceneAndMaxIdAndMinId(listFaceChangeRecRequest.getSceneId(),
                listFaceChangeRecRequest.getMaxCurrent(), listFaceChangeRecRequest.getMinCurrent());
        return WebApiReturnResultModel.ofSuccess(faceChangeRecList);
    }



    @PostMapping("/deleteById")
    @Override
    public WebApiReturnResultModel deleteById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        faceChangeService.delete(commonIdRequest.getId());
        return WebApiReturnResultModel.ofSuccess();
    }
}
