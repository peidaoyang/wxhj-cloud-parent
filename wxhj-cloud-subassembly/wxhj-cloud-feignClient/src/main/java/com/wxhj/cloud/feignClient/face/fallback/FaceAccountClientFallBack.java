/**
 * @className FaceAccountClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:41:23
 */
package com.wxhj.cloud.feignClient.face.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterBatchRequestDTO;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;

/**
 * @className FaceAccountClientFallBack.java
 * @admin jwl
 * @date 2020年1月19日 下午1:41:23
 */
@Component
public class FaceAccountClientFallBack implements FaceAccountClient {

	@Override
	public WebApiReturnResultModel faceRegister(FaceRegisterRequestDTO faceRegisterRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel faceDelete(CommonIdRequestDTO commonIdRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}
//
//	@Override
//	public WebApiReturnResultModel listFaceAccountByIdList(CommonIdListRequestDTO commonIdListRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel faceRegisterBatch(FaceRegisterBatchRequestDTO faceRegisterBatchRequest) {
		// TODO Auto-generated method stub
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);

	}

}
