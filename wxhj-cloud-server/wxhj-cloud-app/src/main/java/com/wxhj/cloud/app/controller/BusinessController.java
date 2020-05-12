package com.wxhj.cloud.app.controller;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.app.dto.request.AppAskForLeaveRequestDTO;
import com.wxhj.cloud.app.dto.request.OnBusinessRequetDTO;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.business.OnBusinessClient;
import com.wxhj.cloud.feignClient.business.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
import com.wxhj.cloud.feignClient.business.request.CheckAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.business.request.CheckOnBusinessRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListAskForLeaveByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListOnBusinessByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/business")
@Api(tags = "商务管理")
public class BusinessController {
    @Resource
    AskForLeaveClient askForLeaveClient;
    @Resource
    OnBusinessClient onBusinessClient;
    @Resource
    Mapper dozerBeanMapper;

    @ApiOperation("提交请假申请")
    @PostMapping("/submitAskForLeave")
    @LcnTransaction
    public WebApiReturnResultModel submitAskForLeave(@RequestBody @Validated AppAskForLeaveRequestDTO appAskForLeaveRequest){
        AskForLeaveDTO askForLeave = dozerBeanMapper.map(appAskForLeaveRequest,AskForLeaveDTO.class);
        return askForLeaveClient.submitAskForLeave(askForLeave);
    }

    @ApiOperation("提交出差记录")
    @PostMapping("/submitOnBusiness")
    @LcnTransaction
    public WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessRequetDTO onBusinessRequet){
        OnBusinessDTO onBusinessDTO = dozerBeanMapper.map(onBusinessRequet,OnBusinessDTO.class);
        return onBusinessClient.submitOnBusiness(onBusinessDTO);
    }

    @ApiOperation("查询出差记录")
    @PostMapping("/listOnBusinessByAccountId")
    public WebApiReturnResultModel listOnBusinessByAccountId(@RequestBody @Validated ListOnBusinessByAccountIdRequestDTO listOnBusinessByAccountId){
        return onBusinessClient.listOnBusinessByAccountId(listOnBusinessByAccountId);
    }

    @ApiOperation("根据id查询出差记录")
    @PostMapping("/onBusinessById")
    public WebApiReturnResultModel onBusinessById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        return onBusinessClient.onBusinessById(commonIdRequest);
    }

    @ApiOperation("根据id查询请假记录")
    @PostMapping("/askForLeaveById")
    public WebApiReturnResultModel askForLeaveById(@RequestBody CommonIdRequestDTO commonIdRequest){
        return askForLeaveClient.askForLeaveById(commonIdRequest);
    }

    @ApiOperation("查询出差记录")
    @PostMapping("/listAskForLeaveByAccountId")
    public WebApiReturnResultModel listAskForLeaveByAccountId(@RequestBody ListAskForLeaveByAccountIdRequestDTO listAskForLeaveByAccountId){
        return askForLeaveClient.listAskForLeaveByAccountId(listAskForLeaveByAccountId);
    }


}
