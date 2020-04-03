package com.wxhj.cloud.platform.organize;

import javax.annotation.Resource;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.design.observer.CommonObserver;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.request.AccountRegisterRequestDTO;
import com.wxhj.cloud.feignClient.account.response.AccountRegisterResponseDTO;
import com.wxhj.cloud.platform.dto.request.SysOrgUserSubmitRequestDTO;

@Component
public class AccountRegisterOrganizeObserver extends CommonObserver<OrganizeVariableBO> {
	@Resource
	AccountClient accountClient;

	@Override
	public OrganizeVariableBO execute(OrganizeVariableBO t) throws WuXiHuaJieFeignError {
		SysOrgUserSubmitRequestDTO sysOrgUserSubmit = t.getSysOrgUserSubmitRequest();
		AccountRegisterRequestDTO accountRegisterRequestDTO = new AccountRegisterRequestDTO(
				sysOrgUserSubmit.getMobilePhone(), sysOrgUserSubmit.getRealName(), sysOrgUserSubmit.getIdNumber(),
				sysOrgUserSubmit.getSex(), 0, t.getId(), t.getId(),null);

		WebApiReturnResultModel webApiReturnResultModel = accountClient.accountRegister(accountRegisterRequestDTO);
		FeignUtil.formatClass(webApiReturnResultModel, AccountRegisterResponseDTO.class).getAccountId();
		String accountId = FeignUtil.formatClass(webApiReturnResultModel, AccountRegisterResponseDTO.class)
				.getAccountId();
		t.setAccountId(accountId);
		return t;
	}

}
