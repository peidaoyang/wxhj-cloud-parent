package com.wxhj.cloud.feignClient.business;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.dto.OnBusinessDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author daxiong
 * @date 2020-04-08 13:09
 */
@FeignClient(name = "businessServer")
public interface OnBusinessClient {
    @PostMapping("/onBusiness/submitOnBusiness")
    WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness);

    @PostMapping("/onBusiness/listOnBusiness")
    WebApiReturnResultModel listOnBusiness(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequestDTO);

    @PostMapping("/onBusiness/deleteOnBusiness")
    WebApiReturnResultModel deleteOnBusiness(@RequestBody @Validated CommonIdRequestDTO commonIdRequestDTO);
}
