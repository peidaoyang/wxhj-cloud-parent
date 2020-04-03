/** 
 * @fileName: ViewOrganizeUserService.java  
 * @author: pjf
 * @date: 2019年11月12日 下午1:15:18 
 */

package com.wxhj.cloud.platform.service;
/**
 * @className ViewOrganizeUserService.java
 * @author pjf
 * @date 2019年11月12日 下午1:15:18   
*/


import java.util.List;

import com.wxhj.cloud.platform.domain.view.ViewOrganizeUserDO;

public interface ViewOrganizeUserService {

	List<ViewOrganizeUserDO> listByUserId(String userId);
	
	List<ViewOrganizeUserDO> listByUserIdByOrganizeName(String userId,String organizeName);
	
	List<ViewOrganizeUserDO> listByUserIdAndOrgId(String userId,String organizeId);
}
