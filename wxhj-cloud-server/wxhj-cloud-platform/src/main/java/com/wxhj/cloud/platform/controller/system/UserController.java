package com.wxhj.cloud.platform.controller.system;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.core.utils.PasswordUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.bo.AccountInfoBO;
import com.wxhj.cloud.feignClient.account.request.ListAccountPageByRootOrg;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.platform.bo.RoleInfoBO;
import com.wxhj.cloud.platform.bo.SysUserSubmitListBO;
import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.domain.SysRoleDO;
import com.wxhj.cloud.platform.domain.SysUserDO;
import com.wxhj.cloud.platform.domain.view.ViewUserOrgRoleDO;
import com.wxhj.cloud.platform.domain.view.ViewUserOrganizeDO;
import com.wxhj.cloud.platform.dto.request.AccountImportRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysUserOrganizeListRequestDTO;
//import com.wxhj.cloud.platform.dto.request.SysUserPageRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysUserRetPassowrdRequestDTO;
import com.wxhj.cloud.platform.dto.request.SysUserSubmitRequestDTO;
import com.wxhj.cloud.platform.dto.request.UserOrgRoleListRequestDTO;
import com.wxhj.cloud.platform.service.MapOrganizeUserService;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.service.SysRoleService;
import com.wxhj.cloud.platform.service.SysUserService;
import com.wxhj.cloud.platform.service.ViewOrganizeUserService;
import com.wxhj.cloud.platform.service.ViewRoleOrganizeService;
import com.wxhj.cloud.platform.service.ViewUserOrgRoleService;
import com.wxhj.cloud.platform.service.ViewUserOrganizeService;
import com.wxhj.cloud.platform.vo.SysUserListVO;
import com.wxhj.cloud.platform.vo.SysUserOrgRoleVO;
import com.wxhj.cloud.platform.vo.UserByIdListVO;
import com.wxhj.cloud.platform.vo.UserOrgRoleVO;
import com.wxhj.cloud.platform.vo.UserOrganizeListVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/systemManage/user")
@Api(tags = "账号管理")
public class UserController {
	@Resource
	SysUserService sysUserService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	MapOrganizeUserService mapOrganizeUserService;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	SysRoleService sysRoleService;
	@Resource
	ViewOrganizeUserService viewOrganizeUserService;
	@Resource
	ViewUserOrganizeService viewUserOrganizeService;
	@Resource
	ViewUserOrgRoleService viewUserOrgRoleService;
	@Resource
	ViewRoleOrganizeService viewRoleOrganizeService;

	@Resource
	AccountClient accountClient;

