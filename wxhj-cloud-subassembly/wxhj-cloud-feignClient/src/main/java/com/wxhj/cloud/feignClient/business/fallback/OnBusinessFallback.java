package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.OnBusinessClient;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020-04-08 13:14
 */
@Component
public class OnBusinessFallback implements OnBusinessClient {
    @Override
    public WebApiReturnResultModel submitOnBusiness(OnBusinessDTO onBusiness) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listOnBusiness(ListAskForLeaveRequestDTO listAskForLeaveRequestDTO) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteOnBusiness(CommonIdListRequestDTO commonIdListRequestDTO) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
