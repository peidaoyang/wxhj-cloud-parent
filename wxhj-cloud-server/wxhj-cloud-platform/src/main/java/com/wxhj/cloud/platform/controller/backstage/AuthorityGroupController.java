/** 
 * @fileName: AuthorityGroupController.java  
 * @author: pjf
 * @date: 2019年11月19日 上午11:01:31 
 */

package com.wxhj.cloud.platform.controller.backstage;


import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageRequestDTO;
import com.wxhj.cloud.feignClient.account.request.OptionalAuthorityGroupListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.ListAuthorityGroupPageVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.vo.DropDownListControlVO;
import com.wxhj.cloud.platform.service.SysOrganizeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className AuthorityGroupController.java
 * @author pjf
 * @date 2019年11月19日 上午11:01:31
 */
@RestController
@RequestMapping("/systemManage/authorityGroup")
@Api(tags = "权限组接口")
public class AuthorityGroupController {
	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	
	@ApiOperation(value = "根据组织查询权限组信息",response = DropDownListControlVO.class)
	@PostMapping("/optionalAuthorityGroupList")
	@LcnTransaction
	public WebApiReturnResultModel optionalAuthorityGroupList(
			@Validated @RequestBody OptionalAuthorityGroupListRequestDTO optionalAuthorityGroupListRequest) {
		return authorityGroupClient.optionalAuthorityGroupList(optionalAuthorityGroupListRequest);
	}
	
	@LcnTransaction
	@ApiOperation(value="分页查询权限组信息",response=ListAuthorityGroupPageVO.class)
	@PostMapping("/listAuthorityGroupPage")
	public WebApiReturnResultModel listAuthorityGroupPage(
			@Validated @RequestBody ListAuthorityGroupPageRequestDTO listAuthorityGroupPage) {
		return authorityGroupClient.listAuthorityGroupPage(listAuthorityGroupPage);
	}
	
	@LcnTransaction
	@ApiOperation("修改权限组")
	@PostMapping("/submitAuthorityGroupInfo")
	public WebApiReturnResultModel submitAuthorityGroupInfo(
			@Validated @RequestBody SubmitAuthorityGroupInfoRequestDTO submitAuthorityGroupInfoRequest) {
		return authorityGroupClient.submitAuthorityGroupInfo(submitAuthorityGroupInfoRequest);
	}
	
	@LcnTransaction
	@ApiOperation("删除权限组")
	@PostMapping("/deleteAuthorityGroupInfo")
	public WebApiReturnResultModel deleteAuthorityGroupInfo(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return authorityGroupClient.deleteAuthorityGroupInfo(commonIdRequest);
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
//	@ApiOperation("组织及子组织可选权限组id")
//	@PostMapping("/optionalAuthList")
//	@LcnTransaction
//	public WebApiReturnResultModel optionalAuthList(@RequestBody OptionalAuthListRequestDTO optionalAuthListRequest)
//			throws WuXiHuaJieFeignError {
//		List<OptionalAuthListVO> authList = sysOrganizeService
//				.selectByIdRecursion(optionalAuthListRequest.getCurrentOrgId(), optionalAuthListRequest.getOrgId())
//				.stream().map(q -> dozerBeanMapper.map(q, OptionalAuthListVO.class)).collect(Collectors.toList());
//		WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient.optionalAuthList();
//		if (!webApiReturnResultModel.resultSuccess()) {
//			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_DATA);
//		}
//		List<AuthorityGroupInfoVO> authGroupList = FeignUtil.formatArrayClass(webApiReturnResultModel,
//				AuthorityGroupInfoVO.class);
//
//		authList.forEach(q -> {
//			List<AuthorityGroupInfoVO> p = authGroupList.stream().filter(j -> j.getOrganizeId().equals(q.getId()))
//					.collect(Collectors.toList());
//			q.setAuthGroupList(p);
//		});
//
//		authList = authList.stream().filter(q -> q.getAuthGroupList() != null && q.getAuthGroupList().size() > 0)
//				.collect(Collectors.toList());
//		if (authList.size() == 0) {
//			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_DATA);
//		}
//		return WebApiReturnResultModel.ofSuccess(authList);
//	}

//	@LcnTransaction
//	@ApiOperation("已选场景id")
//	@PostMapping("/selectedSceneList")
//	public WebApiReturnResultModel selectedSceneList(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
//		return authorityGroupClient.selectedSceneList(commonIdRequest);
//	}
	
	
	


	//预留
//	@ApiOperation("获取可选权限组信息")
//	@PostMapping("/optionalAuthByOrganList")
//	public WebApiReturnResultModel optionalAuthByOrganList(
//			@Validated @RequestBody OptionalAuthByOrganListPlusRequestDTO optionalAuthByOrganListPlusRequest) {
////		List<SysOrganizeDO> listByIdMinToMax = sysOrganizeService.listByIdMinToMax(
////				optionalAuthByOrganListPlusRequest.getChildOrganizeId(),
////				optionalAuthByOrganListPlusRequest.getCurrentOrganizeId());
////		
//		List<SysOrganizeDO> listByIdMinToMax = sysOrganizeService.selectByIdRecursion(
//				optionalAuthByOrganListPlusRequest.getChildOrganizeId(),
//				optionalAuthByOrganListPlusRequest.getCurrentOrganizeId());
//		List<String> organizeIdList = listByIdMinToMax.stream().map(q -> q.getId()).collect(Collectors.toList());
//		WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient
//				.optionalAuthByOrganList(new CommonOrganizeIdListRequestDTO(organizeIdList));
//		List<AuthorityGroupInfoBO> authorityGroupInfoList = null;
//		try {
//			authorityGroupInfoList = FeignUtil.formatArrayClass(webApiReturnResultModel, AuthorityGroupInfoBO.class);
//		} catch (WuXiHuaJieFeignError e) {
//			return e.getWebApiReturnResultModel();
//		}
//		List<OptionalAuthByOrganVO> optionalAuthByOrganList = new ArrayList();
//		for (SysOrganizeDO sysOrganizeTemp : listByIdMinToMax) {
//			OptionalAuthByOrganVO optionalAuthByOrganTemp = dozerBeanMapper.map(sysOrganizeTemp,
//					OptionalAuthByOrganVO.class);
//			List<AuthorityGroupInfoBO> authorityGroupInfoTemp = authorityGroupInfoList.stream()
//					.filter(q -> q.getOrganizeId().equals(sysOrganizeTemp.getId())).collect(Collectors.toList());
//			optionalAuthByOrganTemp.setAuthGroupInfoList(authorityGroupInfoTemp);
//			optionalAuthByOrganList.add(optionalAuthByOrganTemp);
//		}
//		return WebApiReturnResultModel.ofSuccess(optionalAuthByOrganList);
//	}
}
