package com.wxhj.cloud.platform.controller.backstage;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.wxhj.cloud.platform.dto.request.ListModuleTypeRequestDTO;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeTypeService;
import org.apache.commons.lang.StringUtils;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.common.base.Strings;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.vo.TreeListControlVO;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import com.wxhj.cloud.platform.domain.SysModuleDO;
import com.wxhj.cloud.platform.dto.request.SysModuleSumbitRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysModuleTreeRequestDTO;
import com.wxhj.cloud.platform.service.EnumManageService;
import com.wxhj.cloud.platform.service.SysModuleService;
import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;
import com.wxhj.cloud.platform.util.ViewControlUtil;
import com.wxhj.cloud.platform.vo.ModuleTypeListVO;
import com.wxhj.cloud.platform.vo.SysModuleTreeVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * 
 * @className ModuleController.java
 * @author pjf
 * @date 2019年11月5日 下午4:53:17
 */
@RestController
@RequestMapping("/backstage/module")
@Api(tags = "菜单接口")
public class ModuleController {
	@Resource
	SysModuleService sysModuleService;
	@Resource
	SysOrganizeAuthorizeService sysOrganizeAuthorizeService;
	@Resource
	EnumManageService enumManageService;
	@Resource
	SysOrganizeAuthorizeTypeService sysOrganizeAuthorizeTypeService;

	@Resource
	Mapper dozerBeanMapper;

	@ApiOperation(value="菜单分页查询",response = SysModuleTreeVO.class)
	@PostMapping("/sysModuleTreeList")
	public WebApiReturnResultModel sysModuleTreeList(
			@Validated @RequestBody() SysModuleTreeRequestDTO sysModuleSelect) {
		List<SysModuleDO> sysModuleList = sysModuleService.selectByFullName(sysModuleSelect.getFullName());
		List<SysModuleTreeVO> sysModuleTreeList = ViewControlUtil.buildTreeList(sysModuleList, "0", (q) -> {
			return dozerBeanMapper.map(q, SysModuleTreeVO.class);
		});
		return WebApiReturnResultModel.ofSuccess(sysModuleTreeList);
	}

	@ApiOperation(value="菜单树形控件",response = TreeListControlVO.class)
	@GetMapping("/treeListControl")
	public WebApiReturnResultModel treeListControl() {
		List<SysModuleDO> sysModuleList = sysModuleService.select();
		List<TreeListControlVO> treeListControlList = ViewControlUtil.buildTreeListControl(sysModuleList, "0");
		return WebApiReturnResultModel.ofSuccess(treeListControlList);
	}

	@ApiOperation("添加或修改菜单")
	@PostMapping("/submitSysModule")
	public WebApiReturnResultModel submitSysModule(
			@Validated @RequestBody() SysModuleSumbitRequestDTO sysModuleSumbit) {
		SysModuleDO sysModule = dozerBeanMapper.map(sysModuleSumbit, SysModuleDO.class);
		String userId = sysModuleSumbit.getUserId();
		String id;
		if (Strings.isNullOrEmpty(sysModule.getId())) {
			id = sysModuleService.insertCascade(sysModule, userId);
		} else {
			sysModuleService.update(sysModule, userId);
			id = sysModule.getId();
		}
		return WebApiReturnResultModel.ofSuccess(id);
	}

	@ApiOperation("删除菜单")
	@PostMapping("/deleteSysModule")
	public WebApiReturnResultModel deleteSysModule(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
		sysModuleService.deleteCascade(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}
	
	@ApiOperation(value = "组织快选菜单", response = ModuleTypeListVO.class)
	@PostMapping("/listModuleType")
	public WebApiReturnResultModel listModuleType(@RequestBody @Validated ListModuleTypeRequestDTO listModuleType) {
		List<ModuleTypeListVO> moduleTypeList = new ArrayList<>();
		List<String> moduleList = sysOrganizeAuthorizeService.selectByOrganizeId(listModuleType.getId()).stream().map(q -> q.getModuleId()).collect(Collectors.toList());
		//根据组织类型获取特有菜单页面
		moduleList.addAll(sysOrganizeAuthorizeTypeService.list(listModuleType.getOrgType()));
		moduleList = moduleList.stream().distinct().collect(Collectors.toList());

		List<SysModuleDO> sysModuleList = sysModuleService.listByLayersAndMType(0, moduleList);

		List<Integer> moduleTypeIntegerList = sysModuleList.stream().map(q -> q.getModuleType()).distinct().collect(Collectors.toList());
		List<EnumManageDO> enumList = enumManageService.listByEnumcodeAndEnumType(6, moduleTypeIntegerList);

		for (EnumManageDO enumManage : enumList) {
			List<String> idList = sysModuleList.stream().filter(q -> enumManage.getEnumType().equals(q.getModuleType())).map(q -> q.getId()).collect(Collectors.toList());
			// 此处去除jsonList中的中括号，方便前端做适配
			moduleTypeList.add(new ModuleTypeListVO(enumManage.getEnumType(),
					StringUtils.strip(idList.toString(), "[]").replace(" ", ""), enumManage.getEnumTypeName()));
		}
		return WebApiReturnResultModel.ofSuccess(moduleTypeList);
	}

}
