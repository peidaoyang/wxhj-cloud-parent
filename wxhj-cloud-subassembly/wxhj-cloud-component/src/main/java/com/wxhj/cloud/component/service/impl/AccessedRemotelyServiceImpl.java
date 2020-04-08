/** 
 * @fileName: AccessedRemotelyServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月6日 下午3:19:01 
 */

package com.wxhj.cloud.component.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.PlatformEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.bo.IOrganizeChildrenOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeUserModel;
import com.wxhj.cloud.feignClient.bo.IPlatformEnumModel;
import com.wxhj.cloud.feignClient.bo.ISceneModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.platform.EnumManageClient;
import com.wxhj.cloud.feignClient.platform.OrganizeClient;
import com.wxhj.cloud.feignClient.platform.SceneClient;
import com.wxhj.cloud.feignClient.platform.UserClient;
import com.wxhj.cloud.feignClient.platform.bo.EnumManageBO;
import com.wxhj.cloud.feignClient.platform.bo.SceneInfoBO;
import com.wxhj.cloud.feignClient.platform.bo.SysOrganizeBO;
import com.wxhj.cloud.feignClient.platform.bo.UserByIdListBO;
import com.wxhj.cloud.feignClient.platform.request.EnumTypeListRequestDTO;

/**
 * @className AccessedRemotelyServiceImpl.java
 * @author pjf
 * @date 2020年2月6日 下午3:19:01
 */

@Service
public class AccessedRemotelyServiceImpl implements AccessedRemotelyService {
	@Resource
	OrganizeClient organizeClient;
	@Resource
	SceneClient sceneClient;
	@Resource
	EnumManageClient enumManageClient;
	@Resource
	UserClient userClient;
//	@Resource
//	FaceAccountClient faceAccountClient;

	private Map<String, String> accessedOrganize(List<String> organizeList) throws WuXiHuaJieFeignError {
		Map<String, String> organizeMap = new HashMap<String, String>();
		CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest = new CommonOrganizeIdListRequestDTO(organizeList);

		WebApiReturnResultModel webApiReturnResultModel = organizeClient
				.listOrganizeByIdList(commonOrganizeIdListRequest);
		List<SysOrganizeBO> sysOrganizeList = FeignUtil.formatArrayClass(webApiReturnResultModel, SysOrganizeBO.class);
		sysOrganizeList.stream().forEach(q -> {
			organizeMap.put(q.getId(), q.getFullName());
		});
//		if (webApiReturnResultModel.resultSuccess()) {
//			String jsonString = JSON.toJSONString(webApiReturnResultModel.getData());
//			List<SysOrganizeBO> sysOrganizeList = JSON.parseArray(jsonString, SysOrganizeBO.class);
//			sysOrganizeList.stream().forEach(q -> {
//				organizeMap.put(q.getId(), q.getFullName());
//			});
//		}
		return organizeMap;
	}

	private Map<String, String> accessedScene(List<String> sceneList) throws WuXiHuaJieFeignError {
		Map<String, String> sceneMap = new HashMap<String, String>();
		CommonIdListRequestDTO commonIdListRequest = new CommonIdListRequestDTO();
		commonIdListRequest.setIdList(sceneList);
		WebApiReturnResultModel webApiReturnResultModel = sceneClient.listSceneByIdList(commonIdListRequest);

		List<SceneInfoBO> sceneInfoList = FeignUtil.formatArrayClass(webApiReturnResultModel, SceneInfoBO.class);
		sceneInfoList.stream().forEach(q -> {
			sceneMap.put(q.getId(), q.getSceneName());
		});
		return sceneMap;

//		if (webApiReturnResultModel.resultSuccess()) {
//			String jsonString = JSON.toJSONString(webApiReturnResultModel.getData());
//			List<SceneInfoBO> sceneInfoList = JSON.parseArray(jsonString, SceneInfoBO.class);
//			sceneInfoList.stream().forEach(q -> {
//				sceneMap.put(q.getId(), q.getSceneName());
//			});
//		}
//		return sceneMap;
	}

	private Map<String, String> accessedUser(List<String> userList) throws WuXiHuaJieFeignError {
		Map<String, String> userMap = new HashMap<String, String>();
		CommonIdListRequestDTO commonIdListRequest = new CommonIdListRequestDTO(userList);

		WebApiReturnResultModel webApiReturnResultModel = userClient.userByIdList(commonIdListRequest);
		List<UserByIdListBO> userInfoList = FeignUtil.formatArrayClass(webApiReturnResultModel, UserByIdListBO.class);
		userInfoList.stream().forEach(q -> {
			userMap.put(q.getCreatorUserId(), q.getCreatorUserName());
		});

		return userMap;
//		if (webApiReturnResultModel.resultSuccess()) {
//			String jsonString = JSON.toJSONString(webApiReturnResultModel.getData());
//			List<UserByIdListBO> userInfoList = JSON.parseArray(jsonString, UserByIdListBO.class);
//			userInfoList.stream().forEach(q -> {
//				userMap.put(q.getCreatorUserId(), q.getCreatorUserName());
//			});
//		}
//		return userMap;
	}

