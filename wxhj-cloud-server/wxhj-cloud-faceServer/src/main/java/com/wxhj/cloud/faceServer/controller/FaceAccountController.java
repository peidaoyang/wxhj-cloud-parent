/** 
 * @fileName: FaceAccountController.java  
 * @author: pjf
 * @date: 2019年11月1日 下午2:44:33 
 */

package com.wxhj.cloud.faceServer.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.google.common.io.Files;
import com.wxhj.cloud.component.service.FaceImageService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.faceServer.domian.FaceAccountInfoDO;
import com.wxhj.cloud.faceServer.service.FaceAccountInfoService;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.bo.AccountInfoBO;
import com.wxhj.cloud.feignClient.account.bo.MapAccountAuthorityBO;
import com.wxhj.cloud.feignClient.account.request.AccountAppointNoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.UpdateIsFaceRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterBatchRequestDTO;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @className FaceAccountController.java
 * @author pjf
 * @date 2019年11月1日 下午2:44:33
 */
@RestController
@RequestMapping("/faceAccount")
@Slf4j
public class FaceAccountController implements FaceAccountClient {

	@Resource
	FileStorageService fileStorageService;
	@Resource
	AccountClient accountClient;
	@Resource
	FaceAccountInfoService faceAccountInfoService;
	@Resource
	FaceImageService faceImageService;
	@Resource
	MapperClient mapperClient;

	@Override
	@PostMapping("/faceRegister")
	@ApiOperation("人脸注册")
	public WebApiReturnResultModel faceRegister(@Validated @RequestBody FaceRegisterRequestDTO faceRegisterRequest) {
		AccountInfoBO accountInfo = null;
		WebApiReturnResultModel webApiReturnResultModel = null;
		try {
			faceImageCheck(faceRegisterRequest.getImageName());
			CommonIdRequestDTO commonIdRequest = new CommonIdRequestDTO(faceRegisterRequest.getAccountId());
			webApiReturnResultModel = accountClient.accountOne(commonIdRequest);
			accountInfo = FeignUtil.formatClass(webApiReturnResultModel, AccountInfoBO.class);

			commondFaceRegister(accountInfo, faceRegisterRequest.getImageName());

		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		return WebApiReturnResultModel.ofSuccess();
	}

	@Override
	@ApiOperation("人脸注册信息删除")
	@PostMapping("/faceDelete")
	public WebApiReturnResultModel faceDelete(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		FaceAccountInfoDO selectByAccount = faceAccountInfoService.selectByAccountId(commonIdRequest.getId());
		if (selectByAccount == null) {
			return WebApiReturnResultModel.ofSuccess();
		}
		faceAccountInfoService.deleteCascade(selectByAccount);
		return WebApiReturnResultModel.ofSuccess();
	}

	@Override
	@ApiOperation("人脸注册信息根据用户id查询")
	@PostMapping("/listFaceAccountByIdList")
	public WebApiReturnResultModel listFaceAccountByIdList(
			@Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
		List<FaceAccountInfoDO> listByAccountIdList = faceAccountInfoService
				.listByAccountIdList(commonIdListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess(listByAccountIdList);
	}

	@Override
	@ApiOperation("人脸批量注册")
	@PostMapping("/faceRegisterBatch")
	public WebApiReturnResultModel faceRegisterBatch(
			@Validated @RequestBody FaceRegisterBatchRequestDTO faceRegisterBatchRequest) {
		String originalImageName = Files.getNameWithoutExtension(faceRegisterBatchRequest.getOriginalImageName());
		AccountAppointNoRequestDTO accountAppointNoRequest = new AccountAppointNoRequestDTO();
		accountAppointNoRequest.setNo(originalImageName);
		accountAppointNoRequest.setNoType(faceRegisterBatchRequest.getImageNameType());
		accountAppointNoRequest.setOrganizeId(faceRegisterBatchRequest.getOrganizeId());
		WebApiReturnResultModel webApiReturnResultModel = null;
		try {
			faceImageCheck(faceRegisterBatchRequest.getImageName());
			webApiReturnResultModel = accountClient.accountAppointNo(accountAppointNoRequest);
			AccountInfoBO accountInfo = FeignUtil.formatClass(webApiReturnResultModel, AccountInfoBO.class);
			commondFaceRegister(accountInfo, faceRegisterBatchRequest.getImageName());
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		return WebApiReturnResultModel.ofSuccess();
	}

	// 人脸校验
	private void faceImageCheck(String imageName) throws WuXiHuaJieFeignError {
		byte[] imageFile = fileStorageService.getFile(imageName);
		boolean faceMonitor = faceImageService.faceMonitor(imageFile);
		if (!faceMonitor) {
			throw new WuXiHuaJieFeignError(WebApiReturnResultModel.ofStatus(WebResponseState.FACE_ERROR));
		}
	}

	private void commondFaceRegister(AccountInfoBO accountInfo, String imageName) throws WuXiHuaJieFeignError {
		WebApiReturnResultModel webApiReturnResultModel = null;
		if (accountInfo == null) {
			throw new WuXiHuaJieFeignError(WebResponseState.ACCOUNT_NO_EXIST);
		}
		
//		if (accountInfo.getIsReal() == 0) {
//			throw new WuXiHuaJieFeignError(WebResponseState.ACCOUNT_NO_REAL);
//		}
		FaceAccountInfoDO faceAcountInfo;
		if (accountInfo.getIsFace() == 0) {
			faceAcountInfo = new FaceAccountInfoDO();

		} else {
			throw new WuXiHuaJieFeignError(WebResponseState.ACCOUNT_NO_EXIST); 
			//faceAcountInfo = faceAccountInfoService.selectByAccountId(accountInfo.getAccountId());
		}
		faceAcountInfo.setFaceTocken("123");
		faceAcountInfo.setImageName(imageName);
		faceAcountInfo.setOrganizeId(accountInfo.getOrganizeId());
		faceAcountInfo.setIdNumber(accountInfo.getIdNumber());
		faceAcountInfo.setName(accountInfo.getName());
		faceAcountInfo.setPhoneNumber(accountInfo.getPhoneNumber());
		//
		// faceAcountInfo
		//
		if (Strings.isNullOrEmpty(faceAcountInfo.getAccountId())) {
			faceAcountInfo.setAccountId(accountInfo.getAccountId());
			faceAccountInfoService.insert(faceAcountInfo);
		} else {
			webApiReturnResultModel = mapperClient
					.deleteMapAccountAuthByAccountId(new CommonIdRequestDTO(faceAcountInfo.getAccountId()));

			List<MapAccountAuthorityBO> mapAccountAuthorityList = FeignUtil.formatArrayClass(webApiReturnResultModel,
					MapAccountAuthorityBO.class);
			faceAccountInfoService.update(faceAcountInfo);
			mapperClient.submitMapAccountAuthorityList(new SubmitMapAccountAuthListRequestDTO(mapAccountAuthorityList));

		}
		fileStorageService.notDeleteFile(imageName);
		// 事务问题暂不考虑
		accountClient.updateIsFace(new UpdateIsFaceRequestDTO(accountInfo.getAccountId(), 1));
	}
}
