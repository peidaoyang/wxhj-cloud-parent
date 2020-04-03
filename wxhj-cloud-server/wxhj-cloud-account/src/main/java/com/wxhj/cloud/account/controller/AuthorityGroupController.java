/** 
 * @fileName: AuthoritySceneController.java  
 * @author: pjf
 * @date: 2019年11月18日 上午11:28:48 
 */

package com.wxhj.cloud.account.controller;

import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.account.domain.AuthorityGroupInfoDO;
import com.wxhj.cloud.account.domain.AutoSynchroAuthorityDO;
import com.wxhj.cloud.account.domain.view.ViewAutoSynchroAuthorityDO;
import com.wxhj.cloud.account.service.AuthorityGroupInfoService;
import com.wxhj.cloud.account.service.AutoSynchroAuthorityService;
import com.wxhj.cloud.account.service.MapAccountAuthorityService;
import com.wxhj.cloud.account.service.MapAuthoritySceneService;
import com.wxhj.cloud.account.service.ViewAutoSynchroAuthorityService;
import com.wxhj.cloud.account.vo.ListAuthorityGroupPageByTypeVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.request.AutoSynchroAuthRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageByTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageRequestDTO;
import com.wxhj.cloud.feignClient.account.request.OptionalAuthorityGroupListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.AuthorityGroupVO;
import com.wxhj.cloud.feignClient.account.vo.AutoSynchroAuthVO;
import com.wxhj.cloud.feignClient.account.vo.ListAuthorityGroupPageVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.vo.DropDownListControlVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

/**
 * @className AuthoritySceneController.java
 * @author pjf
 * @date 2019年11月18日 上午11:28:48
 */
@RestController
@RequestMapping("/authorityGroup")
@Slf4j
public class AuthorityGroupController implements AuthorityGroupClient {
	@Resource
	AuthorityGroupInfoService authorityGroupInfoService;
	@Resource
	MapAuthoritySceneService mapAuthoritySceneService;
	@Resource
	MapAccountAuthorityService mapAccountAuthorityService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	AutoSynchroAuthorityService autoSynchroAuthorityService;
	@Resource
	ViewAutoSynchroAuthorityService viewAutoSynchroAuthorityService;

	@Override
	@ApiOperation("根据组织查询权限组信息")
	@PostMapping("/optionalAuthorityGroupList")
	public WebApiReturnResultModel optionalAuthorityGroupList(
			@Validated @RequestBody OptionalAuthorityGroupListRequestDTO optionalAuthorityGroupListRequest) {
		List<AuthorityGroupInfoDO> authorityGroupInfoList = authorityGroupInfoService.listByOrgAndType(optionalAuthorityGroupListRequest.getOrganizeId(),optionalAuthorityGroupListRequest.getType());
		List<DropDownListControlVO> dropDownListControlList = authorityGroupInfoList.stream()
				.map(q -> new DropDownListControlVO(q.getId(), q.getFullName(), q.getOrganizeId()))
				.collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(dropDownListControlList);
	}

