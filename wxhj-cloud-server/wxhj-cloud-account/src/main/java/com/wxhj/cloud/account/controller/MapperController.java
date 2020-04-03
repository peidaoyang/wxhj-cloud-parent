/** 
 * @fileName: MapListenListController.java  
 * @author: pjf
 * @date: 2019年10月31日 下午2:30:43 
 */

package com.wxhj.cloud.account.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.account.domain.MapAuthoritySceneDO;
import com.wxhj.cloud.account.domain.view.ViewMapAccountAuthorityDO;
import com.wxhj.cloud.account.dto.response.MapAuthSceneResponseDTO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.account.service.MapAuthoritySceneService;
import com.wxhj.cloud.account.service.MapListenListService;
import com.wxhj.cloud.account.service.ViewMapAccountAuthorityService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.MapperClient;
import com.wxhj.cloud.feignClient.account.bo.MapAccountAuthorityBO;
import com.wxhj.cloud.feignClient.account.request.AsyncMapListenListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AuthGroupIdListAndSceneIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ConfirmAsyncMapListenListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneByIdRequestDTO;
import com.wxhj.cloud.feignClient.account.request.DeleteMapAuthSceneRequestDTO;
import com.wxhj.cloud.feignClient.account.request.MapAuthoritySceneRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitMapAccountAuthRequestDTO;
import com.wxhj.cloud.feignClient.account.response.MapAccountAuthResponseDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.platform.SceneClient;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @className MapListenListController.java
 * @author pjf
 * @date 2019年10月31日 下午2:30:43
 */

@RestController
@RequestMapping("/mapper")
@Slf4j
public class MapperController implements MapperClient {
	@Resource
	MapListenListService mapListenListService;
	@Resource
	MapAccountAuthorityService mapAccountAuthorityService;
	@Resource
	ViewMapAccountAuthorityService viewMapAccountAuthorityService;
	@Resource
	MapAuthoritySceneService mapAuthoritySceneService;
	@Resource
	AccountInfoService accountInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	SceneClient sceneClient;

	@ApiOperation("获取MapListen内容")
	@PostMapping("/asyncMapListenList")
	@Override
	public WebApiReturnResultModel asyncMapListenList(
			@Validated @RequestBody AsyncMapListenListRequestDTO asyncMapListenListRequest) {
		return WebApiReturnResultModel
				.ofSuccess(mapListenListService.selectByNoSync(asyncMapListenListRequest.getAsyncCount()));
	}

	@ApiOperation("确认asyncMapListenList已同步")
	@PostMapping("/confirmAsyncMapListenList")
	@Override
	public WebApiReturnResultModel confirmAsyncMapListenList(
			@Validated @RequestBody ConfirmAsyncMapListenListRequestDTO confirmAsyncMapListenListRequest) {
		int updateByIdSetSync = mapListenListService.updateByIdSetSync(confirmAsyncMapListenListRequest.getIdList());
		return WebApiReturnResultModel.ofSuccess(updateByIdSetSync);
	}

//	@ApiOperation("插入MapAccountAuthority")
//	@PostMapping("/submitMapAccountAuthority")
//	@Override
//	public WebApiReturnResultModel submitMapAccountAuthority(
//			@Validated @RequestBody MapAccountAuthRequestDTO mapAccountAuthRequestDTO) {
//		MapAccountAuthorityDO mapAccountAuthority = dozerBeanMapper.map(mapAccountAuthRequestDTO,
//				MapAccountAuthorityDO.class);
//		mapAccountAuthorityService.insertCascade(mapAccountAuthority);
//		return WebApiReturnResultModel.ofSuccess();
//	}

	// 权限组选择人员
	@ApiOperation("权限组选择用户")
	@PostMapping("/submitMapAccountAuth")
	@Override
	public WebApiReturnResultModel submitMapAccountAuth(
			@Validated @RequestBody SubmitMapAccountAuthRequestDTO mapAccountAuth) {
//		List<MapAccountAuthorityDO> newlistMap = mapAccountAuth.getAccountId().stream()
//				.map(q -> new MapAccountAuthorityDO(null, mapAccountAuth.getAuthorityId(), q))
//				.collect(Collectors.toList());
//		MapAccountAuthorityDO mapAccountAuthority = new MapAccountAuthorityDO();
//		mapAccountAuthority.setAuthorityGroupId(mapAccountAuth.getAuthorityId());
//		List<MapAccountAuthorityDO> oldlistMap = mapAccountAuthorityService.list(mapAccountAuthority);
//		List<MapAccountAuthorityDO> addlistMap = newlistMap.stream().filter(q -> !oldlistMap.contains(q))
//				.collect(Collectors.toList());
//		List<MapAccountAuthorityDO> dellistMap = oldlistMap.stream().filter(q -> !newlistMap.contains(q))
//				.collect(Collectors.toList());
//		addlistMap.forEach(q -> {
//			mapAccountAuthorityService.insertCascade(q);
//		});
//		dellistMap.forEach(q -> {
//			mapAccountAuthorityService.deleteCascade(q.getAuthorityGroupId(), q.getAccountId());
//		});
		mapAccountAuthorityService.update(mapAccountAuth.getAuthorityId(), mapAccountAuth.getAccountIdList());
		return WebApiReturnResultModel.ofSuccess();
	}

//	@ApiOperation("删除MapAccountAuthority")
//	@PostMapping("/deleteMapAccountAuthority")
//	@Override
//	public WebApiReturnResultModel deleteMapAccountAuthority(
//			@Validated @RequestBody DeleteMapAccountAuthRequestDTO deleteMapAccountAuthRequestDTO) {
//		int deleteCount = mapAccountAuthorityService.deleteCascade(deleteMapAccountAuthRequestDTO.getAuthorityGroupId(),
//				deleteMapAccountAuthRequestDTO.getAccountId());
//		if (deleteCount <= 0) {
//			return WebApiReturnResultModel.ofStatus(WebResponseState.NOT_MATCHING_RECORD);
//		}
//		return WebApiReturnResultModel.ofSuccess(null);
//	}

