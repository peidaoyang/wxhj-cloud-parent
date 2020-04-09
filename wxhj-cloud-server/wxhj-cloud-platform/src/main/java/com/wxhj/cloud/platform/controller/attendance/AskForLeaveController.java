package com.wxhj.cloud.platform.controller.attendance;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.vo.AskForLeaveVO;
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
@RequestMapping("/askForLeave")
@Api(tags = "请假管理")
public class AskForLeaveController {
    @Resource
    AskForLeaveClient askForLeaveClient;

    @PostMapping("/submitAskForLeave")
    @ApiOperation("编辑请假记录")
    public WebApiReturnResultModel submitAskForLeave(@RequestBody @Validated AskForLeaveDTO askForLeave) {
        return askForLeaveClient.submitAskForLeave(askForLeave);
    }

    @PostMapping("/listAskForLeave")
    @ApiOperation(value = "获取请假记录列表", response = AskForLeaveVO.class)
    public WebApiReturnResultModel listAskForLeave(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        return askForLeaveClient.listAskForLeave(listAskForLeaveRequest);
    }

    @PostMapping("/deleteAskForLeave")
    @ApiOperation("删除请假记录")
    public WebApiReturnResultModel deleteAskForLeave(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        return askForLeaveClient.deleteAskForLeave(commonIdListRequestDTO);
    }

}
