/** 
 * @fileName: AccessedRemotelyServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月6日 下午3:19:01 
 */

package com.wxhj.cloud.component.service.impl;

import com.google.common.base.Strings;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.PlatformEnum;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.OrganizeCardPriorityClient;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.account.vo.AutoSynchroAuthVO;
import com.wxhj.cloud.feignClient.account.vo.OrganizeCardPriorityVO;
import com.wxhj.cloud.feignClient.bo.IAccountOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IAuthoritySynchroModel;
import com.wxhj.cloud.feignClient.bo.ICardNameOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IDeviceRecordModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeChildrenOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeUserModel;
import com.wxhj.cloud.feignClient.bo.IPlatformEnumModel;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.platform.EnumManageClient;
import com.wxhj.cloud.feignClient.platform.OrganizeClient;
import com.wxhj.cloud.feignClient.platform.SceneClient;
import com.wxhj.cloud.feignClient.platform.UserClient;
import com.wxhj.cloud.feignClient.platform.bo.EnumManageBO;
import com.wxhj.cloud.feignClient.platform.bo.SceneInfoBO;
import com.wxhj.cloud.feignClient.platform.bo.SysOrganizeBO;
import com.wxhj.cloud.feignClient.platform.bo.UserByIdListBO;
import com.wxhj.cloud.feignClient.platform.request.EnumTypeListRequestDTO;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

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
	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	AccountClient accountClient;
	@Resource
	OrganizeCardPriorityClient organizeCardPriorityClient;

	private Map<String, String> accessedOrganize(List<String> organizeList) throws WuXiHuaJieFeignError {
		Map<String, String> organizeMap = new HashMap<String, String>();
		CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest = new CommonOrganizeIdListRequestDTO(organizeList);

		WebApiReturnResultModel webApiReturnResultModel = organizeClient.listOrganizeByIdList(commonOrganizeIdListRequest);
		List<SysOrganizeBO> sysOrganizeList = FeignUtil.formatArrayClass(webApiReturnResultModel, SysOrganizeBO.class);
		sysOrganizeList.stream().forEach(q -> {
			organizeMap.put(q.getId(), q.getFullName());
		});
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
		return platformEnumMap;
	}


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

