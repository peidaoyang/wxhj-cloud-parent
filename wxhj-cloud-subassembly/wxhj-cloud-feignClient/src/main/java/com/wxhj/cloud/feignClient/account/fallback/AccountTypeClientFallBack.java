package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountTypeClient;
import com.wxhj.cloud.feignClient.account.request.AccountByIdAndTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListByOrgTypeRequestDTO;
import org.springframework.stereotype.Component;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@Component
public class AccountTypeClientFallBack implements AccountTypeClient {
    @Override
    public WebApiReturnResultModel listByOrgType(ListByOrgTypeRequestDTO listByOrgType) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }

    @Override
    public WebApiReturnResultModel accountByIdAndType(AccountByIdAndTypeRequestDTO accountByIdAndType) {
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