	private Map<Integer, String> accessedPlatformEnum(PlatformEnum platformEnum) throws WuXiHuaJieFeignError {
		Map<Integer, String> platformEnumMap = new HashMap<>(6);
		EnumTypeListRequestDTO enumTypeListRequest = new EnumTypeListRequestDTO();
		enumTypeListRequest.setEnumCode(platformEnum.getCode());
		WebApiReturnResultModel webApiReturnResultModel = enumManageClient.enumTypeList(enumTypeListRequest);

		List<EnumManageBO> enumManage = FeignUtil.formatArrayClass(webApiReturnResultModel, EnumManageBO.class);
		enumManage.stream().forEach(q -> {
			platformEnumMap.put(q.getEnumType(), q.getEnumTypeName());
		});
//		if (webApiReturnResultModel.resultSuccess()) {
//			String jsonString = JSON.toJSONString(webApiReturnResultModel.getData());
//			List<EnumManageBO> enumManage = JSON.parseArray(jsonString, EnumManageBO.class);
//			enumManage.stream().forEach(q -> {
//				platformEnumMap.put(q.getEnumType(), q.getEnumTypeName());
//			});
//		}
		return platformEnumMap;
	}

//	private Map<String, String> accessedAccountFaceImage(List<String> accountIdList) throws WuXiHuaJieFeignError {
//		Map<String, String> accountFaceMap = new HashMap<String, String>();
//		WebApiReturnResultModel webApiReturnResultModel = faceAccountClient
//				.listFaceAccountByIdList(new CommonIdListRequestDTO(accountIdList));
//		List<FaceAccountInfoBO> faceAccountInfoList = FeignUtil.formatArrayClass(webApiReturnResultModel,
//				FaceAccountInfoBO.class);
//
//		faceAccountInfoList.stream().forEach(q -> {
//			accountFaceMap.put(q.getAccountId(), q.getImageName());
//		});
//		return accountFaceMap;
//	}

