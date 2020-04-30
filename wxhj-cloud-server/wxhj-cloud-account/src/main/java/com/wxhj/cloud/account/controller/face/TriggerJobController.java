package com.wxhj.cloud.account.controller.face;

import com.wxhj.cloud.account.config.ThreadPoolStaticClass;
import com.wxhj.cloud.account.runnable.FaceChangeCacheRunnable;
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
    @Resource
    FaceChangeCacheRunnable faceChangeCacheRunnable;
    @PostMapping("/faceChangeSynch")
    public WebApiReturnResultModel faceChangeSynch() {
        ThreadPoolStaticClass.faceChangeThreadPool.execute(faceChangeSynchRunnable);

        return WebApiReturnResultModel.ofSuccess();
    }
    @PostMapping("/faceChangeCache")
    public WebApiReturnResultModel faceChangeCache()
    {
        ThreadPoolStaticClass.faceChangeThreadPool.execute(faceChangeCacheRunnable);
        return WebApiReturnResultModel.ofSuccess();
    }
}