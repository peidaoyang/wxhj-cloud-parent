package com.wxhj.cloud.feignClient.school.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.request.AccountByOtherCodeAndTypeRequestDTO;
import com.wxhj.cloud.feignClient.school.StudentAccountClient;
import org.springframework.stereotype.Component;

/**
 * @Author cya
 * @Date 2020/5/22
 * @Version V1.0
 **/
@Component
public class StudentAccountClientFallBack implements StudentAccountClient {
    @Override
    public WebApiReturnResultModel accountByIdAndType(AccountByOtherCodeAndTypeRequestDTO accountByOtherCodeAndType){
        return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
    }
}
