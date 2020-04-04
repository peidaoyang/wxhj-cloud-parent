package com.wxhj.cloud.account.controller;

import com.wxhj.cloud.account.domain.MapSceneAccountDO;
import com.wxhj.cloud.account.service.MapSceneAccountService;
import com.wxhj.cloud.account.thread.AccountFileDownloadThread;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.pool.ThreadPoolHelper;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.core.utils.SpringUtil;
import com.wxhj.cloud.feignClient.account.MapSceneAccountClient;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.file.FileDownloadDTO;
import com.wxhj.cloud.feignClient.platform.request.FileDownloadRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.FaceChangeClient;
import com.wxhj.cloud.feignClient.face.bo.FaceAccountInfoBO;
import com.wxhj.cloud.feignClient.face.bo.FaceChangeBO;
import com.wxhj.cloud.feignClient.platform.FileDownloadClient;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author daxiong
 * @date 2020-03-30 16:37
 */
@RestController
@RequestMapping("/scene/account")
@Api(tags = "场景账号管理")
@Slf4j
public class MapSceneAccountController implements MapSceneAccountClient {

	@Resource
	MapSceneAccountService mapSceneAccountService;
	@Resource
	FaceAccountClient faceAccountClient;
	@Resource
	FaceChangeClient faceChangeClient;
	@Resource
	FileDownloadClient fileDownloadClient;
	@Resource
	ThreadPoolHelper threadPoolHelper;

	@Resource
	SpringUtil springUtil;

	@ApiOperation("打包场景关联的人,人脸信息")
	@PostMapping("/packFaceBySceneId")
	@Override
	@Transactional
	public WebApiReturnResultModel packFaceBySceneId(@RequestBody @Validated FileDownloadRequestDTO fileDownloadRequestDTO) {
		String sceneId = fileDownloadRequestDTO.getSceneId();
		String organizeId = fileDownloadRequestDTO.getOrganizeId();
		// 根据场景id获取账号的id
		List<MapSceneAccountDO> mapSceneAccounts = mapSceneAccountService.listBySceneId(sceneId);
		// 获取账号id
		List<String> accountIds = new ArrayList<>(SystemStaticClass.INIT_CAPACITY);
		mapSceneAccounts.forEach(item -> accountIds.add(item.getAccountId()));
		try {
			// 根据场景id获取场景流水号信息
			CommonIdRequestDTO commonIdRequestDTO = new CommonIdRequestDTO();
			commonIdRequestDTO.setId(sceneId);
			WebApiReturnResultModel webApiReturnResultModel = faceChangeClient.selectBySceneId(commonIdRequestDTO);
			FaceChangeBO faceChange = FeignUtil.formatClass(webApiReturnResultModel, FaceChangeBO.class);

			// 获取账户face地址
			CommonIdListRequestDTO commonIdListRequestDTO = new CommonIdListRequestDTO();
			commonIdListRequestDTO.setIdList(accountIds);
			webApiReturnResultModel = faceAccountClient.listFaceAccountByIdList(commonIdListRequestDTO);
			List<FaceAccountInfoBO> faceAccountInfos = FeignUtil.formatArrayClass(webApiReturnResultModel,
					FaceAccountInfoBO.class);

			// 调用platform服务，新增下载记录，先不判断是否为同一个任务，也即同一个任务可以多次同时下载
			FileDownloadDTO fileDownload = new FileDownloadDTO();
			fileDownload.setTaskId(sceneId);
			fileDownload.setOrganizeId(organizeId);
			webApiReturnResultModel = fileDownloadClient.insertFileDownload(fileDownload);
			String insertId = FeignUtil.formatClass(webApiReturnResultModel, String.class);

			// 异步开启文件下载任务
			AccountFileDownloadThread accountFileDownloadThread = springUtil.getBean(AccountFileDownloadThread.class);
			accountFileDownloadThread.setFaceChange(faceChange);
			accountFileDownloadThread.setFaceAccountInfos(faceAccountInfos);
			accountFileDownloadThread.setDownloadId(insertId);
			threadPoolHelper.executeTask(insertId, accountFileDownloadThread);

			return WebApiReturnResultModel.ofSuccess(insertId);

		} catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
			return wuXiHuaJieFeignError.getWebApiReturnResultModel();
		}
	}


}