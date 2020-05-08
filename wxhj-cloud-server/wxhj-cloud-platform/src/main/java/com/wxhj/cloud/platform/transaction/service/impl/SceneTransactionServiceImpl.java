/** 
 * @fileName: SceneTransactionServiceImpl.java  
 * @author: pjf
 * @date: 2019年11月13日 下午1:19:17 
 */

package com.wxhj.cloud.platform.transaction.service.impl;

import javax.annotation.Resource;

import com.wxhj.cloud.feignClient.account.FaceChangeClient;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.codingapi.txlcn.tc.annotation.TccTransaction;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.request.AuthGroupIdListAndSceneIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneByIdRequestDTO;
import com.wxhj.cloud.platform.domain.SceneInfoDO;
import com.wxhj.cloud.platform.dto.request.SubmitSceneInfoRequestDTO;
import com.wxhj.cloud.platform.service.SceneInfoService;
import com.wxhj.cloud.platform.transaction.service.SceneTransactionService;

/**
 * @className SceneTransactionServiceImpl.java
 * @author pjf
 * @date 2019年11月13日 下午1:19:17
 */

@Service
public class SceneTransactionServiceImpl implements SceneTransactionService {

	@Resource
	SceneInfoService sceneInfoService;
	@Resource
	MapperClient mapperClient;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	FaceChangeClient faceChangeClient;

	@Override
	@TccTransaction(confirmMethod = "deleteSceneInfoConfirmRpc", cancelMethod = "deleteSceneInfoCancelRpc")
	public WebApiReturnResultModel deleteSceneInfoExecute(String sceneId) {
		sceneInfoService.delete(sceneId);
		DeleteMapAuthSceneByIdRequestDTO deleteMapAuthSceneByIdRequest = new DeleteMapAuthSceneByIdRequestDTO(0,
				sceneId);
		faceChangeClient.deleteById(new CommonIdRequestDTO(sceneId ));
		return mapperClient.deleteMapAuthSceneById(deleteMapAuthSceneByIdRequest);
	}

	public WebApiReturnResultModel deleteSceneInfoConfirmRpc(String sceneId) {

		return WebApiReturnResultModel.ofSuccess();
	}

	public WebApiReturnResultModel deleteSceneInfoCancelRpc(String sceneId) {

		return WebApiReturnResultModel.ofMessage(500, "临时事务异常");
	}

	@Override
	@TccTransaction(confirmMethod = "submitSceneInfoConfirmRpc", cancelMethod = "submitSceneInfoCancelRpc")
	@LcnTransaction
	public WebApiReturnResultModel submitSceneInfoExecute(SubmitSceneInfoRequestDTO submitSceneInfoRequest) {
		SceneInfoDO sceneInfo;
		//MapAuthoritySceneListRequestDTO mapAuthoritySceneListRequest;
		String sceneId = "";
		if (Strings.isNullOrEmpty(submitSceneInfoRequest.getId())) {
			sceneInfo = dozerBeanMapper.map(submitSceneInfoRequest, SceneInfoDO.class);
			sceneId = sceneInfoService.insert(sceneInfo);
		} else {
			sceneInfo = dozerBeanMapper.map(submitSceneInfoRequest, SceneInfoDO.class);
			sceneInfoService.update(sceneInfo);
			sceneId = sceneInfo.getId();
		}
//		mapAuthoritySceneListRequest = new MapAuthoritySceneListRequestDTO();
//		mapAuthoritySceneListRequest.setSubmitType(0);
//		mapAuthoritySceneListRequest.setMainId(submitSceneInfoRequest.getAuthGroupId());
//		mapAuthoritySceneListRequest.setAssistId(sceneId);
//		
//		mapperClient.submitMapAuthoritySceneList(mapAuthoritySceneListRequest);
		AuthGroupIdListAndSceneIdRequestDTO authGroupIdListAndSceneIdRequestDTO = new AuthGroupIdListAndSceneIdRequestDTO(submitSceneInfoRequest.getAuthGroupIdList(),sceneId);
		mapperClient.submitAuthGroupIdListAndSceneId(authGroupIdListAndSceneIdRequestDTO);
		return WebApiReturnResultModel.ofSuccess(sceneId);
	}

	public WebApiReturnResultModel submitSceneInfoConfirmRpc(SubmitSceneInfoRequestDTO submitSceneInfoRequest) {
		return WebApiReturnResultModel.ofSuccess();
	}

	public WebApiReturnResultModel submitSceneInfoEsubmitSceneInfoCancelRpcxecute(
			SubmitSceneInfoRequestDTO submitSceneInfoRequest) {
		return WebApiReturnResultModel.ofMessage(500, "临时事务异常");
	}
}
