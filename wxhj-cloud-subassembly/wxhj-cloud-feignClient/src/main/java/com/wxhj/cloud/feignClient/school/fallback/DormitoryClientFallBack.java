package com.wxhj.cloud.feignClient.school.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.school.DormitoryClient;
import com.wxhj.cloud.feignClient.school.requestDTO.SubmitDormitoryRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@Component
public class DormitoryClientFallBack implements DormitoryClient {
    @Override
    public WebApiReturnResultModel listDormitory(CommonListPageRequestDTO commonListPage) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel listDormitoryByOrgId(CommonIdRequestDTO commonId) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel submitDormitory(SubmitDormitoryRequestDTO submitDormitory) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel deleteDormitory(CommonIdRequestDTO commonId) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel dormitoryInfo(CommonIdRequestDTO commonId) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
