package com.wxhj.cloud.feignClient.business.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.business.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.business.request.CheckAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListAskForLeaveByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
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
    public WebApiReturnResultModel deleteAskForLeave(CommonIdListRequestDTO commonIdListRequestDTO) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel checkAskForLeave(CheckAskForLeaveRequestDTO checkAskForLeaveRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel askForLeaveById(CommonIdRequestDTO commonIdRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listAskForLeaveByAccountId(ListAskForLeaveByAccountIdRequestDTO listAskForLeaveByAccountId) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
