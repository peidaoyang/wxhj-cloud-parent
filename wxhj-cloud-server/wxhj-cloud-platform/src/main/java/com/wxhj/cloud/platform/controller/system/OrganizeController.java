/** 
s                                                                                                                                                                                     * @fileName: OrganizeController.java  
 * @author: pjf
 * @date: 2019年10月16日 下午2:13:56 
 */
package com.wxhj.cloud.platform.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.platform.dto.request.*;
import com.wxhj.cloud.platform.service.*;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.platform.OrganizeClient;
import com.wxhj.cloud.feignClient.vo.DropDownListControlVO;
import com.wxhj.cloud.feignClient.vo.TreeListControlVO;
import com.wxhj.cloud.platform.bo.SysOrgOptimizeBO;
import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.domain.SysRoleAuthorizeDO;
import com.wxhj.cloud.platform.domain.SysRoleDO;
import com.wxhj.cloud.platform.domain.view.ViewOrganizeInfoDO;
import com.wxhj.cloud.platform.organize.AccountRegisterOrganizeObserver;
import com.wxhj.cloud.platform.organize.OrganizeObserverable;
import com.wxhj.cloud.platform.organize.OrganizeVariableBO;
import com.wxhj.cloud.platform.organize.SysModuleOrganizeObserver;
import com.wxhj.cloud.platform.organize.SysRoleOrganizeObserver;
import com.wxhj.cloud.platform.organize.SysUserOrganizeObserver;
import com.wxhj.cloud.platform.util.ViewControlUtil;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className OrganizeController.java
 * @author pjf
 * @date 2019年10月16日 下午2:13:56
 */

@RestController
@RequestMapping("/systemManage/organize")
@Api(tags = "组织接口")
public class OrganizeController implements OrganizeClient {
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	SysOrganizeAuthorizeService sysOrganizeAuthorizeService;
	@Resource
	SysModuleService sysModuleService;
	@Resource
	SysRoleService sysRoleService;
	@Resource
	SysRoleAuthorizeService sysRoleAuthorizeService;
	@Resource
	SysUserService sysUserService;
	@Resource
	SysOrganizeAuthorizeTypeService sysOrganizeAuthorizeTypeService;

	@Resource
	ViewOrganizeInfoService viewOrganizeInfoService;

	@Resource
	SysRoleOrganizeObserver sysRoleOrganizeObserver;
	@Resource
	SysModuleOrganizeObserver sysModuleOrganizeObserver;
	@Resource
	AccountRegisterOrganizeObserver accountRegisterOrganizeObserver;
	@Resource
	SysUserOrganizeObserver sysUserOrganizeObserver;

	@Resource
	Mapper dozerBeanMapper;

	@ApiOperation(value = "组织分页查询", response = ViewOrganizeInfoDO.class)
	@PostMapping("/sysOrganize")
	public WebApiReturnResultModel sysOrganize(@RequestBody CommonListPageRequestDTO commonListPage) {
		return WebApiReturnResultModel.ofSuccess(viewOrganizeInfoService.select(commonListPage,
				commonListPage.getNameValue(), commonListPage.getOrganizeId()));
	}

	@ApiOperation(value = "组织菜单已选列表", response = String.class, responseContainer = "List")
	@PostMapping("/sysOrgAutModuleSelectList")
	public WebApiReturnResultModel sysOrgAutModuleSelectList(@RequestBody @Validated CommonIdRequestDTO commonId) {
		List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService
				.selectByOrganizeId(commonId.getId());
		List<String> itemList = sysOrganizeAuthorizeList.stream().map(q -> q.getModuleId())
				.collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(itemList);
	}

	@ApiOperation(value = "组织菜单全选列表", response = TreeListControlVO.class)
	@PostMapping("/sysOrgAutModuleParentTreeList")
	public WebApiReturnResultModel sysOrgAutModuleParentTreeList(@RequestBody @Validated CommonIdRequestDTO commonId) {
		SysOrganizeDO sysOrganizeDO = sysOrganizeService.selectById(commonId.getId());
		List<SysModuleDO> sysModuleList;
		if(sysOrganizeDO.getLayers() == 1){
			sysModuleList = sysModuleService.select();
		}else{
			List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService.selectByOrganizeId(sysOrganizeDO.getParentId());
			List<String> moduleIdList = sysOrganizeAuthorizeList.stream().map(q -> q.getModuleId()).collect(Collectors.toList());
			sysModuleList = sysModuleService.selectByidList(moduleIdList);
		}
		List<TreeListControlVO> treeListControlVO = ViewControlUtil.buildTreeListControl(sysModuleList, "0");

		return WebApiReturnResultModel.ofSuccess(treeListControlVO);
	}

