/**
 * 
 */
package com.wxhj.cloud.feignClient.device;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.fallback.VersionManageClientFallBack;
import com.wxhj.cloud.feignClient.device.request.SubmitVerManageRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageListRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageOrgListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

/**
 * @ClassName: VersionManage.java
 * @author: cya
 * @Date: 2020年1月3日 上午9:32:09
 */
@Component
@FeignClient(name = "deviceServer", fallback = VersionManageClientFallBack.class)
public interface VersionManageClient {
	@PostMapping("/versionManage/versionManageList")
	WebApiReturnResultModel versionManageList(@RequestBody VersionManageListRequestDTO versionManageListRequestDTO);

	@PostMapping("/versionManage/versionManageOrgList")
	WebApiReturnResultModel versionManageOrgList(@RequestBody VersionManageOrgListRequestDTO versionManageOrgList);

	@PostMapping("/versionManage/submitVerManage")
	WebApiReturnResultModel submitVerManage(@RequestBody SubmitVerManageRequestDTO submitVerManage);

	@PostMapping("/versionManage/deleteVerManage")
	WebApiReturnResultModel deleteVerManage(@RequestBody CommonIdListRequestDTO commonIdListRequest);
}
