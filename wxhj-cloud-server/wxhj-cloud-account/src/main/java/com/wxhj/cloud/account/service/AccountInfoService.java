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
	void updateCascade(AccountInfoDO accountInfo);

	/**
	 * 账户余额修改（只操作余额，不做其他数据修改）
	 * @param balance
	 * @param amount
	 * @param accountId
	 */
	void revoke(Integer balance,Integer amount,String accountId);
	boolean isExistByPhoneNumberAndOrg(String phoneNumber, String organizeId);

	String insert(AccountInfoDO accountInfo);

	void insertList(List<AccountInfoDO> accountInfoList);

	/**
	 *
	 * @param accountId
	 * @param frozen 0为非冻结1为冻结
	 */
	void updateFrozen(String accountId,Integer frozen);


	/**
	 *
	 * @param accountId
	 * @param userPassword
	 */
	void updatePassword(String accountId,String userPassword);

	void insertFaceImage(String accountId,String imageName);

	
	PageInfo<AccountInfoDO> listByNamePage(String fullName, String organizeId,String type, IPageRequestModel pageRequestModel);
	

	PageInfo<AccountInfoDO> listByNameAndChildrenOrg(String fullName, String organizeId,
			IPageRequestModel pageRequestModel);

//	PageInfo<AccountInfoDO> listByNameOrPhoneNumberAndChildOrgPage(String fullName, List<String> organizeId,String type,
//			IPageRequestModel pageRequestModel);

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