//	@Override
//	public List<? extends ISceneModel> accessedSceneList(List<? extends ISceneModel> sceneModelList)
//			throws WuXiHuaJieFeignError {
//
//		List<String> sceneIdList = sceneModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getSceneId()))
//				.map(q -> q.getSceneId()).distinct().collect(Collectors.toList());
//
//		Map<String, String> accessedScene = new HashMap<String, String>();
//		if (sceneIdList.size() > 0) {
//			accessedScene = accessedScene(sceneIdList);
//		}
//		for (ISceneModel sceneModelTemp : sceneModelList) {
//			if (!Strings.isNullOrEmpty(sceneModelTemp.getSceneId())) {
//				sceneModelTemp.setSceneName(accessedScene.get(sceneModelTemp.getSceneId()));
//			}
//		}
//		return sceneModelList;
//
//	}

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
//				organizeSceneModelTemp.setSceneName(accessedScene.get(organizeSceneModelTemp.getSceneId()));
				if(organizeSceneModelTemp.getSceneId().equals("*")){
					organizeSceneModelTemp.setSceneName("全部场景");
				}else{
					organizeSceneModelTemp.setSceneName(accessedScene.get(organizeSceneModelTemp.getSceneId()));
				}
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
	public List<? extends IAccountOrganizeModel> accessedAccountOrganizeList(
			List<? extends IAccountOrganizeModel> organizeUserModelList) throws WuXiHuaJieFeignError {
		List<String> accountIds = organizeUserModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getAccountId()))
				.map(IAccountOrganizeModel::getAccountId).distinct().collect(Collectors.toList());
		Map<String, AccountInfoVO> accountInfoMap = accessAccountInfo(accountIds);

		organizeUserModelList.forEach(item -> {
			AccountInfoVO accountInfo = accountInfoMap.get(item.getAccountId());
			if (accountInfo != null) {
				item.setOrganizeId(accountInfo.getOrganizeId());
				item.setChildOrganizeId(accountInfo.getChildOrganizeId());
			}
		});
		return organizeUserModelList;
	}

	public Map<String, AccountInfoVO> accessAccountInfo(List<String> accountIds) throws WuXiHuaJieFeignError {
		CommonIdListRequestDTO commonIdListRequest = new CommonIdListRequestDTO();
		commonIdListRequest.setIdList(accountIds);
		WebApiReturnResultModel webApiReturnResultModel = accountClient.listAccount(commonIdListRequest);
		List<AccountInfoVO> accountInfos = FeignUtil.formatArrayClass(webApiReturnResultModel, AccountInfoVO.class);
		return accountInfos.stream().collect(Collectors.toMap(AccountInfoVO::getAccountId, v -> v));
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

	@Override
	public List<? extends IAuthoritySynchroModel> accessdAuthoritySynchroList(
			List<? extends IAuthoritySynchroModel> authoritySynchroModelList) throws WuXiHuaJieFeignError {
		List<String> authorityIdList = authoritySynchroModelList.stream().map(q-> q.getId()).collect(Collectors.toList());
		Map<String, Integer> accessedAuhority = new HashMap<String, Integer>();
		if(authorityIdList.size()>0){
			accessedAuhority = accessAuthority(authorityIdList);
		}
		for(IAuthoritySynchroModel iAuthoritySynchroModel: authoritySynchroModelList){
			iAuthoritySynchroModel.setAutoSynchro(accessedAuhority.get(iAuthoritySynchroModel.getId()));
		}
		return authoritySynchroModelList;
	}


	private Map<String, Integer> accessAuthority(List<String> authorityIdList) throws WuXiHuaJieFeignError{
		Map<String, Integer> authorityMap = new HashMap<String, Integer>();
		WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient.autoSynchroAuth(new CommonIdListRequestDTO(authorityIdList));
		List<AutoSynchroAuthVO> autoSynchroAuthVOListList = FeignUtil.formatArrayClass(webApiReturnResultModel, AutoSynchroAuthVO.class);
		if(autoSynchroAuthVOListList != null){
			autoSynchroAuthVOListList.stream().forEach(q -> { authorityMap.put(q.getId(),q.getAutoSynchro()); });
		}
		return authorityMap;
	}

	@Override
	public List<? extends ICardNameOrganizeModel> accessCardNameList(List<? extends ICardNameOrganizeModel> cardNameModelList) throws WuXiHuaJieFeignError {
		List<String> organizeIdList = cardNameModelList.stream().filter(q -> q.getOrganizeId() != null)
				.map(ICardNameOrganizeModel::getOrganizeId).collect(Collectors.toList());
		if (organizeIdList.size() > 0) {
			CommonOrganizeRequestDTO commonIdRequestDTO = new CommonOrganizeRequestDTO();
			commonIdRequestDTO.setOrganizeId(organizeIdList.get(0));
			WebApiReturnResultModel webApiReturnResultModel = organizeCardPriorityClient.listOrganizeCardPriority(commonIdRequestDTO);
			List<OrganizeCardPriorityVO> organizeCardPriorities = FeignUtil.formatArrayClass(webApiReturnResultModel, OrganizeCardPriorityVO.class);
			Map<Integer, String> map = organizeCardPriorities.stream().collect(Collectors.toMap(OrganizeCardPriorityVO::getCardType, OrganizeCardPriorityVO::getCardName));
			cardNameModelList.forEach(q -> q.setCardName(map.get(q.getCardType())));
		}
		return cardNameModelList;
	}

	@Override
	public List<? extends IDeviceRecordModel> accessDeviceRecordList(List<? extends IDeviceRecordModel> deviceRecordModelList) throws WuXiHuaJieFeignError {
		List<String> organizeIdList = deviceRecordModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getOrganizeId()))
				.map(q -> q.getOrganizeId()).distinct().collect(Collectors.toList());
		Map<String, String> accessedOrganize = new HashMap<String, String>();
		if (organizeIdList.size() > 0) {
			accessedOrganize = accessedOrganize(organizeIdList);
		}

		List<String> sceneIdList = deviceRecordModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getSceneId()))
				.map(q -> q.getSceneId()).distinct().collect(Collectors.toList());
		Map<String, String> accessedScene = new HashMap<String, String>();
		if (sceneIdList.size() > 0) {
			accessedScene = accessedScene(sceneIdList);
		}

		List<String> accountIdList = deviceRecordModelList.stream().filter(q -> !Strings.isNullOrEmpty(q.getAccountId()))
				.map(q -> q.getAccountId()).distinct().collect(Collectors.toList());

		Map<String, AccountInfoVO> accountInfoMap = new HashMap<String, AccountInfoVO>();
		if (accountIdList.size() > 0) {
			accountInfoMap = accessAccountInfo(accountIdList);
		}

		for (IDeviceRecordModel iDeviceRecordModel : deviceRecordModelList) {
			if (!Strings.isNullOrEmpty(iDeviceRecordModel.getOrganizeId())) {
				iDeviceRecordModel.setOrganizeName(accessedOrganize.get(iDeviceRecordModel.getOrganizeId()));
			}
			if (!Strings.isNullOrEmpty(iDeviceRecordModel.getSceneId())) {
				if(iDeviceRecordModel.getSceneId().equals("*")){
					iDeviceRecordModel.setSceneName("全部场景");
				}else{
					iDeviceRecordModel.setSceneName(accessedScene.get(iDeviceRecordModel.getSceneId()));
				}
			}
			if(!Strings.isNullOrEmpty(iDeviceRecordModel.getAccountId())){
				if(accountInfoMap.size()>0){
					iDeviceRecordModel.setAccountName(accountInfoMap.get(iDeviceRecordModel.getAccountId()).getName());
				}
			}

		}
		return deviceRecordModelList;
	}
}
