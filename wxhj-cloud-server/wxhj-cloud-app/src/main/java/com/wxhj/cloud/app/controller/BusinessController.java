package com.wxhj.cloud.app.controller;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.business.OnBusinessClient;
import com.wxhj.cloud.feignClient.business.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/business")
@Api("商务管理")
public class BusinessController {
    @Resource
    AskForLeaveClient askForLeaveClient;
    @Resource
    OnBusinessClient onBusinessClient;

    @ApiOperation("提交请假申请")
    @PostMapping("/submitAskForLeave")
    public WebApiReturnResultModel submitAskForLeave(@RequestBody @Validated AskForLeaveDTO askForLeave){
        return askForLeaveClient.submitAskForLeave(askForLeave);
    }

    @ApiOperation("编辑出差记录")
    @PostMapping("/submitOnBusiness")
    public WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness){
        return onBusinessClient.submitOnBusiness(onBusiness);
    }
}
