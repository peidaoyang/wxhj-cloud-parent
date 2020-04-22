/** 
 * @fileName: AccountClient.java  
 * @author: pjf
 * @date: 2019年11月7日 上午9:54:48 
 */

package com.wxhj.cloud.feignClient.account;

import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.AccountClientFallBack;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;

/**
 * @className AccountClient.java
 * @author pjf
 * @date 2019年11月7日 上午9:54:48
 */
@FeignClient(name = "accountServer", fallback = AccountClientFallBack.class)
public interface AccountClient {
//	@PostMapping("/account/updateIsFace")
//	WebApiReturnResultModel updateIsFace(@RequestBody UpdateIsFaceRequestDTO updateIsFaceRequest);

	/**
	 * 获取手机验证码
	 * 
	 * @author pjf
	 * @date 2019年10月29日 下午2:53:45
	 * @param mobilePhoneCodeRequest
	 * @return
	 */
	@PostMapping("/account/mobilePhoneCode")
	WebApiReturnResultModel mobilePhoneCode(@RequestBody MobilePhoneCodeRequestDTO mobilePhoneCodeRequest);

	/**
	 * 用户注册
	 *
	 * @author pjf
	 * @date 2019年10月29日 下午3:19:32
	 * @param accountRegisterRequest
	 * @return
	 */
	@PostMapping("/account/accountRegister")
	WebApiReturnResultModel accountRegister(@RequestBody AccountRegisterRequestDTO accountRegisterRequest);

	/**
	 * 通过文件名批量注册文件内的账户信息
	 * 
	 * @param importFileAccountInfoRequest
	 * @return
	 */
	@PostMapping("/account/importFileAccountInfo")
	WebApiReturnResultModel importFileAccountInfo(
			@RequestBody ImportFileAccountInfoRequestDTO importFileAccountInfoRequest);

	/**
	 * @author pjf
	 * @date 2019年11月6日 下午3:34:30
	 * @param commonListPageRequest
	 * @return
	 */
	@PostMapping("/account/listAccountPage")
	WebApiReturnResultModel listAccountPage(@RequestBody CommonListPageRequestDTO commonListPageRequest);

	@PostMapping("/account/listAccountPageByOrg")
	WebApiReturnResultModel listAccountPageByOrg(@RequestBody ListAccountPageByOrgRequestDTO listAccountPageByOrg);

	/**
	 * @author pjf
	 * @date 2019年11月6日 下午3:46:54
	 * @param commonIdRequest
	 * @return
	 */
	@PostMapping("/account/accountOne")
	WebApiReturnResultModel accountOne(@RequestBody CommonIdRequestDTO commonIdRequest);


	@PostMapping("/account/listAccount")
	WebApiReturnResultModel listAccount(@Validated @RequestBody() CommonIdListRequestDTO commonIdListRequest);

	@PostMapping("/account/submitAccountInfo")
	WebApiReturnResultModel submitAccountInfo(@RequestBody SubmitAccountInfoRequestDTO submitAccountInfoRequest);


	@PostMapping("/account/accountLogin")
	WebApiReturnResultModel accountLogin(@RequestBody AccountLoginRequestDTO accountLoginRequest);

	@PostMapping("/account/accountLoginOrganize")
	WebApiReturnResultModel accountLoginOrganize(@RequestBody AccountLoginOrganizeRequestDTO listAccountLoginRequest);

	@PostMapping("/account/accountResetPassword")
	WebApiReturnResultModel accountResetPassword(@Validated @RequestBody AccountResetPasswordRequestDTO accountreset);

	@PostMapping("/account/recharge")
	WebApiReturnResultModel recharge(@Validated @RequestBody RechargeRequestDTO recharge);

	@PostMapping("/account/appAccountInfo")
	WebApiReturnResultModel appAccountInfo(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest);

	@PostMapping("/account/verifyMobileCode")
	WebApiReturnResultModel verifyMobileCode(@RequestBody VerifyMobileCodeRequestDTO verifyMobileCodeRequest);

	@PostMapping("/account/forgetPassword")
	WebApiReturnResultModel forgetPassword(@RequestBody @Validated ForgetPasswordRequestDTO forgetPasswordRequest);

	@PostMapping("/account/accountLogout")
	WebApiReturnResultModel accountLogout(@RequestBody @Validated AccoutLogoutRequestDTO accoutLogoutRequest);

	@PostMapping("/account/accountBalance")
	WebApiReturnResultModel accountBalance(@RequestBody @Validated CommonIdRequestDTO commonIdRequest);

	@PostMapping("/account/listAccountByChildOrganizeList")
	WebApiReturnResultModel listAccountByChildOrganizeList(
			@Validated @RequestBody CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest);

	@PostMapping("/account/accountFrozen")
	WebApiReturnResultModel accountFrozen(@Validated @RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/account/accountDelete")
	WebApiReturnResultModel accountDelete(@Validated @RequestBody CommonIdRequestDTO commonIdRequest);

	@PostMapping("/account/accountThaw")
	WebApiReturnResultModel accountThaw(@RequestBody CommonIdRequestDTO commonId);

	@PostMapping("/account/accountDetail")
	WebApiReturnResultModel accountDetail(@Validated @RequestBody() CommonIdRequestDTO commonIdRequest);

	@PostMapping("/account/listAccountPageByRootOrg")
	WebApiReturnResultModel listAccountPageByRootOrg(
			@Validated @RequestBody ListAccountPageByRootOrg listAccountPageByRoot);

	@PostMapping("/account/accountTotal")
	WebApiReturnResultModel accountTotal(@RequestBody CommonIdRequestDTO commonIdRequest);

}
