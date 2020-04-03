//package com.wxhj.cloud.platform.controller.system;
//
//import java.util.List;
//import java.util.stream.Collectors;
//
//import javax.annotation.Resource;
//
//import org.dozer.DozerBeanMapper;
//import org.springframework.transaction.annotation.Transactional;
//import org.springframework.validation.annotation.Validated;
//import org.springframework.web.bind.annotation.PostMapping;
//import org.springframework.web.bind.annotation.RequestBody;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import com.wxhj.cloud.core.model.WebApiReturnResultModel;
//import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;
//import com.wxhj.cloud.platform.domain.SysOrganizeDO;
//import com.wxhj.cloud.platform.dto.request.SysOrganizeAuthUpdateRequestDTO;
//import com.wxhj.cloud.platform.service.SysModuleService;
//import com.wxhj.cloud.platform.service.SysOrganizeAuthorizeService;
//import com.wxhj.cloud.platform.service.SysOrganizeService;
//import com.wxhj.cloud.platform.service.SysRoleAuthorizeService;
//import com.wxhj.cloud.platform.service.SysRoleService;
//
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiOperation;
//
//@RestController
//@RequestMapping("/systemManage/organizeAuthorize")
//@Api(tags="组织权限接口")
//public class OrganizeAuthorizeController {
//	@Resource
//	SysModuleService sysModuleService;
//	@Resource
//	SysRoleAuthorizeService sysRoleAuthorizeService;
//	@Resource
//	SysRoleService sysRoleService;
//	@Resource
//	SysOrganizeService sysOrganizeService;
//	@Resource
//	SysOrganizeAuthorizeService sysOrganizeAuthorizeService;
//	@Resource
//	DozerBeanMapper dozerBeanMapper;
//
//	@Transactional
//	@ApiOperation("修改组织权限菜单")
//	@PostMapping("/updateSysAutOrganize")
//	public WebApiReturnResultModel updateSysAutOrganize(
//			@Validated @RequestBody() SysOrganizeAuthUpdateRequestDTO sysOrganizeAuthorizeUpdate) {
//		String userId = sysOrganizeAuthorizeUpdate.getUserId();
//		String organizeId = sysOrganizeAuthorizeUpdate.getId();
//		List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList = sysOrganizeAuthorizeService
//				.selectByOrganizeId(organizeId);
//		List<String> oldModuleList = sysOrganizeAuthorizeList.stream().map(q -> q.getModuleId())
//				.collect(Collectors.toList());
//		List<String> newModuleList = sysOrganizeAuthorizeUpdate.getSysModuleIdList();
//
//		List<String> addModuleList = newModuleList.stream().filter(q -> !oldModuleList.contains(q))
//				.collect(Collectors.toList());
//		List<String> deleteModuleList = oldModuleList.stream().filter(q -> !newModuleList.contains(q))
//				.collect(Collectors.toList());
//		
//		SysOrganizeDO sysOrganize = dozerBeanMapper.map(sysOrganizeAuthorizeUpdate, SysOrganizeDO.class);
//		sysOrganizeService.update(sysOrganize, userId);
//		
//		if (addModuleList.size() > 0) {
//			List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeAddList = addModuleList.stream().map(q -> {
//				SysOrganizeAuthorizeDO sysOrganizeAuthorizeTemp = new SysOrganizeAuthorizeDO();
//				sysOrganizeAuthorizeTemp.setModuleId(q);
//				sysOrganizeAuthorizeTemp.setOrganizeId(organizeId);
//				return sysOrganizeAuthorizeTemp;
//			}).collect(Collectors.toList());
//			sysOrganizeAuthorizeService.insertList(sysOrganizeAuthorizeAddList, userId);
//
//		}
//
//		if (deleteModuleList.size() > 0) {
//			List<String> organizeIdList = sysOrganizeService.selectByParentIdRecursion(organizeId).stream()
//					.map(q -> q.getId()).collect(Collectors.toList());
//			organizeIdList.add(organizeId);
//			sysOrganizeAuthorizeService.deleteByOrgListAndModuleList(organizeIdList, deleteModuleList);
//			List<String> sysRoleIdList = sysRoleService.selectByOrganizeId(organizeId).stream().map(q -> q.getId()).collect(Collectors.toList());
//			if(sysRoleIdList.size()>0) {
//				sysRoleAuthorizeService.deleteByRoleIdListAndModuleIdList(sysRoleIdList, deleteModuleList);
//			}
//		}
//		return WebApiReturnResultModel.ofSuccess();
//	}
//	
//}
