package com.wxhj.cloud.account.controller.face;

import com.wxhj.cloud.account.handle.CacheSyncFaceChangeHandle;
import com.wxhj.cloud.account.handle.FaceChangeSynchHandle;
import com.wxhj.cloud.component.job.AsynJobPoolHelper;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonJobRequestDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/triggerJob")
public class TriggerJobController {
    @Resource
    AsynJobPoolHelper asynJobPoolHelper;
    @Resource
    FaceChangeSynchHandle fcaeChangeSynchHandle;
    @Resource
    CacheSyncFaceChangeHandle cacheSyncFaceChangeHandle;

    @PostMapping("/faceChangeSynch")
    public WebApiReturnResultModel faceChangeSynch(@RequestBody CommonJobRequestDTO commonJobRequest) {
        fcaeChangeSynchHandle.setCommonJobRequest(commonJobRequest);
        asynJobPoolHelper.addTrigger(fcaeChangeSynchHandle);
        return WebApiReturnResultModel.ofSuccess();
    }

    @PostMapping("/cacheSyncFaceChange")
    public WebApiReturnResultModel cacheSyncFaceChange(@RequestBody CommonJobRequestDTO commonJobRequest) {

        cacheSyncFaceChangeHandle.setCommonJobRequest(commonJobRequest);
        asynJobPoolHelper.addTrigger(cacheSyncFaceChangeHandle);
        return WebApiReturnResultModel.ofSuccess();
    }
}