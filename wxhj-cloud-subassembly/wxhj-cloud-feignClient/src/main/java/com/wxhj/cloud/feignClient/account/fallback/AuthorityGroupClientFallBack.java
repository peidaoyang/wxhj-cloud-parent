/**
 * @className AuthorityGroupClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:46:21
 */
package com.wxhj.cloud.feignClient.account.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageByTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageRequestDTO;
import com.wxhj.cloud.feignClient.account.request.OptionalAuthorityGroupListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;

/**
 * @className AuthorityGroupClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:46:21
 */
@Component
public class AuthorityGroupClientFallBack implements AuthorityGroupClient {

	@Override
	public WebApiReturnResultModel listAuthorityGroupPage(ListAuthorityGroupPageRequestDTO listAuthorityGroupPage) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitAuthorityGroupInfo(
			SubmitAuthorityGroupInfoRequestDTO submitAuthorityGroupInfoRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteAuthorityGroupInfo(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


	@Override
	public WebApiReturnResultModel listAuthorityGroupPageByType(
			ListAuthorityGroupPageByTypeRequestDTO listAuthorityGroupPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}


//	@Override
//	public WebApiReturnResultModel selectedSceneList(CommonIdRequestDTO commonIdRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel optionalAuthorityGroupList(OptionalAuthorityGroupListRequestDTO optionalAuthorityGroupListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}
	
	
	@Override
	public WebApiReturnResultModel optionalAuthByOrganList(CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel autoSynchroAuth(CommonIdListRequestDTO commonIdListRequestDTO) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel authorityBySceneId(CommonIdListRequestDTO commonIdListRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listAccountById(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel optionalAuthList() {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}
}
