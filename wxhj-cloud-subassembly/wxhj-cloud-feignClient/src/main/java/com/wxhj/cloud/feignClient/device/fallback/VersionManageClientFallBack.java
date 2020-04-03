/**
 * @className VersionManageClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:25:52
 */
package com.wxhj.cloud.feignClient.device.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.device.VersionManageClient;
import com.wxhj.cloud.feignClient.device.request.SubmitVerManageRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageListRequestDTO;
import com.wxhj.cloud.feignClient.device.request.VersionManageOrgListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;

/**
 * @className VersionManageClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:25:52
 */
@Component
public class VersionManageClientFallBack implements VersionManageClient {

	@Override
	public WebApiReturnResultModel versionManageList(VersionManageListRequestDTO versionManageListRequestDTO) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel versionManageOrgList(VersionManageOrgListRequestDTO versionManageOrgList) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitVerManage(SubmitVerManageRequestDTO submitVerManage) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteVerManage(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
