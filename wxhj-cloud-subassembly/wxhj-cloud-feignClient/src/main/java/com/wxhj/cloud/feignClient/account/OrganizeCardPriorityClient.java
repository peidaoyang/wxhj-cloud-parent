package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.OrganizeCardPriorityClientFallBack;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.dto.OrganizeCardPriorityReOrderDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020/5/21 5:53 下午
 */
@FeignClient(name = "accountServer", fallback = OrganizeCardPriorityClientFallBack.class)
public interface OrganizeCardPriorityClient {

    @PostMapping("/organizeCardPriority/reorder")
    WebApiReturnResultModel reorder(@RequestBody @Validated OrganizeCardPriorityReOrderDTO organizeCardPriorityReOrder);

    @PostMapping("/organizeCardPriority/listOrganizeCardPriority")
    WebApiReturnResultModel listOrganizeCardPriority(@RequestBody @Validated CommonOrganizeRequestDTO commonOrganizeRequest);
}
