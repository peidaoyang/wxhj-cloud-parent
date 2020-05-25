package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
import com.wxhj.cloud.feignClient.business.fallback.OnBusinessFallback;
import com.wxhj.cloud.feignClient.business.request.CheckOnBusinessRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListOnBusinessByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020-04-08 13:09
 */
@FeignClient(name = "businessServer", fallback = OnBusinessFallback.class)
public interface OnBusinessClient {
    @PostMapping("/onBusiness/submitOnBusiness")
    WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness);

    @PostMapping("/onBusiness/listOnBusiness")
    WebApiReturnResultModel listOnBusiness(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequestDTO);

    @PostMapping("/onBusiness/deleteOnBusiness")
    WebApiReturnResultModel deleteOnBusiness(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO);

    @PostMapping("/onBusiness/checkOnBusiness")
    WebApiReturnResultModel checkOnBusiness(@RequestBody CheckOnBusinessRequestDTO checkOnBusinessRequest);

    @PostMapping("/onBusiness/listOnBusinessByAccountId")
    WebApiReturnResultModel listOnBusinessByAccountId(@RequestBody ListOnBusinessByAccountIdRequestDTO listOnBusinessByAccountId);

    @PostMapping("/onBusiness/onBusinessById")
    WebApiReturnResultModel onBusinessById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest);

}
