/** 
 * @fileName: AccountInfoService.java  
 * @author: pjf
 * @date: 2019年10月28日 下午3:11:17 
 */

package com.wxhj.cloud.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className AccountInfoService.java
 * @author pjf
 * @date 2019年10月28日 下午3:11:17
 */

public interface AccountInfoService {

	void update(AccountInfoDO accountInfo);

	boolean isExistByPhoneNumberAndOrg(String phoneNumber, String organizeId);

	String insert(AccountInfoDO accountInfo);

	void insertList(List<AccountInfoDO> accountInfoList);

	/**
	 * 根据(姓名或手机号)和组织编号分页查询人员信息
	 * 
	 * @author pjf
	 * @date 2019年11月6日 下午1:59:51
	 * @param fullName
	 * @param paginationRequestModel
	 * @return
	 */
	PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage(String fullName, String organizeId,
			IPageRequestModel pageRequestModel);
	

//	PageInfo<AccountInfoDO> listByNameOrPhoneNumberPage(String fullName, String organizeId,
//			IPageRequestModel pageRequestModel);
	
	PageInfo<AccountInfoDO> listByNamePage(String fullName, String organizeId,String type, IPageRequestModel pageRequestModel);
	

	PageInfo<AccountInfoDO> listByNameAndChildrenOrg(String fullName, String organizeId,
			IPageRequestModel pageRequestModel);

	PageInfo<AccountInfoDO> listByNameOrPhoneNumberAndChildOrgPage(String fullName, List<String> organizeId,String type,
			IPageRequestModel pageRequestModel);

	// List<AccountInfoDO> listByName(String fullName, List<String> organizeId);

	// List<AccountInfoDO> listByName(String fullName);
	/**
	 * 根据人员id查询人员信息
	 * 
	 * @author pjf
	 * @date 2019年11月6日 下午2:01:19
	 * @param accountId
	 * @return
	 */
	AccountInfoDO selectByAccountId(String accountId);

	/**
	 * 根据人员id查询人员列表
	 * @author daxiong
	 * @date 2020/4/17 11:29 上午
	 * @param accountIdList
	 * @return com.wxhj.cloud.account.domain.AccountInfoDO
	 */
	List<AccountInfoDO> listByAccountId(List<String> accountIdList);

	AccountInfoDO selectByOrganizeIdAndPhone(String organizeId, String phone);

	List<AccountInfoDO> listByPhoneNumber(String phoneNumber);

	List<AccountInfoDO> listByOrganizeId(String organizeId);

	int listByOrganizeIdAndIsFace(String organizeId);

	List<AccountInfoDO> listByAccountIdList(List<String> idList);

	void recharge(String id, Integer amount);

	List<AccountInfoDO> listByChildOrgIdAndIsFace(List<String> organizeList);

	/**
	 * 扣费接口 不包含余额为负数的判断
	 * 
	 * @author cya
	 * @date 2020年2月1日 下午2:01:19
	 * @param accountId,amount
	 * @return
	 */
	void charging(String accountId, Integer amount);

	void deleteCascade(AccountInfoDO accountInfo);

	AccountInfoDO selectByNoAndOrganizeId(String no, int noType, String organizeId);

}
