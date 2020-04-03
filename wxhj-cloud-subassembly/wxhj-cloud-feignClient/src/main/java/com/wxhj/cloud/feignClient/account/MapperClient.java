/** 
 * @fileName: MapoerClient.java  
 * @author: pjf
 * @date: 2019年11月8日 上午8:36:12 
 */

package com.wxhj.cloud.feignClient.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.MapperClientFallBack;
import com.wxhj.cloud.feignClient.account.request.AsyncMapListenListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AuthGroupIdListAndSceneIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ConfirmAsyncMapListenListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneByIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneRequestDTO;
import com.wxhj.cloud.feignClient.account.request.MapAuthoritySceneRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;

/**
 * @className MapoerClient.java
 * @author pjf
 * @date 2019年11月8日 上午8:36:12
 */

@FeignClient(name = "accountServer", fallback = MapperClientFallBack.class)
public interface MapperClient {
	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 上午9:56:21
	 * @param asyncCount
	 * @return
	 */
	@PostMapping("/mapper/asyncMapListenList")
	WebApiReturnResultModel asyncMapListenList(@RequestBody AsyncMapListenListRequestDTO asyncMapListenListRequest);

	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 上午9:56:27
	 * @param confirmAsyncMapListenListRequest
	 * @return
	 */
	@PostMapping("/mapper/confirmAsyncMapListenList")
	WebApiReturnResultModel confirmAsyncMapListenList(
			@RequestBody ConfirmAsyncMapListenListRequestDTO confirmAsyncMapListenListRequest);

	/**
	 * @author pjf
	 * @date 2019年11月6日 下午4:16:25
	 * @param mapAccountAuthRequestDTO 单个添加权限组人员
	 * @return
	 */
	//@PostMapping("/mapper/submitMapAccountAuthority")
	//WebApiReturnResultModel submitMapAccountAuthority(@RequestBody MapAccountAuthRequestDTO mapAccountAuthRequestDTO);

	/*
	 * 批量添加权限组人员
	 */
	@PostMapping("/mapper/submitMapAccountAuth")
	WebApiReturnResultModel submitMapAccountAuth(@Validated @RequestBody SubmitMapAccountAuthRequestDTO mapAccountAuth);

	/**
	 * @author pjf
	 * @date 2019年11月6日 下午4:43:02
	 * @param mapAuthoritySceneRequestDTO
	 * @return
	 */
	@PostMapping("/mapper/submitMapAuthorityScene")
	WebApiReturnResultModel submitMapAuthorityScene(
			@RequestBody MapAuthoritySceneRequestDTO mapAuthoritySceneRequestDTO);

	// @PostMapping("/mapper/submitMapAuthoritySceneList")
	// WebApiReturnResultModel
	// submitMapAuthoritySceneList(MapAuthoritySceneListRequestDTO
	// mapAuthoritySceneListRequest);
	@PostMapping("/mapper/submitAuthGroupIdListAndSceneId")
	WebApiReturnResultModel submitAuthGroupIdListAndSceneId(@RequestBody AuthGroupIdListAndSceneIdRequestDTO authGroupIdListAndSceneIdRequest);

	/**
	 * @author pjf
	 * @date 2019年11月6日 下午4:57:28
	 * @param deleteMapAuthSceneRequestDTO
	 * @return
	 */
	@PostMapping("/mapper/deleteMapAuthorityScene")
	WebApiReturnResultModel deleteMapAuthorityScene(
			@RequestBody DeleteMapAuthSceneRequestDTO deleteMapAuthSceneRequestDTO);

	@PostMapping("/mapper/deleteMapAuthSceneById")
	WebApiReturnResultModel deleteMapAuthSceneById(
			@RequestBody DeleteMapAuthSceneByIdRequestDTO deleteMapAuthSceneByIdRequest);

	@PostMapping("/mapper/deleteMapAccountAuthByAccountId")
	WebApiReturnResultModel deleteMapAccountAuthByAccountId(@RequestBody CommonIdRequestDTO commonIdRequest);

	/**
	 * @author pjf
	 * @date 2019年11月6日 下午5:00:28
	 * @param deleteMapAccountAuthRequestDTO
	 * @return
	 */
//	@PostMapping("/mapper/deleteMapAccountAuthority")
//	WebApiReturnResultModel deleteMapAccountAuthority(
//			@RequestBody DeleteMapAccountAuthRequestDTO deleteMapAccountAuthRequestDTO);

	@PostMapping("/mapper/listBySceneIdFromMapAuthScene")
	WebApiReturnResultModel listBySceneIdFromMapAuthScene(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/mapper/listByAuthIdFromMapAuthScene")
	WebApiReturnResultModel listByAuthIdFromMapAuthScene(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/mapper/listByAuthIdFromMapAuthAccount")
	WebApiReturnResultModel listByAuthIdFromMapAuthAccount(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/mapper/listByAuthFromMapAuthAccount")
	WebApiReturnResultModel listByAuthFromMapAuthAccount(@RequestBody CommonIdListRequestDTO commonIdListRequest);

	//
	@PostMapping("/mapper/listViewMapAuthAccountByAuthId")
	WebApiReturnResultModel listViewMapAuthAccountByAuthId(@RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/mapper/submitMapAccountAuthorityList")
	WebApiReturnResultModel submitMapAccountAuthorityList(
			@RequestBody SubmitMapAccountAuthListRequestDTO submitMapAccountAuthListRequest);
}
