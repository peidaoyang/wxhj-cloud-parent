package com.wxhj.cloud.platform.controller.system;

import java.util.ArrayList;
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

import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.vo.TreeListControlVO;
import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;
import com.wxhj.cloud.platform.domain.SysRoleAuthorizeDO;
import com.wxhj.cloud.platform.domain.SysRoleDO;
import com.wxhj.cloud.platform.domain.view.ViewRoleOrganizeDO;
import com.wxhj.cloud.platform.dto.request.SysRoleSubmitRequestDTO;
import com.wxhj.cloud.platform.service.SysModuleService;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;
import com.wxhj.cloud.platform.service.SysRoleService;
import com.wxhj.cloud.platform.service.ViewRoleOrganizeService;
import com.wxhj.cloud.platform.util.ViewControlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @className RoleController.java
 * @author pjf
 * @date 2019年11月5日 下午4:56:08
 */
@RestController
@RequestMapping("/systemManage/role")
@Api(tags = "角色接口")
public class RoleController {
	@Resource
	SysRoleService sysRoleService;
	@Resource
	ViewRoleOrganizeService viewRoleOrganizeService;
	@Resource
	SysRoleAuthorizeService sysRoleAuthorizeService;
	@Resource
	SysModuleService sysModuleService;
	@Resource
	SysOrganizeAuthorizeService sysOrganizeAuthorizeService;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	
	@ApiOperation(value = "分页获取角色显示列表(只显示已选组织下角色)",response=ViewRoleOrganizeDO.class)
	@PostMapping("/sysRoleListByOrgId")
	public WebApiReturnResultModel sysRoleListByOrgId(@Validated @RequestBody() CommonListPageRequestDTO commonListPageRequest) {
		List<String> orgIdList = sysOrganizeService.selectByParentIdRecursion(commonListPageRequest.getOrganizeId()).stream().map(q -> q.getId()).collect(Collectors.toList());
		orgIdList.add(commonListPageRequest.getOrganizeId());
		
		return WebApiReturnResultModel.ofSuccess(viewRoleOrganizeService.listByOrgId(commonListPageRequest.getNameValue(), orgIdList, commonListPageRequest));
	}
	
	@Transactional
	@ApiOperation("添加或修改角色")
	@PostMapping("/submitSysRole")
	public WebApiReturnResultModel submitSysRole(@Validated @RequestBody() SysRoleSubmitRequestDTO sysRoleSubmit) {
		String userId = sysRoleSubmit.getUserId();
		SysRoleDO sysRoleDO = dozerBeanMapper.map(sysRoleSubmit, SysRoleDO.class);
		List<String> newModuleIdList = sysRoleSubmit.getModuleIdList();
		List<String> addModuleIdList = new ArrayList<>();
		String id;
		if (Strings.isNullOrEmpty(sysRoleSubmit.getId())) {
			String roleId = sysRoleService.insert(sysRoleDO, userId);
			addModuleIdList = newModuleIdList;
			if (addModuleIdList.size() > 0) {
				List<SysRoleAuthorizeDO> sysRoleAuthorizeList = addModuleIdList.stream().map(q -> {
					SysRoleAuthorizeDO sysRoleAuthorizeTemp = new SysRoleAuthorizeDO();
					sysRoleAuthorizeTemp.setRoleId(roleId);
					sysRoleAuthorizeTemp.setModuleId(q);
					return sysRoleAuthorizeTemp;
				}).collect(Collectors.toList());
				sysRoleAuthorizeService.insertList(sysRoleAuthorizeList, userId);
			}
			id = roleId;
		} else {
			sysRoleService.update(sysRoleDO, userId);
			List<SysRoleAuthorizeDO> sysRoleAuthorizeListTemp = sysRoleAuthorizeService
					.selectByRoleId(sysRoleSubmit.getId());
			List<String> oldModuleIdList = sysRoleAuthorizeListTemp.stream().map(q -> q.getModuleId())
					.collect(Collectors.toList());

			addModuleIdList = newModuleIdList.stream().filter(q -> !oldModuleIdList.contains(q))
					.collect(Collectors.toList());
			List<String> deleteModuleIdList = oldModuleIdList.stream().filter(q -> !newModuleIdList.contains(q))
					.collect(Collectors.toList());
			if (deleteModuleIdList.size() > 0) {
				sysRoleAuthorizeService.deleteByIdList(
						sysRoleAuthorizeListTemp.stream().filter(q -> deleteModuleIdList.contains(q.getModuleId()))
								.map(q -> q.getId()).collect(Collectors.toList()));
			}
			if (addModuleIdList.size() > 0) {
				List<SysRoleAuthorizeDO> sysRoleAuthorizeList = addModuleIdList.stream().map(q -> {
					SysRoleAuthorizeDO sysRoleAuthorizeTemp = new SysRoleAuthorizeDO();
					sysRoleAuthorizeTemp.setRoleId(sysRoleSubmit.getId());
					sysRoleAuthorizeTemp.setModuleId(q);
					return sysRoleAuthorizeTemp;
				}).collect(Collectors.toList());
				sysRoleAuthorizeService.insertList(sysRoleAuthorizeList, userId);
			}
			id = sysRoleDO.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}
	
	@ApiOperation(value="角色全选菜单列表",response=TreeListControlVO.class)
	@PostMapping("/sysRoleTreeListControl")
	public WebApiReturnResultModel sysRoleTreeListControl(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
		List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService
				.selectByOrganizeId(commonIdRequest.getId());
		List<String> moduleIdList = sysOrganizeAuthorizeList.stream().map(q -> q.getModuleId())
				.collect(Collectors.toList());
		
		List<SysModuleDO> sysModuleList = sysModuleService.selectByidList(moduleIdList);

		List<TreeListControlVO> treeListControlVO = ViewControlUtil.buildTreeListControl(sysModuleList, "0");

		return WebApiReturnResultModel.ofSuccess(treeListControlVO);
	}
	
	
	@ApiOperation("角色已选菜单列表")
	@PostMapping("/sysRoleModuleList")
	public WebApiReturnResultModel sysRoleModuleList(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
		List<String> moduleIdList = sysRoleAuthorizeService.selectByRoleId(commonIdRequest.getId())
				.stream().map(q -> q.getModuleId()).collect(Collectors.toList());

		return WebApiReturnResultModel.ofSuccess(moduleIdList);
	}
	
	@Transactional
	@ApiOperation("删除角色")
	@PostMapping("/deleteSysRole")
	public WebApiReturnResultModel deleteSysRole(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
		sysRoleService.delete(commonIdRequest.getId());
		sysRoleAuthorizeService.deleteByRoleId(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}

}
