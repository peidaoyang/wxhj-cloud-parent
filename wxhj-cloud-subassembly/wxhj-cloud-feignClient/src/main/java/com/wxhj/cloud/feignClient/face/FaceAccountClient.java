/** 
 * @fileName: FaceAccountClient.java  
 * @author: pjf
 * @date: 2019年11月1日 下午2:39:34 
 */

package com.wxhj.cloud.feignClient.face;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.face.fallback.FaceAccountClientFallBack;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterBatchRequestDTO;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;

/**
 * @className FaceAccountClient.java
 * @author pjf
 * @date 2019年11月1日 下午2:39:34
 */

@Component
@FeignClient(name = "faceServer", fallback = FaceAccountClientFallBack.class)
public interface FaceAccountClient {
	@PostMapping("/faceAccount/faceRegister")
	WebApiReturnResultModel faceRegister(@RequestBody FaceRegisterRequestDTO faceRegisterRequest);

//	@PostMapping("/faceAccount/faceDelete")
//	WebApiReturnResultModel faceDelete(@RequestBody CommonIdRequestDTO commonIdRequest);

//	@PostMapping("/faceAccount/listFaceAccountByIdList")
//	WebApiReturnResultModel listFaceAccountByIdList(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/faceAccount/faceRegisterBatch")
	WebApiReturnResultModel faceRegisterBatch(@RequestBody FaceRegisterBatchRequestDTO faceRegisterBatchRequest);

}