	@Transactional
	@ApiOperation("修改组织菜单列表")
	@PostMapping("/updateSysAutOrganize")
	public WebApiReturnResultModel updateSysAutOrganize(
			@Validated @RequestBody() SysOrganizeAuthUpdateRequestDTO sysOrganizeAuthorizeUpdate) {
		String userId = sysOrganizeAuthorizeUpdate.getUserId();
		String organizeId = sysOrganizeAuthorizeUpdate.getId();
		
		List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService.selectByOrganizeId(organizeId);
		List<String> oldModuleList = sysOrganizeAuthorizeList.stream().map(q -> q.getModuleId()).collect(Collectors.toList());
		List<String> newModuleList = sysOrganizeAuthorizeUpdate.getSysModuleIdList();
		List<String> addModuleList = newModuleList.stream().filter(q -> !oldModuleList.contains(q)).collect(Collectors.toList());
		List<String> deleteModuleList = oldModuleList.stream().filter(q -> !newModuleList.contains(q)).collect(Collectors.toList());
		
		SysOrganizeDO sysOrganize = dozerBeanMapper.map(sysOrganizeAuthorizeUpdate, SysOrganizeDO.class);
		sysOrganizeService.update(sysOrganize, userId);
		
		int layers = sysOrganizeService.selectById(organizeId).getLayers();
		
		if (addModuleList.size() > 0) {
			List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeAddList = addModuleList.stream().map(q -> {
				SysOrganizeAuthorizeDO sysOrganizeAuthorizeTemp = new SysOrganizeAuthorizeDO();
				sysOrganizeAuthorizeTemp.setModuleId(q);
				sysOrganizeAuthorizeTemp.setOrganizeId(organizeId);
				return sysOrganizeAuthorizeTemp;
			}).collect(Collectors.toList());
			sysOrganizeAuthorizeService.insertList(sysOrganizeAuthorizeAddList, userId);
			
			if(layers == 1) {
				//1级商户管理员菜单权限要和组织菜单权限同步
				SysRoleDO sysRole = sysRoleService.selectSuperManage(organizeId);
				List<SysRoleAuthorizeDO> sysRoleAuthList = sysOrganizeAuthorizeAddList.stream().map(q-> new SysRoleAuthorizeDO(q.getModuleId(),sysRole.getId())).collect(Collectors.toList());
				sysRoleAuthorizeService.insertList(sysRoleAuthList, userId);
				
			}
		}

		if (deleteModuleList.size() > 0) {
			List<String> organizeIdList = sysOrganizeService.selectByParentIdRecursion(organizeId).stream()
					.map(q -> q.getId()).collect(Collectors.toList());
			organizeIdList.add(organizeId);
			sysOrganizeAuthorizeService.deleteByOrgListAndModuleList(organizeIdList, deleteModuleList);
			List<String> sysRoleIdList = sysRoleService.selectByOrganizeId(organizeId).stream().map(q -> q.getId())
					.collect(Collectors.toList());
			if (sysRoleIdList.size() > 0) {
				sysRoleAuthorizeService.deleteByRoleIdListAndModuleIdList(sysRoleIdList, deleteModuleList);
			}
		}
		return WebApiReturnResultModel.ofSuccess();
	}

	@Transactional
	@ApiOperation("删除组织")
	@PostMapping("/deleteSysOrganize")
	public WebApiReturnResultModel deleteSysOrganize(@RequestBody SysOrgaDeleteRequestDTO sysOrganizeDelete) {
		String id = sysOrganizeDelete.getId();
		int size = sysOrganizeService.selectCountByParentId(id);
		if (size > 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.ORGANZIE_HAS_CHILD);
		}
		
		// 删除平台账户
		sysUserService.deleteByOrganizeId(id);
		sysRoleService.deleteByOrganizeIdCascade(id);
		// 删除组织
		String userId = sysOrganizeDelete.getUserId();
		sysOrganizeService.shamDeleteCascade(id, userId);
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation(value = "获取组织树形菜单", response = TreeListControlVO.class)
	@PostMapping("/treeListControl")
	public WebApiReturnResultModel treeListControl(@Validated @RequestBody() CommonIdRequestDTO commonId) {
		String currentOrganizeId = commonId.getId();
		List<SysOrganizeDO> sysOrganizeList = sysOrganizeService.selectByParentIdRecursion(currentOrganizeId);
		SysOrganizeDO sysOrganize = sysOrganizeService.selectById(currentOrganizeId);
		sysOrganizeList.add(sysOrganize);

		List<TreeListControlVO> treeListControlList = ViewControlUtil.buildTreeListControl(sysOrganizeList,
				sysOrganize.getParentId());

		return WebApiReturnResultModel.ofSuccess(treeListControlList);
	}

