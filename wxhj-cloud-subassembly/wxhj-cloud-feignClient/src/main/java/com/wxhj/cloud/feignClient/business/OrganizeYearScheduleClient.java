package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.fallback.OrganizeYearScheduleFallback;
import com.wxhj.cloud.feignClient.business.request.SubmitOrganizeYearScheduleDTO;
import com.wxhj.cloud.feignClient.business.request.UseNationLegalVocationDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020/5/13 8:57 上午
 */
@FeignClient(name = "businessServer", fallback = OrganizeYearScheduleFallback.class)
public interface OrganizeYearScheduleClient {
    @PostMapping("/yearSchedule/listYearSchedule")
    WebApiReturnResultModel listYearSchedule(@RequestBody @Validated CommonListPageRequestDTO commonListPageRequest);

    @PostMapping("/yearSchedule/submitYearSchedule")
    WebApiReturnResultModel submitYearSchedule(@RequestBody @Validated SubmitOrganizeYearScheduleDTO submitOrganizeYearSchedule);

    @PostMapping("/yearSchedule/deleteYearSchedule")
    WebApiReturnResultModel deleteYearSchedule(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO);


    @PostMapping("/yearSchedule/useNationLegalVocation")
    WebApiReturnResultModel useNationLegalVocation(@RequestBody @Validated UseNationLegalVocationDTO useNationLegalVocation);

    @PostMapping("/yearSchedule/editYearSchedule")
    WebApiReturnResultModel editYearSchedule(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO);

    @PostMapping("/yearSchedule/listAllYearSchedule")
    WebApiReturnResultModel listAllYearSchedule(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest);

    @PostMapping("/yearSchedule/getTotalDayType")
    public WebApiReturnResultModel getTotalDayType(@RequestBody @Validated CommonIdRequestDTO commonIdRequest);
}