	@SuppressWarnings("unchecked")
	@ApiOperation("分页查询权限组信息")
	@PostMapping("/listAuthorityGroupPage")
	@Override
	public WebApiReturnResultModel listAuthorityGroupPage(@RequestBody ListAuthorityGroupPageRequestDTO listAuthorityGroupPage) {
//		PageInfo<AuthorityGroupInfoDO> authorityList = authorityGroupInfoService.listByFullAndOrganizeAndTypePage(
//				listAuthorityGroupPage.getNameValue(), listAuthorityGroupPage.getOrganizeId(), listAuthorityGroupPage.getType(),listAuthorityGroupPage);
		
		PageInfo<ViewAutoSynchroAuthorityDO> authorityList = viewAutoSynchroAuthorityService.listByFullAndOrganizeAndTypePage(
				listAuthorityGroupPage.getNameValue(), listAuthorityGroupPage.getOrganizeId(), listAuthorityGroupPage.getType(),listAuthorityGroupPage);
		
		List<ListAuthorityGroupPageVO> authorityReponseList = authorityList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ListAuthorityGroupPageVO.class)).collect(Collectors.toList());
		
		for(ListAuthorityGroupPageVO pageByTypeVO: authorityReponseList) {
			List<String> listScene = mapAuthoritySceneService.listByAuthorityId(pageByTypeVO.getId())
					.stream().map(q -> q.getSceneId()).collect(Collectors.toList());
			List<String> accountlist = mapAccountAuthorityService.listByAuthorityId(pageByTypeVO.getId())
					.stream().map(q -> q.getAccountId()).collect(Collectors.toList());
			pageByTypeVO.setScencIdList(listScene);
			pageByTypeVO.setAccountIdList(accountlist);
		}
		
		try {
			authorityReponseList = (List<ListAuthorityGroupPageVO>) accessedRemotelyService
					.accessedOrganizeList(authorityReponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(authorityList,
				authorityReponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@Override
	@ApiOperation("修改和编辑权限组")
	@PostMapping("/submitAuthorityGroupInfo")
	@Transactional
	public WebApiReturnResultModel submitAuthorityGroupInfo(
			@Validated @RequestBody SubmitAuthorityGroupInfoRequestDTO submitAuthorityGroupInfoRequest) {
		AuthorityGroupInfoDO authorityGroupInfo = dozerBeanMapper.map(submitAuthorityGroupInfoRequest,
				AuthorityGroupInfoDO.class);
		String id = null;
		if (Strings.isNullOrEmpty(submitAuthorityGroupInfoRequest.getId())) {
			id = authorityGroupInfoService.insertCascade(authorityGroupInfo,
					submitAuthorityGroupInfoRequest.getSceneIdList(),
					submitAuthorityGroupInfoRequest.getAccountIdList());
		} else {
			id = submitAuthorityGroupInfoRequest.getId();
			authorityGroupInfoService.updateCascade(authorityGroupInfo,
					submitAuthorityGroupInfoRequest.getSceneIdList(),
					submitAuthorityGroupInfoRequest.getAccountIdList());
		}
		AuthorityGroupVO authorityGroupVO = dozerBeanMapper.map(authorityGroupInfo,AuthorityGroupVO.class);
		authorityGroupVO.setId(id);
		//设置是否权限同步
		autoSynchroAuthorityService.insert(new AutoSynchroAuthorityDO(id,submitAuthorityGroupInfoRequest.getAutoSynchro()));
		
		return WebApiReturnResultModel.ofSuccess(id);
	}
	

	
	@ApiOperation("自动同步权限组信息查询")
	@PostMapping("/autoSynchroAuth")
	@Override
	public WebApiReturnResultModel autoSynchroAuth(@RequestBody @Validated AutoSynchroAuthRequestDTO autoSynchroAuth) {
		List<AutoSynchroAuthVO> list = viewAutoSynchroAuthorityService.listByOrgId(autoSynchroAuth.getOrganizeId())
				.stream().map(q-> dozerBeanMapper.map(q, AutoSynchroAuthVO.class)).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(list);
	}
	
	
	@Override
	@ApiOperation("删除权限组")
	@PostMapping("/deleteAuthorityGroupInfo")
	@Transactional
	public WebApiReturnResultModel deleteAuthorityGroupInfo(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		String id = commonIdRequest.getId();
		mapAuthoritySceneService.deleteCascade(id, null);
		mapAccountAuthorityService.deleteCascade(id, null);
		authorityGroupInfoService.deleteById(id);
		autoSynchroAuthorityService.delete(id);
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation(value = "查询权限组", response = ListAuthorityGroupPageByTypeVO.class, responseContainer = "List")
	@PostMapping("/listAuthorityGroupPageByType")
	@Override
	public WebApiReturnResultModel listAuthorityGroupPageByType(
			@RequestBody ListAuthorityGroupPageByTypeRequestDTO listAuthorityGroupPage) {
//		PageInfo<AuthorityGroupInfoDO> authorityList = authorityGroupInfoService.listByFullAndOrganizeAndTypePage(
//				listAuthorityGroupPageRequest.getNameValue(), listAuthorityGroupPageRequest.getOrganizeId(),
//				listAuthorityGroupPageRequest.getType(), listAuthorityGroupPageRequest);
		PageInfo<ViewAutoSynchroAuthorityDO> authorityList = viewAutoSynchroAuthorityService.listByFullAndOrganizeAndTypePage(
				listAuthorityGroupPage.getNameValue(), listAuthorityGroupPage.getOrganizeId(), listAuthorityGroupPage.getType(),listAuthorityGroupPage);

		
		List<ListAuthorityGroupPageByTypeVO> authorityReponseList = authorityList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ListAuthorityGroupPageByTypeVO.class)).collect(Collectors.toList());

		for (ListAuthorityGroupPageByTypeVO pageByTypeVO : authorityReponseList) {
			List<String> listScene = mapAuthoritySceneService.listByAuthorityId(pageByTypeVO.getId()).stream()
					.map(q -> q.getSceneId()).collect(Collectors.toList());
			List<String> accountlist = mapAccountAuthorityService.listByAuthorityId(pageByTypeVO.getId()).stream()
					.map(q -> q.getAccountId()).collect(Collectors.toList());
			pageByTypeVO.setScencIdList(listScene);
			pageByTypeVO.setAccountIdList(accountlist);
		}
		try {
			authorityReponseList = (List<ListAuthorityGroupPageByTypeVO>) accessedRemotelyService
					.accessedOrganizeList(authorityReponseList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(authorityList,
				authorityReponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

//	@Override
//	@ApiOperation("已选场景id列表")
//	@PostMapping("/selectedSceneList")
//	public WebApiReturnResultModel selectedSceneList(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
//		MapAuthoritySceneDO mapAuthorityScene = new MapAuthoritySceneDO();
//		mapAuthorityScene.setAuthorityGroupId(commonIdRequest.getId());
//		List<MapAuthoritySceneDO> mapAuthoritySceneList = mapAuthoritySceneService.list(mapAuthorityScene);
//		List<String> sceneIdList = mapAuthoritySceneList.stream().map(q -> q.getSceneId()).collect(Collectors.toList());
//		return WebApiReturnResultModel.ofSuccess(sceneIdList);
//	}

//	@Override
//	@ApiOperation("组织及子组织可选权限组id")
//	@PostMapping("/optionalAuthList")
//	public WebApiReturnResultModel optionalAuthList() {
//		List<AuthorityGroupInfoVO> authorityGroupInfoList = authorityGroupInfoService.list().stream()
//				.map(q -> dozerBeanMapper.map(q, AuthorityGroupInfoVO.class)).collect(Collectors.toList());
//		return WebApiReturnResultModel.ofSuccess(authorityGroupInfoList);
//	}

	@ApiOperation("根据组织列表获取权限组")
	@PostMapping("/optionalAuthByOrganList")
	@Override
	public WebApiReturnResultModel optionalAuthByOrganList(
			@RequestBody CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest) {
		List<AuthorityGroupInfoDO> listByOrganizeList = authorityGroupInfoService
				.listByOrganizeList(commonOrganizeIdListRequest.getOrganizeIdList());
		return WebApiReturnResultModel.ofSuccess(listByOrganizeList);
	}

}
