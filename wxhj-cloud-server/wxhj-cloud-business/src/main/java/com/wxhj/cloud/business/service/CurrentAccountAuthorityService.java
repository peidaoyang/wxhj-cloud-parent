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
}
