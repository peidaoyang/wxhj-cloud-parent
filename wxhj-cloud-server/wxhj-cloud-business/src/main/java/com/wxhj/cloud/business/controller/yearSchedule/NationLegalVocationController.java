package com.wxhj.cloud.business.controller.yearSchedule;

import com.wxhj.cloud.business.runnable.NationLegalVocationUpdateThread;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.pool.ThreadPoolHelper;
import com.wxhj.cloud.core.utils.SpringUtil;
import com.wxhj.cloud.redis.annotation.LogAnnotationController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *
 * @author daxiong
 * @date 2020/5/8 5:53 下午
 */
@RestController
@RequestMapping("/nationLegalVocation")
@Api(tags = "国家法定节假日管理")
@Slf4j
@LogAnnotationController
public class NationLegalVocationController {

    @Resource
    SpringUtil springUtil;
    @Resource
    ThreadPoolHelper threadPoolHelper;

    @ApiOperation("更新年度国家法定节假日")
    @PostMapping("/updateNationLegalVocation")
    public WebApiReturnResultModel updateNationLegalVocation() {
        NationLegalVocationUpdateThread legalVocationUpdateThread = springUtil.getBean(NationLegalVocationUpdateThread.class);
        threadPoolHelper.executeTask("", legalVocationUpdateThread);
        return WebApiReturnResultModel.ofSuccess();
    }

}
