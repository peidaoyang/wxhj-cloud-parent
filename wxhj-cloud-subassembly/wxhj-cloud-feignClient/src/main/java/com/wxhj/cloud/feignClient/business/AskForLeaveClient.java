package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.business.request.CheckAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020-04-08 13:09
 */
@FeignClient(name = "businessServer")
public interface AskForLeaveClient {
    @PostMapping("/askForLeave/submitAskForLeave")
    WebApiReturnResultModel submitAskForLeave(@RequestBody @Validated AskForLeaveDTO askForLeave);

    @PostMapping("/askForLeave/listAskForLeave")
    WebApiReturnResultModel listAskForLeave(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest);

    @PostMapping("/askForLeave/deleteAskForLeave")
    WebApiReturnResultModel deleteAskForLeave(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO);

    @PostMapping("/askForLeave/checkAskForLeave")
    WebApiReturnResultModel checkAskForLeave(@RequestBody @Validated CheckAskForLeaveRequestDTO checkAskForLeaveRequest);
}
