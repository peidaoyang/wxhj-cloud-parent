package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.OrganizeYearScheduleClient;
import com.wxhj.cloud.feignClient.business.request.SubmitOrganizeYearScheduleDTO;
import com.wxhj.cloud.feignClient.business.request.UseNationLegalVocationDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020/5/13 9:02 上午
 */
@Component
public class OrganizeYearScheduleFallback implements OrganizeYearScheduleClient {
    @Override
    public WebApiReturnResultModel listYearSchedule(CommonListPageRequestDTO commonListPageRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel submitYearSchedule(SubmitOrganizeYearScheduleDTO submitOrganizeYearSchedule) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteYearSchedule(CommonIdRequestDTO commonIdRequestDTO) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel useNationLegalVocation(UseNationLegalVocationDTO useNationLegalVocation) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel editYearSchedule(CommonIdRequestDTO commonIdRequestDTO) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listAllYearSchedule(CommonOrganizeRequestDTO commonOrganizeRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel getTotalDayType(CommonIdRequestDTO commonIdRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

}
