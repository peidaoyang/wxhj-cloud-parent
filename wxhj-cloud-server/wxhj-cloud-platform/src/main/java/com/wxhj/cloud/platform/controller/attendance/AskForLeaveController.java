package com.wxhj.cloud.platform.controller.attendance;

import com.wxhj.cloud.redis.annotation.LogAnnotationIgnore;
import com.wxhj.cloud.redis.annotation.LogAnnotationController;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.business.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.business.vo.AskForLeaveVO;
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
@RequestMapping("/askForLeave")
@Api(tags = "请假管理")
@LogAnnotationController
public class AskForLeaveController {
    @Resource
    AskForLeaveClient askForLeaveClient;

    @LogAnnotationIgnore
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