	@ApiOperation(value="账号分页查询",response = SysUserListVO.class)
	@PostMapping("/sysUserList")
	public WebApiReturnResultModel sysUserList(@Validated @RequestBody() CommonListPageRequestDTO sysUserPage) {
		PageInfo<ViewUserOrganizeDO> listPage = viewUserOrganizeService.select(sysUserPage, sysUserPage.getNameValue(), sysUserPage.getOrganizeId());
		List<SysUserListVO> userList = listPage.getList().stream().map(q -> dozerBeanMapper.map(q, SysUserListVO.class))
				.collect(Collectors.toList());

		List<ViewUserOrgRoleDO> roleList = new ArrayList<ViewUserOrgRoleDO>();
		if(userList !=null && userList.size()>0){
			roleList = viewUserOrgRoleService.list(userList.stream().map(q -> q.getId()).collect(Collectors.toList()));
		}

		for (SysUserListVO q : userList) {
			List<String> roleStrList = roleList.stream().filter(p -> p.getUserId().equals(q.getId())).map(p -> p.getRoleName()).collect(Collectors.toList());
			q.setRoleListStr(roleStrList);
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage, userList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
	
	@ApiOperation(value="账号分配选择人员",response = AccountInfoVO.class)
	@PostMapping("/listAccountPage")
	@LcnTransaction
	public WebApiReturnResultModel listAccountPage(
			@Validated @RequestBody() CommonListPageRequestDTO commonListPageRequest) {
		return accountClient.listAccountPage(commonListPageRequest);
	}
	
	// 吕佳俊专用
	@ApiOperation(value="账号根据人员选择组织",response =UserOrganizeListVO.class)
	@PostMapping("/userOrganizeList")
	public WebApiReturnResultModel userOrganizeList(
			@Validated @RequestBody() SysUserOrganizeListRequestDTO sysUserOrganize) {
		List<SysOrganizeDO> sysOrgList = sysOrganizeService.selectByParentIdRecursion(sysUserOrganize.getId());
		sysOrgList.add(sysOrganizeService.selectById(sysUserOrganize.getId()));
		sysOrgList = sysOrgList.stream()
				.filter(q -> q.getLayers() != 0 && q.getFullName().indexOf(sysUserOrganize.getFullName()) > -1)
				.collect(Collectors.toList());

		List<UserOrganizeListVO> sysUserOrgList = sysOrgList.stream()
				.map(q -> new UserOrganizeListVO(q.getId(), q.getFullName())).collect(Collectors.toList());
		for (UserOrganizeListVO userOrganizeListVO : sysUserOrgList) {
			userOrganizeListVO.setDisabled(viewRoleOrganizeService.exitsRoleByOrg(userOrganizeListVO.getId()));
		}
		return WebApiReturnResultModel.ofSuccess(sysUserOrgList);
	}
	
	@ApiOperation(value="账号根据组织选择角色",response=UserOrgRoleVO.class)
	@PostMapping("/userOrgRoleList")
	public WebApiReturnResultModel userOrgRoleList(@Validated @RequestBody UserOrgRoleListRequestDTO userOrgRoleList) {
		List<SysOrganizeDO> orgList = new ArrayList<SysOrganizeDO>();
		for (String values : userOrgRoleList.getOrgMap().values()) {
			SysOrganizeDO sysOrganizeDO = sysOrganizeService.selectById(values);
			if (sysOrganizeDO == null) {
				SysOrganizeDO nullOrg = new SysOrganizeDO();
				nullOrg.setId(values);
				orgList.add(nullOrg);
			} else {
				orgList.add(sysOrganizeDO);
			}
		}
		List<UserOrgRoleVO> voList = orgList.stream().map(q -> new UserOrgRoleVO(q.getId(), q.getFullName()))
				.collect(Collectors.toList());
		for (UserOrgRoleVO userOrgRole : voList) {
			List<SysRoleDO> roleList = sysRoleService.selectByOrganizeId(userOrgRole.getOrganizeId());
			if (roleList.size() > 0) {
				List<RoleInfoBO> roleBOList = roleList.stream().map(q -> new RoleInfoBO(q.getId(), q.getFullName()))
						.collect(Collectors.toList());
				userOrgRole.setRoleList(roleBOList);
			}
		}
		return WebApiReturnResultModel.ofSuccess(voList);
	}
	
	@ApiOperation("账号分配")
	@PostMapping("/accountImport")
	@LcnTransaction
	public WebApiReturnResultModel accountImport(@Validated @RequestBody AccountImportRequestDTO accountImportRequest) throws WuXiHuaJieFeignError {
		if (sysUserService.existByAccountId(accountImportRequest.getId())) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.DATA_EXIST);
		}
		WebApiReturnResultModel webApiReturnResultModel = accountClient
				.accountOne(new CommonIdRequestDTO(accountImportRequest.getId()));
		if (!webApiReturnResultModel.resultSuccess()) {
			return webApiReturnResultModel;
		}
		AccountInfoBO accountInfo = FeignUtil.formatClass(webApiReturnResultModel, AccountInfoBO.class);
		SysUserDO sysUser = dozerBeanMapper.map(accountInfo, SysUserDO.class);
		sysUser.setAccount(accountInfo.getAccountId());
		sysUser.setRealName(accountInfo.getName());
		String mobile = accountInfo.getPhoneNumber();
		sysUser.setMobilePhone(mobile);
		String passwordStr = mobile.substring(mobile.length() - 4);
		sysUser.setUserPassword(passwordStr);
		sysUser.initialization();
		List<MapOrganizeUserDO> mapList = accountImportRequest.getSysUserSubmitRequestList().stream()
				.map(q -> new MapOrganizeUserDO(q.getOrganizeId(),accountImportRequest.getUserId(),q.getRoleId())).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(sysUserService.insertCascade(sysUser, accountImportRequest.getUserId(), mapList));
	}
	
