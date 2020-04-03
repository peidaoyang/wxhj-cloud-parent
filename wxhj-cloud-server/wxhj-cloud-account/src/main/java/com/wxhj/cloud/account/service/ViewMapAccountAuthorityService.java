/** 
 * @fileName: ViewMapAccountAuthorityService.java  
 * @author: pjf
 * @date: 2020年2月7日 下午4:45:23 
 */

package com.wxhj.cloud.account.service;

import java.util.List;

import com.wxhj.cloud.account.domain.view.ViewMapAccountAuthorityDO;

/**
 * @className ViewMapAccountAuthorityService.java
 * @author pjf
 * @date 2020年2月7日 下午4:45:23   
*/

public interface ViewMapAccountAuthorityService {
	List<ViewMapAccountAuthorityDO> listByAuthorityId(String authorityId);
}