	@ApiOperation("插入MapAuthorityScene")
	@PostMapping("/submitMapAuthorityScene")
	@Override
	public WebApiReturnResultModel submitMapAuthorityScene(
			@Validated @RequestBody MapAuthoritySceneRequestDTO mapAuthoritySceneRequestDTO) {
		MapAuthoritySceneDO mapAuthorityScene = dozerBeanMapper.map(mapAuthoritySceneRequestDTO,
				MapAuthoritySceneDO.class);
		mapAuthoritySceneService.insertCascade(mapAuthorityScene);
		return WebApiReturnResultModel.ofSuccess(null);
	}

	@ApiOperation("删除MapAuthorityScene")
	@PostMapping("/deleteMapAuthorityScene")
	@Override
	public WebApiReturnResultModel deleteMapAuthorityScene(
			@RequestBody DeleteMapAuthSceneRequestDTO deleteMapAuthSceneRequestDTO) {
		int deleteCount = mapAuthoritySceneService.deleteCascade(deleteMapAuthSceneRequestDTO.getAuthorityGroupId(),
				deleteMapAuthSceneRequestDTO.getSceneId());
		if (deleteCount <= 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.NOT_MATCHING_RECORD);
		}
		return WebApiReturnResultModel.ofSuccess();
	}

	@Override
	@PostMapping("/submitAuthGroupIdListAndSceneId")
	public WebApiReturnResultModel submitAuthGroupIdListAndSceneId(@RequestBody
			AuthGroupIdListAndSceneIdRequestDTO authGroupIdListAndSceneIdRequest) {
		mapAuthoritySceneService.update(0, authGroupIdListAndSceneIdRequest.getAuthGroupIdList(),
				authGroupIdListAndSceneIdRequest.getSceneId());
		return WebApiReturnResultModel.ofSuccess();
	}
	// 场景绑定权限组
