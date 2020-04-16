/** 
 * @fileName: CurrentAccountAuthorityService.java  
 * @author: pjf
 * @date: 2019年12月19日 下午4:39:48 
 */

package com.wxhj.cloud.business.service;
/**
 * @className CurrentAccountAuthorityService.java
 * @author pjf
 * @date 2019年12月19日 下午4:39:48   
*/
/** 
 * @className CurrentAccountAuthorityService.java
 * @author pjf
 * @date 2019年12月19日 下午4:39:48 
*/

import java.util.List;

import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;

public interface CurrentAccountAuthorityService {

	List<CurrentAccountAuthorityDO> selectByAuthorityList(List<String> authorityList);

	CurrentAccountAuthorityDO selectByAccountId(String accountId);

	String insert(CurrentAccountAuthorityDO currentAccountAuthorityDO);
	
	void insertList(List<CurrentAccountAuthorityDO> listAccount);
	
	void update(CurrentAccountAuthorityDO currentAccountAuthorityDO);
	
	void delete(String id);

	/**
	 * 获取全部数据
	 * @author daxiong
	 * @date 2020/4/15 11:35 上午
	 * @param
	 * @return java.util.List<com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO>
	 */
	List<CurrentAccountAuthorityDO> listAll();
}
