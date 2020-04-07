/** 
 * @fileName: AccountController.java  
 * @author: pjf
 * @date: 2019年11月19日 下午3:20:14 
 */

package com.wxhj.cloud.platform.controller.system;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.AuthorityGroupClient;
import com.wxhj.cloud.feignClient.account.bo.AuthorityGroupInfoBO;
import com.wxhj.cloud.feignClient.account.request.AccountRegisterRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AutoSynchroAuthRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ImportFileAccountInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAccountPageByOrgRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitAccountInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.response.AccountRegisterResponseDTO;
import com.wxhj.cloud.feignClient.account.vo.AccountDetailVO;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.account.vo.AccountOneVO;
import com.wxhj.cloud.feignClient.account.vo.AutoSynchroAuthVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.face.FaceAccountClient;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterBatchRequestDTO;
import com.wxhj.cloud.feignClient.face.request.FaceRegisterRequestDTO;
import com.wxhj.cloud.feignClient.vo.KeyValueVO;
import com.wxhj.cloud.platform.domain.SysOrganizeDO;
import com.wxhj.cloud.platform.dto.request.OptionalAuthByOrganListPlusRequestDTO;
import com.wxhj.cloud.platform.service.SysOrganizeService;
import com.wxhj.cloud.platform.vo.OptionalAuthByOrganVO;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

/**
 * @className AccountController.java
 * @author pjf
 * @date 2019年11月19日 下午3:20:14
 */

@RestController
@RequestMapping("/systemManage/account")
@Api(tags = "人员管理")
public class AccountController {
	@Resource
	AccountClient accountClient;
	@Resource
	AuthorityGroupClient authorityGroupClient;
	@Resource
	FaceAccountClient faceAccountClient;
	@Resource
	SysOrganizeService sysOrganizeService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	

	@ApiOperation(value = "人员分页查询（包含组织及子组织的人员）", response = AccountInfoVO.class)
	@PostMapping("/listAccountPageByOrg")
	@LcnTransaction
	public WebApiReturnResultModel listAccountPageByOrg(
			@Validated @RequestBody() CommonListPageRequestDTO commonListPageRequest) {
		ListAccountPageByOrgRequestDTO listAccountPageByOrgRequest = dozerBeanMapper.map(commonListPageRequest,
				ListAccountPageByOrgRequestDTO.class);
		List<String> orgIdList = sysOrganizeService.selectByParentIdRecursion(commonListPageRequest.getOrganizeId())
				.stream().map(q -> q.getId()).collect(Collectors.toList());
		orgIdList.add(commonListPageRequest.getOrganizeId());
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
			return WebApiReturnResultModel.ofStatus(WebResponseState.BAD_REQUEST);
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
		for (SysOrganizeDO sysOrganizeTemp : listByIdMinToMax) {
			OptionalAuthByOrganVO optionalAuthByOrganTemp = dozerBeanMapper.map(sysOrganizeTemp,
					OptionalAuthByOrganVO.class);
			List<AuthorityGroupInfoBO> authorityGroupInfoTemp = authorityGroupInfoList.stream()
					.filter(q -> q.getOrganizeId().equals(sysOrganizeTemp.getId())).collect(Collectors.toList());
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
		WebApiReturnResultModel webApiReturnResultModel = authorityGroupClient.autoSynchroAuth(new AutoSynchroAuthRequestDTO(submitAccountInfo.getChildOrganizeId()));
		List<String> authIdList = new ArrayList<>();
		if(webApiReturnResultModel.getData() != null){
			authIdList = FeignUtil.formatArrayClass(webApiReturnResultModel, AutoSynchroAuthVO.class).stream().map(q-> q.getId()).collect(Collectors.toList());
		}
		
		authIdList.addAll(submitAccountInfo.getAuthorityGroupIdList());
		authIdList.stream().distinct().collect(Collectors.toList());
		
		submitAccountInfo.setAuthorityGroupIdList(authIdList);
		return accountClient.submitAccountInfo(submitAccountInfo);
	}


	@ApiOperation("人员人脸注册")
	@PostMapping("/faceRegister")
	@LcnTransaction
	public WebApiReturnResultModel faceRegister(@Validated @RequestBody FaceRegisterRequestDTO faceRegisterRequest) {
		return faceAccountClient.faceRegister(faceRegisterRequest);
	}

	@ApiOperation("人员冻结")
	@PostMapping("/accountFrozen")
	public WebApiReturnResultModel accountFrozen(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return accountClient.accountFrozen(commonIdRequest);
	}

	@ApiOperation("人员删除接口(删除的前提是已冻结)")
	@PostMapping("/accountDelete")
	public WebApiReturnResultModel accountDelete(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
		return accountClient.accountDelete(commonIdRequest);
	}

	@ApiOperation("人员批量添加（通过文件）")
	@PostMapping(value = "/importFileAccountInfo")
	@LcnTransaction
	public WebApiReturnResultModel importFileAccountInfo(
			@Validated @RequestBody ImportFileAccountInfoRequestDTO importFileAccountInfoRequestDTO) {
		return accountClient.importFileAccountInfo(importFileAccountInfoRequestDTO);
	}

	@ApiOperation(value = "根据子组织选择人员（通用接口）", response = KeyValueVO.class)
	@PostMapping("/listAccountByChildOrganize")
	public WebApiReturnResultModel listAccountByChildOrganize(
			@Validated @RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
		List<SysOrganizeDO> sysOrganizeList = sysOrganizeService
				.selectByParentIdRecursion(commonOrganizeRequest.getOrganizeId());
		List<String> organizeIdList = sysOrganizeList.stream().filter(q -> q.getLayers() > 0).map(q -> q.getId())
				.collect(Collectors.toList());
		organizeIdList.add(commonOrganizeRequest.getOrganizeId());
		return accountClient.listAccountByChildOrganizeList(new CommonOrganizeIdListRequestDTO(organizeIdList));
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
		return accountClient.accountDetail(commonIdRequest);
	}

	@ApiOperation("账户解冻")
	@PostMapping("/accountThaw")
	@LcnTransaction
	public WebApiReturnResultModel accountThaw(@RequestBody @Validated CommonIdRequestDTO commonId) {
		return accountClient.accountThaw(commonId);
	}

	@ApiOperation("人脸批量注册")
	@PostMapping("/faceRegisterBatch")
	public WebApiReturnResultModel faceRegisterBatch(
			@Validated @RequestBody FaceRegisterBatchRequestDTO faceRegisterBatchRequest) {
		return faceAccountClient.faceRegisterBatch(faceRegisterBatchRequest);
	}
}