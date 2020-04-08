package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.ListAskForLeaveRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @author daxiong
 * @date 2020-04-08 13:14
 */
@Component
public class AskForLeaveFallback implements AskForLeaveClient {
    @Override
    public WebApiReturnResultModel submitAskForLeave(AskForLeaveDTO askForLeave) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listAskForLeave(ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteAskForLeave(CommonIdRequestDTO commonIdRequestDTO) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