	@ApiOperation(value="获取用户已选中的角色和组织",response = SysUserOrgRoleVO.class)
	@PostMapping("/userOrgRoleChooseList")
	public WebApiReturnResultModel userOrgRoleChooseList(
			@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		SysUserDO sysUserDO = sysUserService.select(commonIdRequest.getId());
		List<String> orgList = sysOrganizeService.selectByParentIdRecursion(sysUserDO.getOrganizeId()).stream()
				.map(q -> q.getId()).collect(Collectors.toList());
		orgList.add(sysUserDO.getOrganizeId());

		List<ViewUserOrgRoleDO> orgRoleList = viewUserOrgRoleService.select(orgList, sysUserDO.getId());
		List<SysUserOrgRoleVO> userOrgRoleBOList = orgRoleList.stream()
				.map(q -> dozerBeanMapper.map(q, SysUserOrgRoleVO.class)).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(userOrgRoleBOList);
	}
	
	@ApiOperation("添加或修改用户")
	@PostMapping("/submitSysUser")
	public WebApiReturnResultModel submitSysUser(@Validated @RequestBody() SysUserSubmitRequestDTO sysUserSubmit) {
		List<SysUserSubmitListBO> sysUserSubmitRequestList = sysUserSubmit.getSysUserSubmitRequestList();
		if (sysUserSubmitRequestList == null) {
			sysUserSubmitRequestList = new ArrayList<>();
		}
		List<MapOrganizeUserDO> mapOrganizeUserList = sysUserSubmitRequestList.stream().map(q -> {
			return new MapOrganizeUserDO(null, q.getOrganizeId(), sysUserSubmit.getId(), q.getRoleId());
		}).collect(Collectors.toList());
		SysUserDO sysUserDO = dozerBeanMapper.map(sysUserSubmit, SysUserDO.class);
		String userId = sysUserSubmit.getUserId();
		if (Strings.isNullOrEmpty(sysUserSubmit.getId())) {
			sysUserDO.initialization();
			sysUserService.insertCascade(sysUserDO, userId, mapOrganizeUserList);
		} else {
			sysUserService.updateCascade(sysUserDO, userId, mapOrganizeUserList);
		}
		return WebApiReturnResultModel.ofSuccess();
	}
	
	@ApiOperation("重置密码")
	@PostMapping("/reSetPassword")
	public WebApiReturnResultModel reSetPassword(
			@Validated @RequestBody() SysUserRetPassowrdRequestDTO sysUserRetPassowrd) {
		String password = sysUserRetPassowrd.getPassword();
		if (Strings.isNullOrEmpty(password)) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.PASSWORD_ERROR);
		}
		String key = PasswordUtil.generatePasswordKey();
		password = PasswordUtil.calculationPassword(sysUserRetPassowrd.getPassword(), key);

		String userId = sysUserRetPassowrd.getUserId();
		SysUserDO sysUserDO = new SysUserDO();
		sysUserDO.setId(sysUserRetPassowrd.getId());
		sysUserDO.modify(userId);
		sysUserDO.setUserPassword(password);
		sysUserDO.setUserSecretKey(key);

		sysUserService.reSetPassword(sysUserDO);
		return WebApiReturnResultModel.ofSuccess();
	}
	
	@ApiOperation("删除用户")
	@PostMapping("/deleteSysUser")
	public WebApiReturnResultModel deleteSysUser(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
		sysUserService.delete(commonIdRequest.getId());
		mapOrganizeUserService.deleteByUserId(commonIdRequest.getId());
		return WebApiReturnResultModel.ofSuccess();
	}
	
	
	@ApiOperation("账号角色权限修改")
	@PostMapping("/userByIdList")
	public WebApiReturnResultModel userByIdList(@RequestBody @Validated  CommonIdListRequestDTO commonIdListRequest) {
		List<UserByIdListVO> userByIdVO = sysUserService.select(commonIdListRequest.getIdList()).stream()
				.map(q-> new UserByIdListVO(q.getId(), q.getRealName())).collect(Collectors.toList());
		return WebApiReturnResultModel.ofSuccess(userByIdVO);
	}
	
	@ApiOperation(value="分页根组织账户信息（根组织）",response = AccountInfoVO.class)
	@PostMapping("/listAccountPageByRootOrg")
	@LcnTransaction
	public WebApiReturnResultModel listAccountPageByRootOrg(
			@Validated @RequestBody() ListAccountPageByRootOrg listAccountPageByRootOrg) {
		return accountClient.listAccountPageByRootOrg(listAccountPageByRootOrg);
	}
}