	@Override
	public List<? extends IOrganizeModel> accessedOrganizeList(List<? extends IOrganizeModel> organizeModelList)
			throws WuXiHuaJieFeignError {
		List<String> organizeIdList = organizeModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getOrganizeId()))
				.map(q -> q.getOrganizeId()).distinct().collect(Collectors.toList());
		Map<String, String> accessedOrganize = new HashMap<String, String>();
		if (organizeIdList.size() > 0) {
			accessedOrganize = accessedOrganize(organizeIdList);
		}
		for (IOrganizeModel organizeModelTemp : organizeModelList) {
			if (!Strings.isNullOrEmpty(organizeModelTemp.getOrganizeId())) {
				organizeModelTemp.setOrganizeName(accessedOrganize.get(organizeModelTemp.getOrganizeId()));
			}
		}
		return organizeModelList;
	}

	@Override
	public List<? extends IOrganizeChildrenOrganizeModel> accessedOrganizeChildrenList(
			List<? extends IOrganizeChildrenOrganizeModel> organizeChildrenOrgModelList) throws WuXiHuaJieFeignError {
		List<String> organizeIdList = organizeChildrenOrgModelList.stream()
				.filter(q -> !Strings.isNullOrEmpty(q.getOrganizeId())).map(q -> q.getOrganizeId()).distinct()
				.collect(Collectors.toList());
		List<String> organizeChildrenIdList = organizeChildrenOrgModelList.stream()
				.filter(q -> !Strings.isNullOrEmpty(q.getOrganizeId())).map(q -> q.getChildOrganizeId()).distinct()
				.collect(Collectors.toList());

		Map<String, String> accessedOrganize = new HashMap<String, String>();
		Map<String, String> accessedChildOrganize = new HashMap<String, String>();

		if (organizeIdList.size() > 0) {
			accessedOrganize = accessedOrganize(organizeIdList);
			accessedChildOrganize = accessedOrganize(organizeChildrenIdList);
		}
		for (IOrganizeChildrenOrganizeModel organizeModelTemp : organizeChildrenOrgModelList) {
			if (!Strings.isNullOrEmpty(organizeModelTemp.getOrganizeId())) {
				organizeModelTemp.setOrganizeName(accessedOrganize.get(organizeModelTemp.getOrganizeId()));
				organizeModelTemp
						.setChildOrganizeName(accessedChildOrganize.get(organizeModelTemp.getChildOrganizeId()));
			}
		}

		return organizeChildrenOrgModelList;
	}

	@Override
	public List<? extends ISceneModel> accessedSceneList(List<? extends ISceneModel> sceneModelList)
			throws WuXiHuaJieFeignError {

		List<String> sceneIdList = sceneModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getSceneId()))
				.map(q -> q.getSceneId()).distinct().collect(Collectors.toList());

		Map<String, String> accessedScene = new HashMap<String, String>();
		if (sceneIdList.size() > 0) {
			accessedScene = accessedScene(sceneIdList);
		}
		for (ISceneModel sceneModelTemp : sceneModelList) {
			if (!Strings.isNullOrEmpty(sceneModelTemp.getSceneId())) {
				sceneModelTemp.setSceneName(accessedScene.get(sceneModelTemp.getSceneId()));
			}
		}
		return sceneModelList;

	}

	@Override
	public List<? extends IOrganizeSceneModel> accessedOrganizeSceneList(
			List<? extends IOrganizeSceneModel> organizeSceneList) throws WuXiHuaJieFeignError {
		List<String> organizeIdList = organizeSceneList.stream().filter(q -> !Strings.isNullOrEmpty(q.getOrganizeId()))
				.map(q -> q.getOrganizeId()).distinct().collect(Collectors.toList());
		Map<String, String> accessedOrganize = new HashMap<String, String>();
		if (organizeIdList.size() > 0) {
			accessedOrganize = accessedOrganize(organizeIdList);
		}

		List<String> sceneIdList = organizeSceneList.stream().filter(q -> !Strings.isNullOrEmpty(q.getSceneId()))
				.map(q -> q.getSceneId()).distinct().collect(Collectors.toList());

		Map<String, String> accessedScene = new HashMap<String, String>();
		if (sceneIdList.size() > 0) {
			accessedScene = accessedScene(sceneIdList);
		}
		for (IOrganizeSceneModel organizeSceneModelTemp : organizeSceneList) {
			if (!Strings.isNullOrEmpty(organizeSceneModelTemp.getOrganizeId())) {
				organizeSceneModelTemp.setOrganizeName(accessedOrganize.get(organizeSceneModelTemp.getOrganizeId()));
			}
			if (!Strings.isNullOrEmpty(organizeSceneModelTemp.getSceneId())) {
				organizeSceneModelTemp.setSceneName(accessedScene.get(organizeSceneModelTemp.getSceneId()));
			}
		}
		return organizeSceneList;
	}

	@Override
	public List<? extends IPlatformEnumModel> accessedPlatformEnumList(
			List<? extends IPlatformEnumModel> platformEnumModelList, PlatformEnum platformEnum)
			throws WuXiHuaJieFeignError {
		Map<Integer, String> accessedPlatformMap = accessedPlatformEnum(platformEnum);
		for (IPlatformEnumModel platformEnumModelTemp : platformEnumModelList) {
			if (platformEnumModelTemp.getEnumType() != null) {
				platformEnumModelTemp.setEnumTypeName(accessedPlatformMap.get(platformEnumModelTemp.getEnumType()));
			}
		}
		return platformEnumModelList;
	}

	@Override
	public List<? extends IOrganizeUserModel> accessedOrganizeUserList(
			List<? extends IOrganizeUserModel> organizeUserModelList) throws WuXiHuaJieFeignError {
		List<String> organizeIdList = organizeUserModelList.stream()
				.filter(q -> !Strings.isNullOrEmpty(q.getOrganizeId())).map(q -> q.getOrganizeId()).distinct()
				.collect(Collectors.toList());
		Map<String, String> accessedOrganize = new HashMap<String, String>();
		if (organizeIdList.size() > 0) {
			accessedOrganize = accessedOrganize(organizeIdList);
		}

		List<String> userIdList = organizeUserModelList.stream()
				.filter(q -> !Strings.isNullOrEmpty(q.getCreatorUserId())).map(q -> q.getCreatorUserId()).distinct()
				.collect(Collectors.toList());

		Map<String, String> accessedUser = new HashMap<String, String>();
		if (userIdList.size() > 0) {
			accessedUser = accessedUser(userIdList);
		}

		for (IOrganizeUserModel organizeUserModelTemp : organizeUserModelList) {
			if (!Strings.isNullOrEmpty(organizeUserModelTemp.getOrganizeId())) {
				organizeUserModelTemp.setOrganizeName(accessedOrganize.get(organizeUserModelTemp.getOrganizeId()));
			}
			if (!Strings.isNullOrEmpty(organizeUserModelTemp.getCreatorUserId())) {
				organizeUserModelTemp.setCreatorUserName(accessedUser.get(organizeUserModelTemp.getCreatorUserId()));
			}
		}

		return organizeUserModelList;
	}

//	@Override
//	public List<? extends IFaceImageModel> accessedFaceImageList(List<? extends IFaceImageModel> faceImageList)
//			throws WuXiHuaJieFeignError {
//		List<String> accountIdList = faceImageList.stream().filter(q -> !Strings.isNullOrEmpty(q.getAccountId()))
//				.map(q -> q.getAccountId()).distinct().collect(Collectors.toList());
//		Map<String, String> accountFaceMap = accessedAccountFaceImage(accountIdList);
//
//		for (IFaceImageModel faceImageModelTemp : faceImageList) {
//			if (!Strings.isNullOrEmpty(faceImageModelTemp.getAccountId())) {
//				faceImageModelTemp.setImageName(accountFaceMap.get(faceImageModelTemp.getAccountId()));
//			}
//		}
//		return faceImageList;
//	}

}
