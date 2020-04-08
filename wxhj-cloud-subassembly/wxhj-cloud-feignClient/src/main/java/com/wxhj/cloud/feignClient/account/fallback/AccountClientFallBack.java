/** 
 * @fileName: AccountClientFallBack.java  
 * @author: pjf
 * @date: 2020年1月17日 上午11:41:15 
 */

package com.wxhj.cloud.feignClient.account.fallback;

import org.springframework.stereotype.Component;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.request.AccountAppointNoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AccountLoginOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AccountLoginRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AccountRegisterRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AccountResetPasswordRequestDTO;
import com.wxhj.cloud.feignClient.account.request.AccoutLogoutRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ForgetPasswordRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ImportFileAccountInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAccountPageByOrgRequestDTO;
import com.wxhj.cloud.feignClient.account.request.MobilePhoneCodeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitAccountInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.UpdateIsFaceRequestDTO;
import com.wxhj.cloud.feignClient.account.request.VerifyMobileCodeRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;

/**
 * @className AccountClientFallBack.java
 * @author pjf
 * @date 2020年1月17日 上午11:41:15
 */

@Component
public class AccountClientFallBack implements AccountClient {

//	@Override
//	public WebApiReturnResultModel updateIsFace(UpdateIsFaceRequestDTO updateIsFaceRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel mobilePhoneCode(MobilePhoneCodeRequestDTO mobilePhoneCodeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountRegister(AccountRegisterRequestDTO accountRegisterRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel importFileAccountInfo(
			ImportFileAccountInfoRequestDTO importFileAccountInfoRequestDTO) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	/*
	 * @Override public WebApiReturnResultModel listAccountIdByOrganizeId(
	 * ListAccountOrganizeIdRequestDTO listAccountInfoOrganizeId) { return
	 * WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER); }
	 */

	@Override
	public WebApiReturnResultModel listAccountPage(CommonListPageRequestDTO commonListPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel listAccountId(ListAccountIdRequestDTO listAccountInfo) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel accountOne(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel submitAccountInfo(SubmitAccountInfoRequestDTO submitAccountInfoRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountLogin(AccountLoginRequestDTO accountLoginRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountLoginOrganize(AccountLoginOrganizeRequestDTO listAccountLoginRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountResetPassword(AccountResetPasswordRequestDTO accountreset) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel recharge(RechargeRequestDTO recharge) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel appAccountInfo(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel listAccountByNameAndOrageizeId(
//			ListAccountByNameAndOrageizeIdResquestDTO listAccountByNameAndOrageizeId) {
//		
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel verifyMobileCode(VerifyMobileCodeRequestDTO verifyMobileCodeRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel forgetPassword(ForgetPasswordRequestDTO forgetPasswordRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountLogout(AccoutLogoutRequestDTO accoutLogoutRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel appBalance(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listAccountByChildOrganizeList(
			CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest) {
		// TODO Auto-generated method stub
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountFrozen(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountDelete(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel listAccountPageByOrg(ListAccountPageByOrgRequestDTO listAccountPageByOrg) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountThaw(CommonIdRequestDTO commonId) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

	@Override
	public WebApiReturnResultModel accountDetail(CommonIdRequestDTO commonIdRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

//	@Override
//	public WebApiReturnResultModel accountAppointNo(AccountAppointNoRequestDTO accountAppointNoRequest) {
//		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
//	}

	@Override
	public WebApiReturnResultModel listAccountPageByRootOrg(CommonListPageRequestDTO commonListPageRequest) {
		return WebApiReturnResultModel.ofStatus(WebResponseState.CIRCUIT_BREAKER);
	}

}