//	@Override
//	@Transactional
//	@PostMapping("/submitMapAuthoritySceneList")
//	public WebApiReturnResultModel submitMapAuthoritySceneList(
//			@RequestBody MapAuthoritySceneListRequestDTO mapAuthoritySceneListRequest) {
//		List<MapAuthoritySceneDO> addMapAuthoritySceneList = new ArrayList<>();
//		List<MapAuthoritySceneDO> deleteMapAuthoritySceneList = new ArrayList<>();
//
//		List<MapAuthoritySceneDO> newMapAuthoritySceneList = new ArrayList<>();
//		List<MapAuthoritySceneDO> oldMapAuthoritySceneList = new ArrayList<>();
//		//
//		MapAuthoritySceneDO mapAuthoritySceneDO = new MapAuthoritySceneDO();
//		if (mapAuthoritySceneListRequest.getSubmitType().equals(0)) {
//			mapAuthoritySceneDO.setSceneId(mapAuthoritySceneListRequest.getAssistId());
//			newMapAuthoritySceneList = mapAuthoritySceneListRequest.getMainId().stream()
//					.map(q -> new MapAuthoritySceneDO(null, q, mapAuthoritySceneListRequest.getAssistId()))
//					.collect(Collectors.toList());
//		} else {
//			mapAuthoritySceneDO.setAuthorityGroupId(mapAuthoritySceneListRequest.getAssistId());
//			newMapAuthoritySceneList = mapAuthoritySceneListRequest.getMainId().stream()
//					.map(q -> new MapAuthoritySceneDO(null, mapAuthoritySceneListRequest.getAssistId(), q))
//					.collect(Collectors.toList());
//		}
//
//		//
//		oldMapAuthoritySceneList = mapAuthoritySceneService.list(mapAuthoritySceneDO);
//		SetView<MapAuthoritySceneDO> differenceSetTemp = Sets.difference(Sets.newHashSet(newMapAuthoritySceneList),
//				Sets.newHashSet(oldMapAuthoritySceneList));
//		addMapAuthoritySceneList = Lists.newArrayList(differenceSetTemp);
//		differenceSetTemp = Sets.difference(Sets.newHashSet(oldMapAuthoritySceneList),
//				Sets.newHashSet(newMapAuthoritySceneList));
//		deleteMapAuthoritySceneList = Lists.newArrayList(differenceSetTemp);
//		addMapAuthoritySceneList.forEach(q -> {
//			mapAuthoritySceneService.insertCascade(q);
//		});
//		deleteMapAuthoritySceneList.forEach(q -> {
//			mapAuthoritySceneService.deleteCascade(q.getAuthorityGroupId(), q.getSceneId());
//		});
//		return WebApiReturnResultModel.ofSuccess();
//	}

	@Override
	@PostMapping("/deleteMapAuthSceneById")
	public WebApiReturnResultModel deleteMapAuthSceneById(
			@RequestBody DeleteMapAuthSceneByIdRequestDTO deleteMapAuthSceneByIdRequest) {

		if (deleteMapAuthSceneByIdRequest.getDeleteType().equals(0)) {
			mapAuthoritySceneService.deleteCascade(null, deleteMapAuthSceneByIdRequest.getId());

		} else {
			mapAuthoritySceneService.deleteCascade(deleteMapAuthSceneByIdRequest.getId(), null);
		}
		return WebApiReturnResultModel.ofSuccess();
	}

	@PostMapping("/listBySceneIdFromMapAuthScene")
	@Override
	public WebApiReturnResultModel listBySceneIdFromMapAuthScene(@RequestBody CommonIdRequestDTO commonIdRequest) {
		MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
		mapAuthorityScene.setSceneId(commonIdRequest.getId());
		List<MapAuthoritySceneDO> mapAuthoritySceneList = mapAuthoritySceneService.list(mapAuthorityScene);
		List<MapAuthSceneResponseDTO> mapResponse = mapAuthoritySceneList.stream()
				.map(q -> dozerBeanMapper.map(q, MapAuthSceneResponseDTO.class)).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(mapResponse);
	}

	@ApiOperation(value = "按权限组编号获取权限用户map集合")
	@PostMapping("/listByAuthFromMapAuthAccount")
	@Override
	public WebApiReturnResultModel listByAuthFromMapAuthAccount(
			@Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
		List<MapAccountAuthorityDO> listMap = mapAccountAuthorityService
				.listByAuthorityIdList(commonIdListRequest.getIdList());
		List<MapAccountAuthResponseDTO> listResponse = listMap.stream()
				.map(q -> dozerBeanMapper.map(q, MapAccountAuthResponseDTO.class)).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(listResponse);
	}

	@ApiOperation(value = "根据权限编号获取场景集合")
	@PostMapping("/listByAuthIdFromMapAuthScene")
	@LcnTransaction
	@Override
	public WebApiReturnResultModel listByAuthIdFromMapAuthScene(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		List<MapAuthoritySceneDO> listMap = mapAuthoritySceneService.listByAuthorityId(commonIdRequest.getId());
		List<String> listScene = listMap.stream().map(q -> q.getSceneId()).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(listScene);
	}

	@ApiOperation(value = "根据权限编号获取用户集合")
	@PostMapping("/listByAuthIdFromMapAuthAccount")
	@Override
	public WebApiReturnResultModel listByAuthIdFromMapAuthAccount(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		List<MapAccountAuthorityDO> listMap = mapAccountAuthorityService.listByAuthorityId(commonIdRequest.getId());

		List<String> list = listMap.stream().map(q -> q.getAccountId()).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(list);
	}

	@PostMapping("/listViewMapAuthAccountByAuthId")
	@Override
	public WebApiReturnResultModel listViewMapAuthAccountByAuthId(@RequestBody CommonIdRequestDTO commonIdRequest) {
		List<ViewMapAccountAuthorityDO> listMap = viewMapAccountAuthorityService
				.listByAuthorityId(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess(listMap);
	}

	@Override
	@PostMapping("/deleteMapAccountAuthByAccountId")
	public WebApiReturnResultModel deleteMapAccountAuthByAccountId(@RequestBody CommonIdRequestDTO commonIdRequest) {
		List<MapAccountAuthorityDO> deleteByAccountId = mapAccountAuthorityService
				.deleteByAccountId(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess(deleteByAccountId);
	}

	@Override
	@PostMapping("/submitMapAccountAuthorityList")
	public WebApiReturnResultModel submitMapAccountAuthorityList(
			@RequestBody SubmitMapAccountAuthListRequestDTO submitMapAccountAuthListRequest) {
		for (MapAccountAuthorityBO mapAccountAuthorityTemp : submitMapAccountAuthListRequest
				.getMapAccountAuthorityList()) {
			MapAccountAuthorityDO mapAccountAuthority = dozerBeanMapper.map(mapAccountAuthorityTemp,
					MapAccountAuthorityDO.class);
			mapAccountAuthorityService.insertCascade(mapAccountAuthority);
		}
//		MapAccountAuthorityDO mapAccountAuthority = dozerBeanMapper.map(mapAccountAuthRequestDTO,
//				MapAccountAuthorityDO.class);
//		mapAccountAuthorityService.insertCascade(mapAccountAuthority);
		return WebApiReturnResultModel.ofSuccess();
	}

}
