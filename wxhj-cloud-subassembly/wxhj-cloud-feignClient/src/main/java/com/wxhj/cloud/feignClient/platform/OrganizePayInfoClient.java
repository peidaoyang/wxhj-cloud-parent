package com.wxhj.cloud.feignClient.platform;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.fallback.OrganizePayInfoFallBack;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Component
@FeignClient(name = "platformServer", fallback = OrganizePayInfoFallBack.class)
public interface OrganizePayInfoClient {

    @PostMapping("/other/organizePay/organizePayInfo")
    WebApiReturnResultModel organizePayInfo(@Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest);
}
