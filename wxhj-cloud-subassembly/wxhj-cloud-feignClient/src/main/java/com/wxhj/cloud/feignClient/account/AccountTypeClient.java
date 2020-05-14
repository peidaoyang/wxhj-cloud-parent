package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.AccountTypeClientFallBack;
import com.wxhj.cloud.feignClient.account.request.ListByOrgTypeRequestDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "accountServer", fallback = AccountTypeClientFallBack.class)
public interface AccountTypeClient {
    @PostMapping("/accountType/listByOrgType")
    WebApiReturnResultModel listByOrgType(@RequestBody @Validated ListByOrgTypeRequestDTO listByOrgType);
}
