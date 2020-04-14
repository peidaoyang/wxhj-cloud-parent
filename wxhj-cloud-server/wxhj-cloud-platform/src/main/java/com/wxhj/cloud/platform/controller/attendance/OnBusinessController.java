package com.wxhj.cloud.platform.controller.attendance;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.OnBusinessClient;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
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
public class OnBusinessController implements OnBusinessClient {
    @Resource
    OnBusinessClient onBusinessClient;

    @Override
    @PostMapping("/submitOnBusiness")
    @ApiOperation("编辑出差记录")
    public WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness) {
        return onBusinessClient.submitOnBusiness(onBusiness);
    }

    @Override
    @PostMapping("/listOnBusiness")
    @ApiOperation(value = "获取出差记录列表", response = OnBusinessVO.class)
    public WebApiReturnResultModel listOnBusiness(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        return onBusinessClient.listOnBusiness(listAskForLeaveRequest);
    }

    @Override
    @PostMapping("/deleteOnBusiness")
    @ApiOperation("删除出差记录")
    public WebApiReturnResultModel deleteOnBusiness(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        return onBusinessClient.deleteOnBusiness(commonIdListRequestDTO);
    }

}
