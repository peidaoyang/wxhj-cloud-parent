package com.wxhj.cloud.feignClient.platform.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizePayInfoClient;
import org.springframework.stereotype.Component;

@Component
public class OrganizePayInfoFallBack implements OrganizePayInfoClient {
    @Override
    public WebApiReturnResultModel organizePayInfo(CommonOrganizeRequestDTO commonOrganizeRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
