package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.account.request.ListFaceChangeRecRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;

@Component
public class FaceChangeClientFallback implements FaceChangeClient {


    @Override
    public WebApiReturnResultModel listFaceChange(CommonIdListRequestDTO commonIdListRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listFaceChangeRec(ListFaceChangeRecRequestDTO listFaceChangeRecRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel maxIndex(CommonIdListRequestDTO commonIdListRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteById(CommonIdRequestDTO commonIdRequest) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
