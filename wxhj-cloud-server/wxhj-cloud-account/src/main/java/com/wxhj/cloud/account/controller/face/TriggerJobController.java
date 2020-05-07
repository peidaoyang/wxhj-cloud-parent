package com.wxhj.cloud.account.controller.face;

import com.wxhj.cloud.account.config.ThreadPoolStaticClass;
import com.wxhj.cloud.account.runnable.FaceChangeSynchRunnable;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@RequestMapping("/triggerJob")
public class TriggerJobController {
    @Resource
    FaceChangeSynchRunnable faceChangeSynchRunnable;

    @PostMapping("/faceChangeSynch")
    public WebApiReturnResultModel faceChangeSynch() {
        ThreadPoolStaticClass.faceChangeThreadPool.execute(faceChangeSynchRunnable);

        return WebApiReturnResultModel.ofSuccess();
    }

}