package com.wxhj.cloud.platform.controller.attendance;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.OnBusinessClient;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
import com.wxhj.cloud.feignClient.business.request.CheckOnBusinessRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.OnBusinessVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author daxiong
 * @date 2020-04-08 13:08
 */
@RestController
@RequestMapping("/onBusiness")
@Api(tags = "出差管理")
public class OnBusinessController{
    @Resource
    OnBusinessClient onBusinessClient;

    @PostMapping("/submitOnBusiness")
    @ApiOperation("编辑出差记录")
    @LcnTransaction
    public WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness) {
        return onBusinessClient.submitOnBusiness(onBusiness);
    }

    @PostMapping("/listOnBusiness")
    @ApiOperation(value = "获取出差记录列表", response = OnBusinessVO.class)
    @LcnTransaction
    public WebApiReturnResultModel listOnBusiness(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        return onBusinessClient.listOnBusiness(listAskForLeaveRequest);
    }

    @PostMapping("/deleteOnBusiness")
    @ApiOperation("删除出差记录")
    @LcnTransaction
    public WebApiReturnResultModel deleteOnBusiness(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        return onBusinessClient.deleteOnBusiness(commonIdListRequestDTO);
    }

    @PostMapping("/checkOnBusiness")
    @ApiOperation("出差审核")
    @LcnTransaction
    public WebApiReturnResultModel checkOnBusiness(@RequestBody @Validated CheckOnBusinessRequestDTO checkOnBusinessRequest) {
        return onBusinessClient.checkOnBusiness(checkOnBusinessRequest);
    }

}
