/**
 * 
 */
package com.wxhj.cloud.feignClient.platform;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.platform.fallback.EnumManageClientFallBack;
import com.wxhj.cloud.feignClient.platform.request.EnumTypeListRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: EnumManageClient.java
 * @author: cya
 * @Date: 2020年1月8日 下午2:55:48
 */
@Component
@FeignClient(name = "platformServer", fallback = EnumManageClientFallBack.class)
public interface EnumManageClient {
	@PostMapping("/backstage/enumManage/enumTypeList")
	@ApiOperation("根据枚举编号获取对应的枚举列表")
	WebApiReturnResultModel enumTypeList(@RequestBody EnumTypeListRequestDTO enumTypeListRequest);
}
