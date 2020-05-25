package com.wxhj.cloud.platform.controller.yearSchedule;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.OrganizeYearScheduleClient;
import com.wxhj.cloud.feignClient.business.request.SubmitOrganizeYearScheduleDTO;
import com.wxhj.cloud.feignClient.business.request.UseNationLegalVocationDTO;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.redis.annotation.LogAnnotationController;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/13 9:06 上午
 */
@RestController
@RequestMapping("/yearSchedule")
@LogAnnotationController
@Api(tags = "组织年度日期安排管理")
public class OrganizeYearScheduleController {

    @Resource
    OrganizeYearScheduleClient organizeYearScheduleClient;

    @ApiOperation(value = "获取组织年度日期安排列表", response = OrganizeYearScheduleVO.class)
    @PostMapping("/listYearSchedule")
    public WebApiReturnResultModel listYearSchedule(@RequestBody @Validated CommonListPageRequestDTO commonListPageRequest) {
        return organizeYearScheduleClient.listYearSchedule(commonListPageRequest);
    }

    @ApiOperation(value = "更新组织年度日期安排")
    @PostMapping("/submitYearSchedule")
    public WebApiReturnResultModel submitYearSchedule(@RequestBody @Validated SubmitOrganizeYearScheduleDTO submitOrganizeYearSchedule) {
        return organizeYearScheduleClient.submitYearSchedule(submitOrganizeYearSchedule);
    }

    @ApiOperation(value = "删除组织年度日期安排")
    @PostMapping("/deleteYearSchedule")
    public WebApiReturnResultModel deleteYearSchedule(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO) {
        return organizeYearScheduleClient.deleteYearSchedule(commonIdRequestDTO);
    }

    @ApiOperation(value = "应用国家法定节假日", response = OrganizeYearScheduleRecVO.class)
    @PostMapping("/useNationLegalVocation")
    public WebApiReturnResultModel useNationLegalVocation(@RequestBody @Validated UseNationLegalVocationDTO useNationLegalVocation) {
        return organizeYearScheduleClient.useNationLegalVocation(useNationLegalVocation);
    }

    @ApiOperation(value = "编辑组织年度日期安排", response = SubmitOrganizeYearScheduleDTO.class)
    @PostMapping("/editYearSchedule")
    public WebApiReturnResultModel editYearSchedule(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO) {
        return organizeYearScheduleClient.editYearSchedule(commonIdRequestDTO);
    }

    @ApiOperation(value = "获取所有的组织年度日期安排列表", response = OrganizeYearScheduleVO.class)
    @PostMapping("/listAllYearSchedule")
    public WebApiReturnResultModel listAllYearSchedule(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest) {
        return organizeYearScheduleClient.listAllYearSchedule(commonOrganizeRequest);
    }

    @ApiOperation(value = "获取某个节假日规则的所有日期类型")
    @PostMapping("/getTotalDayType")
    public WebApiReturnResultModel getTotalDayType(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        return organizeYearScheduleClient.getTotalDayType(commonIdRequest);
    }
}
