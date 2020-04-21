package com.wxhj.cloud.account.controller.face;

import com.google.common.eventbus.EventBus;
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
    @Resource(name = "faceChangeEventBus")
    EventBus faceChangeEventBus;

    @PostMapping("/faceChangeSynch")
    public WebApiReturnResultModel faceChangeSynch() {
        faceChangeEventBus.post(50);
        return WebApiReturnResultModel.ofSuccess();
    }

}