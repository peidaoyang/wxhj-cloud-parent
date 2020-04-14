package com.wxhj.cloud.account.controller;

import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.FaceChangeDO;
import com.wxhj.cloud.account.domain.MapSceneAccountDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.FaceChangeService;
import com.wxhj.cloud.account.service.MapSceneAccountService;
import com.wxhj.cloud.account.thread.AccountFileDownloadThread;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.pool.ThreadPoolHelper;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.core.utils.SpringUtil;
import com.wxhj.cloud.feignClient.account.MapSceneAccountClient;
import com.wxhj.cloud.feignClient.account.bo.FileDownloadBO;
import com.wxhj.cloud.feignClient.face.bo.FaceAccountInfoBO;
import com.wxhj.cloud.feignClient.face.bo.FaceChangeBO;
import com.wxhj.cloud.feignClient.platform.FileDownloadClient;
import com.wxhj.cloud.feignClient.platform.request.FileDownloadRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	AccountInfoService accountInfoService;
	@Resource
	MapSceneAccountService mapSceneAccountService;
	@Resource
	FileDownloadClient fileDownloadClient;
	@Resource
	ThreadPoolHelper threadPoolHelper;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	FaceChangeService faceChangeService;

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
//			CommonIdRequestDTO commonIdRequestDTO = new CommonIdRequestDTO();
//			commonIdRequestDTO.setId(sceneId);
//			WebApiReturnResultModel webApiReturnResultModel = faceChangeClient.selectBySceneId(commonIdRequestDTO);
			FaceChangeDO faceChangeDO = faceChangeService.selectBySceneId(sceneId);
			if (faceChangeDO == null) {
				faceChangeDO = new FaceChangeDO();
				faceChangeDO.setMinIndex(-1L);
				faceChangeDO.setMaxIndex(-1L);
				faceChangeDO.setId(sceneId);
			}
			FaceChangeBO faceChange = dozerBeanMapper.map(faceChangeDO,FaceChangeBO.class);

			// 获取账户face地址
//			CommonIdListRequestDTO commonIdListRequestDTO = new CommonIdListRequestDTO();
//			commonIdListRequestDTO.setIdList(accountIds);
//			webApiReturnResultModel = faceAccountClient.listFaceAccountByIdList(commonIdListRequestDTO);
			List<AccountInfoDO> accountInfos = accountIds.size() == 0 ?
					new ArrayList<>() : accountInfoService.listByAccountIdList(accountIds);
			List<FaceAccountInfoBO> faceAccountInfos = accountInfos.stream().map(q-> dozerBeanMapper.map(q,FaceAccountInfoBO.class)).collect(Collectors.toList());

			// 调用platform服务，新增下载记录，先不判断是否为同一个任务，也即同一个任务可以多次同时下载
			FileDownloadBO fileDownload = new FileDownloadBO();
			fileDownload.setTaskId(sceneId);
			fileDownload.setOrganizeId(organizeId);
			WebApiReturnResultModel webApiReturnResultModel = fileDownloadClient.insertFileDownload(fileDownload);
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
