/** 
 * @fileName: AccountController.java  
 * @author: pjf
 * @date: 2019年11月19日 下午3:20:14 
 */

package com.wxhj.cloud.platform.controller.school.schoolManage;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.bo.AuthorityGroupInfoBO;
import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.account.response.AccountRegisterResponseDTO;
import com.wxhj.cloud.feignClient.account.vo.AccountDetailVO;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.account.vo.AccountOneVO;
import com.wxhj.cloud.feignClient.account.vo.ImportFileAccountInfoVO;
import com.wxhj.cloud.feignClient.dto.*;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterBatchRequestDTO;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;
import com.wxhj.cloud.feignClient.school.StudentAccountClient;
import com.wxhj.cloud.feignClient.vo.KeyValueVO;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.dto.request.ListAccountPageRequestDTO;
import com.wxhj.cloud.platform.dto.request.OptionalAuthByOrganListPlusRequestDTO;
import com.wxhj.cloud.platform.service.EnumManageService;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.vo.OptionalAuthByOrganVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @className AccountController.java
 * @author pjf
 * @date 2019年11月19日 下午3:20:14
 */

@RestController
@RequestMapping("/school/account")
@Api(tags = "学生管理")
public class StudentAccountController {
	@Resource
	AccountClient accountClient;
	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	FaceAccountClient faceAccountClient;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	EnumManageService enumManageService;
	@Resource
	StudentAccountClient studentAccountClient;


	@ApiOperation(value = "人员分页查询（包含组织及子组织的人员）", response = AccountInfoVO.class)
	@PostMapping("/listAccountPageByOrg")
	@LcnTransaction
	public WebApiReturnResultModel listAccountPageByOrg(
			@Validated @RequestBody()ListAccountPageRequestDTO listAccountPageRequest) {
		ListAccountPageByOrgRequestDTO listAccountPageByOrgRequest = dozerBeanMapper.map(listAccountPageRequest,ListAccountPageByOrgRequestDTO.class);
		List<String> orgIdList = sysOrganizeService.selectByParentIdRecursion(listAccountPageRequest.getOrganizeId()).stream().map(q -> q.getId()).collect(Collectors.toList());
		orgIdList.add(listAccountPageRequest.getOrganizeId());
		listAccountPageByOrgRequest.setOrganizeIdList(orgIdList);

		return accountClient.listAccountPageByOrg(listAccountPageByOrgRequest);
	}

	@ApiOperation(value = "人员添加按钮", response = AccountRegisterResponseDTO.class)
	@PostMapping("/accountRegister")
	@LcnTransaction
	public WebApiReturnResultModel accountRegister(
			@RequestBody @Validated AccountRegisterRequestDTO accountRegisterRequest) {
		int oldLayers  = sysOrganizeService.selectById(accountRegisterRequest.getChildOrganizeId()).getLayers();
		if(oldLayers == 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.ADMIN_ERROR);
		}
		
