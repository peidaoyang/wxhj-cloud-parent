/**
 * @className MapperClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:48:57
 */
package com.wxhj.cloud.feignClient.account.fallback;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.request.AuthGroupIdListAndSceneIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteByAccountIdAndAuthorityIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneByIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.springframework.stereotype.Component;

//import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneRequestDTO;
//import com.wxhj.cloud.feignClient.account.request.MapAuthoritySceneRequestDTO;
//import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthRequestDTO;

/**
 * @className MapperClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 上午11:48:57
 */
@Component
public class MapperClientFallBack implements MapperClient {

//	@Override
//	public WebApiReturnResultModel asyncMapListenList(AsyncMapListenListRequestDTO asyncMapListenListRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}
//
//	@Override
//	public WebApiReturnResultModel confirmAsyncMapListenList(
//			ConfirmAsyncMapListenListRequestDTO confirmAsyncMapListenListRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

//	@Override
//	public WebApiReturnResultModel submitMapAccountAuthority(MapAccountAuthRequestDTO mapAccountAuthRequestDTO) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

//	@Override
//	public WebApiReturnResultModel submitMapAccountAuth(SubmitMapAccountAuthRequestDTO mapAccountAuth) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

//	@Override
//	public WebApiReturnResultModel submitMapAuthorityScene(MapAuthoritySceneRequestDTO mapAuthoritySceneRequestDTO) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

//	@Override
//	public WebApiReturnResultModel submitMapAuthoritySceneList(
//			MapAuthoritySceneListRequestDTO mapAuthoritySceneListRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

//	@Override
//	public WebApiReturnResultModel deleteMapAuthorityScene(DeleteMapAuthSceneRequestDTO deleteMapAuthSceneRequestDTO) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel deleteMapAuthSceneById(
			DeleteMapAuthSceneByIdRequestDTO deleteMapAuthSceneByIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel deleteMapAccountAuthority(
//			DeleteMapAccountAuthRequestDTO deleteMapAccountAuthRequestDTO) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

//	@Override
//	public WebApiReturnResultModel listBySceneIdFromMapAuthScene(CommonIdRequestDTO commonIdRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel listByAuthIdFromMapAuthScene(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listByAuthIdFromMapAuthAccount(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel listByAuthFromMapAuthAccount(CommonIdListRequestDTO commonIdListRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel listViewMapAuthAccountByAuthId(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitAuthGroupIdListAndSceneId(
			AuthGroupIdListAndSceneIdRequestDTO authGroupIdListAndSceneIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
		
	}

	@Override
	public WebApiReturnResultModel deleteMapAccountAuthByAccountId(CommonIdRequestDTO commonIdRequest) {
		// TODO Auto-generated method stub
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitMapAccountAuthorityList(
			SubmitMapAccountAuthListRequestDTO submitMapAccountAuthListRequest) {
		// TODO Auto-generated method stub
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel deleteByAccountIdAndAuthorityId(DeleteByAccountIdAndAuthorityIdRequestDTO deleteByAccountIdAndAuthorityId) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
