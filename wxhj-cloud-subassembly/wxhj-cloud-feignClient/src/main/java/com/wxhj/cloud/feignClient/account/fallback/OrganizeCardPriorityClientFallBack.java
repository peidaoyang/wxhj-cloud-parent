package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.OrganizeCardPriorityClient;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.dto.OrganizeCardPriorityReOrderDTO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020/5/21 5:54 下午
 */
@Component
public class OrganizeCardPriorityClientFallBack implements OrganizeCardPriorityClient {
    @Override
    public WebApiReturnResultModel reorder(OrganizeCardPriorityReOrderDTO organizeCardPriorityReOrder) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listOrganizeCardPriority(CommonOrganizeRequestDTO commonOrganizeRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