		return accountClient.accountRegister(accountRegisterRequest);
	}

	@ApiOperation(value = "人员编辑页面->选择权限组", response = OptionalAuthByOrganVO.class)
	@PostMapping("/optionalAuthByOrganList")
	public WebApiReturnResultModel optionalAuthByOrganList(
			@Validated @RequestBody OptionalAuthByOrganListPlusRequestDTO optionalAuthByOrganListPlusRequest) {
		List<SysOrganizeDO> listByIdMinToMax = sysOrganizeService.selectByIdRecursion(
				optionalAuthByOrganListPlusRequest.getChildOrganizeId(),
				optionalAuthByOrganListPlusRequest.getCurrentOrganizeId());

		List<String> organizeIdList = listByIdMinToMax.stream().map(q -> q.getId()).collect(Collectors.toList());
		WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient
				.optionalAuthByOrganList(new CommonOrganizeIdListRequestDTO(organizeIdList));
		List<AuthorityGroupInfoBO> authorityGroupInfoList = null;
		try {
			authorityGroupInfoList = FeignUtil.formatArrayClass(webApiReturnResultModel, AuthorityGroupInfoBO.class);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		List<OptionalAuthByOrganVO> optionalAuthByOrganList = new ArrayList<OptionalAuthByOrganVO>();
		List<EnumManageDO> enumList = enumManageService.selectByEnumCode(7);
		for (SysOrganizeDO sysOrganizeTemp : listByIdMinToMax) {
			OptionalAuthByOrganVO optionalAuthByOrganTemp = dozerBeanMapper.map(sysOrganizeTemp,
					OptionalAuthByOrganVO.class);
			//将考勤类型名称添加到权限组名称后
			List<AuthorityGroupInfoBO> authorityGroupInfoTemp = authorityGroupInfoList.stream()
					.filter(q -> q.getOrganizeId().equals(sysOrganizeTemp.getId()))
					.collect(Collectors.toList());
			authorityGroupInfoTemp.forEach(q->{
				String enumTypeName = enumList.stream().filter(p->p.getEnumType().equals(q.getType())).map(p->p.getEnumTypeName()).collect(Collectors.joining(""));
				q.setFullName(q.getFullName()+"("+enumTypeName+")");
			});
			optionalAuthByOrganTemp.setAuthGroupInfoList(authorityGroupInfoTemp);
			optionalAuthByOrganList.add(optionalAuthByOrganTemp);
		}
		return WebApiReturnResultModel.ofSuccess(optionalAuthByOrganList);
	}

	@ApiOperation("人员编辑")
	@PostMapping("/submitAccountInfo")
	@LcnTransaction
	public WebApiReturnResultModel submitAccountInfo(
			@Validated @RequestBody() SubmitAccountInfoRequestDTO submitAccountInfo) throws WuXiHuaJieFeignError {
		int oldLayers  = sysOrganizeService.selectById(submitAccountInfo.getChildOrganizeId()).getLayers();
		if(oldLayers == 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.BAD_REQUEST);
		}
		return accountClient.submitAccountInfo(submitAccountInfo);
	}


	@ApiOperation("人员人脸注册")
	@PostMapping("/faceRegister")
	@LcnTransaction
	public WebApiReturnResultModel faceRegister(@Validated @RequestBody FaceRegisterRequestDTO faceRegisterRequest) {
		return faceAccountClient.faceRegister(faceRegisterRequest);
	}

	@ApiOperation("人员冻结")
	@PostMapping("/accountListFrozen")
	public WebApiReturnResultModel accountListFrozen(@Validated @RequestBody CommonIdListRequestDTO commonIdList) {
		return accountClient.accountListFrozen(commonIdList);
	}


	@ApiOperation("人员删除接口(删除的前提是已冻结)")
	@PostMapping("/accountListDelete")
	public WebApiReturnResultModel accountListDelete(@Validated @RequestBody CommonIdListRequestDTO commonIdList) {
		accountClient.accountListDelete(commonIdList);
		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation(value = "人员批量添加（通过文件）",response = ImportFileAccountInfoVO.class)
	@PostMapping(value = "/importFileAccountInfo")
	@LcnTransaction
	public WebApiReturnResultModel importFileAccountInfo(
			@Validated @RequestBody ImportFileAccountInfoRequestDTO importFileAccountInfoRequestDTO) {
		return accountClient.importFileAccountInfo(importFileAccountInfoRequestDTO);
	}


	
	@ApiOperation(value="分页根组织账户信息（子组织）",response = AccountInfoVO.class)
	@PostMapping("/listAccountPage")
	@LcnTransaction
	public WebApiReturnResultModel listAccountPage(
			@Validated @RequestBody() CommonListPageRequestDTO commonListPageRequest) {
		return accountClient.listAccountPage(commonListPageRequest);
	}

	
	@ApiOperation("用户充值")
	@PostMapping("/recharge")
	@LcnTransaction
	public WebApiReturnResultModel recharge(@Validated @RequestBody RechargeRequestDTO recharge) {
		return accountClient.recharge(recharge);
	}

	@ApiOperation(value = "根据id获取单条信息", response = AccountOneVO.class)
	@PostMapping("/accountOne")
	@LcnTransaction
	public WebApiReturnResultModel accountOne(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
		return accountClient.accountOne(commonIdRequest);
	}

	@ApiOperation(value = "根据账户id查询账户详细信息", response = AccountDetailVO.class)
	@PostMapping("/accountDetail")
	@LcnTransaction
	public WebApiReturnResultModel accountDetail(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest) {
		WebApiReturnResultModel webApiReturnResultModel = accountClient.accountDetail(commonIdRequest);
		try {
			AccountDetailVO accountDetailVO = FeignUtil.formatClass(webApiReturnResultModel,AccountDetailVO.class);

		} catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
			return  wuXiHuaJieFeignError.getWebApiReturnResultModel();
		}
		return accountClient.accountDetail(commonIdRequest);
	}

	@ApiOperation("账户解冻")
	@PostMapping("/accountListThaw")
	@LcnTransaction
	public WebApiReturnResultModel accountListThaw(@RequestBody @Validated CommonIdListRequestDTO commonIdList) {
		return accountClient.accountListThaw(commonIdList);
	}

	@ApiOperation("人脸批量注册")
	@PostMapping("/faceRegisterBatch")
	public WebApiReturnResultModel faceRegisterBatch(
			@Validated @RequestBody FaceRegisterBatchRequestDTO faceRegisterBatchRequest) {
		return faceAccountClient.faceRegisterBatch(faceRegisterBatchRequest);
	}


	@ApiOperation("根据账户类型和账户id查询账户信息")
	@PostMapping("/accountByOtherCodeAndType")
	public WebApiReturnResultModel accountByOtherCodeAndType(@RequestBody @Validated AccountByOtherCodeAndTypeRequestDTO accountByOtherCodeAndType){
		return studentAccountClient.accountByIdAndType(accountByOtherCodeAndType);
	}
}