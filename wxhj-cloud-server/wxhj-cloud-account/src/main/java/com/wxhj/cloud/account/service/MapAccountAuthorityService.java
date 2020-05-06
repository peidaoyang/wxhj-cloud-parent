/** 
 * @fileName: MapAccountAuthorityService.java  
 * @author: pjf
 * @date: 2019年10月31日 下午4:46:21 
 */

package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.MapAccountAuthorityDO;
import com.wxhj.cloud.core.exception.DateError;

/**
 * @className MapAccountAuthorityService.java
 * @author pjf
 * @date 2019年10月31日 下午4:46:21
 */

public interface MapAccountAuthorityService {

	String insertCascade(MapAccountAuthorityDO mapAccountAuthority) throws DateError;

	int deleteCascade(String authorityGroupId, String accountId);

	List<MapAccountAuthorityDO> list(MapAccountAuthorityDO mapAccountAuthority);

	List<MapAccountAuthorityDO> listByAuthorityId(String authorityId);

	List<MapAccountAuthorityDO> listByAuthorityIdList(List<String> authorityIdList);

	List<MapAccountAuthorityDO> listByAccountId(String accountId);
	

}
