/** 
 * @fileName: AuthorityGroupClient.java  
 * @author: pjf
 * @date: 2019年11月19日 上午10:16:15 
 */

package com.wxhj.cloud.feignClient.account;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.fallback.AuthorityGroupClientFallBack;
import com.wxhj.cloud.feignClient.account.request.AutoSynchroAuthRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageByTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListAuthorityGroupPageRequestDTO;
import com.wxhj.cloud.feignClient.account.request.OptionalAuthorityGroupListRequestDTO;
import com.wxhj.cloud.feignClient.account.request.SubmitAuthorityGroupInfoRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeIdListRequestDTO;


/**
 * @className AuthorityGroupClient.java
 * @author pjf
 * @date 2019年11月19日 上午10:16:15
 */
@FeignClient(name = "accountServer", fallback = AuthorityGroupClientFallBack.class)
public interface AuthorityGroupClient {
	@PostMapping("/authorityGroup/optionalAuthorityGroupList")
	WebApiReturnResultModel optionalAuthorityGroupList(@RequestBody OptionalAuthorityGroupListRequestDTO optionalAuthorityGroupListRequest);
	
	@PostMapping("/authorityGroup/listAuthorityGroupPage")
	WebApiReturnResultModel listAuthorityGroupPage(@RequestBody ListAuthorityGroupPageRequestDTO listAuthorityGroupPage);
	
	@PostMapping("/authorityGroup/submitAuthorityGroupInfo")
	WebApiReturnResultModel submitAuthorityGroupInfo(
			@RequestBody SubmitAuthorityGroupInfoRequestDTO submitAuthorityGroupInfoRequest);
	
	@PostMapping("/authorityGroup/deleteAuthorityGroupInfo")
	WebApiReturnResultModel deleteAuthorityGroupInfo(@RequestBody CommonIdRequestDTO commonIdRequest);
	
	
//	
//	@PostMapping("/authorityGroup/selectedSceneList")
//	WebApiReturnResultModel selectedSceneList(@RequestBody CommonIdRequestDTO commonIdRequest);


	@PostMapping("/authorityGroup/listAuthorityGroupPageByType")
	WebApiReturnResultModel listAuthorityGroupPageByType(
			@RequestBody ListAuthorityGroupPageByTypeRequestDTO listAuthorityGroupPageRequest);

	@PostMapping("/authorityGroup/optionalAuthByOrganList")
	WebApiReturnResultModel optionalAuthByOrganList(
			@RequestBody CommonOrganizeIdListRequestDTO commonOrganizeIdListRequest);
	
	@PostMapping("/authorityGroup/autoSynchroAuth")
	WebApiReturnResultModel autoSynchroAuth(@RequestBody @Validated AutoSynchroAuthRequestDTO autoSynchroAuth);
	
//	@PostMapping("/authorityGroup/optionalAuthList")
//	WebApiReturnResultModel optionalAuthList();
	@PostMapping("/authorityGroup/authorityBySceneId")
	WebApiReturnResultModel authorityBySceneId(@RequestBody @Validated CommonIdListRequestDTO commonIdList);
}
