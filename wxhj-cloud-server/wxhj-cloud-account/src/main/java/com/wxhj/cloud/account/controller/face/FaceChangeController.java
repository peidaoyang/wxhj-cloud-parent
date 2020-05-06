package com.wxhj.cloud.account.controller.face;

import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.account.vo.FaceChangeVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import org.dozer.DozerBeanMapper;
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
    DozerBeanMapper dozerBeanMapper;

    @PostMapping("/maxIndex")
    public WebApiReturnResultModel maxIndex(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequest){
        List<FaceChangeVO> responseList = faceChangeService.listBySceneId(commonIdListRequest.getIdList()).stream().map(q-> dozerBeanMapper.map(q,FaceChangeVO.class)).collect(Collectors.toList());Collectors.toList();
        return WebApiReturnResultModel.ofSuccess(responseList);
    }

}