	@ApiOperation(value = "新增组织全部菜单列表", response = TreeListControlVO.class)
	@PostMapping("/sysOrgAutModuleTreeList")
	public WebApiReturnResultModel sysOrgAutModuleTreeList(@Validated @RequestBody() SysOrgAutModuleTreeListRequestDTO sysOrgAutModuleTreeList) {
		List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService.selectByOrganizeId(sysOrgAutModuleTreeList.getId());
		List<String> moduleIdList = sysOrganizeAuthorizeList.stream().map(q -> q.getModuleId()).collect(Collectors.toList());
		moduleIdList.addAll(sysOrganizeAuthorizeTypeService.list(sysOrgAutModuleTreeList.getOrgType()));
		moduleIdList = moduleIdList.stream().distinct().collect(Collectors.toList());

		List<SysModuleDO> sysModuleList = sysModuleService.selectByidList(moduleIdList);
		List<TreeListControlVO> treeListControlVO = ViewControlUtil.buildTreeListControl(sysModuleList, "0");

		return WebApiReturnResultModel.ofSuccess(treeListControlVO);
	}

	@ApiOperation("添加组织")
	@PostMapping("/submitSysOrganizeOptimize")
	@Transactional
	public WebApiReturnResultModel submitSysOrganizeOptimize(
			@RequestBody @Validated SysOrgOptimizeSubmitRequestDTO sysOrgOptimizeSubmit) throws Exception {
		SysOrgOptimizeBO sysOrganize = dozerBeanMapper.map(sysOrgOptimizeSubmit, SysOrgOptimizeBO.class);

		String userId = sysOrgOptimizeSubmit.getUserId();
//		String roleId;

		Integer layer = sysOrganizeService.selectById(sysOrganize.getParentId()).getLayers() + 1;
		sysOrganize.setLayers(layer);
		String id = sysOrganizeService.insertCascade(sysOrganize, userId);
		if (sysOrganize.getLayers() == 1) {
			OrganizeVariableBO organizeVariable = dozerBeanMapper.map(sysOrgOptimizeSubmit, OrganizeVariableBO.class);
			organizeVariable.setId(id);

			OrganizeObserverable organizeObserverable = new OrganizeObserverable();
			// 新增角色
			organizeObserverable.registerObserver(sysRoleOrganizeObserver);
			organizeObserverable.registerObserver(sysModuleOrganizeObserver);
			// 新增账户
			organizeObserverable.registerObserver(accountRegisterOrganizeObserver);
			// 新增用户
			organizeObserverable.registerObserver(sysUserOrganizeObserver);
			try {
				organizeObserverable.execute(organizeVariable);
			} catch (WuXiHuaJieFeignError e) {
				return e.getWebApiReturnResultModel();
			}
		}

		return WebApiReturnResultModel.ofSuccess(id);
	}

    @ApiOperation(value = "组织下拉框（层级小于=1）", response=DropDownListControlVO.class)
    @PostMapping("/sysOrganizeMainList")
    public WebApiReturnResultModel sysOrganizeMainList(
            @Validated @RequestBody() SysOrganizeMainRequestDTO sysOrganizeMainRequestDTO) {
        List<DropDownListControlVO> sysOrganizeSimpleList = new ArrayList<>();
        Boolean isSystem = sysOrganizeMainRequestDTO.getIsSystem();
        String organizeId = sysOrganizeMainRequestDTO.getOrganizeId();
        SysOrganizeDO sysOrganize = sysOrganizeService.selectById(organizeId);
        sysOrganizeSimpleList.add(new DropDownListControlVO(sysOrganize.getId(), sysOrganize.getFullName(), null));
        if (isSystem) {
            List<SysOrganizeDO> selectByParentId = sysOrganizeService.listByLayers(organizeId, 1);
            sysOrganizeSimpleList.addAll(selectByParentId.stream().map(q -> {
                return new DropDownListControlVO(q.getId(), q.getFullName(), null);
            }).collect(Collectors.toList()));
        }
        return WebApiReturnResultModel.ofSuccess(sysOrganizeSimpleList);
    }

    @ApiOperation("根据组织id列表查询组织")
    @PostMapping("/listOrganizeByIdList")
	@Override
	public WebApiReturnResultModel listOrganizeByIdList(
			@RequestBody CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest) {
		List<SysOrganizeDO> listByOrgIdList = sysOrganizeService
				.listByOrgIdList(commonOrganizeIdListRequest.getOrganizeIdList());
		return WebApiReturnResultModel.ofSuccess(listByOrgIdList);
	}
}