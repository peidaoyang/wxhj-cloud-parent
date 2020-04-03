/** 
 * @fileName: SysRoleService.java  
 * @author: pjf
 * @date: 2019年10月16日 上午11:34:16 
 */

package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.SysRoleDO;

/**
 * @className SysRoleService.java
 * @author pjf
 * @date 2019年10月16日 上午11:34:16
 */

public interface SysRoleService {
	IPageResponseModel select(String keyWord, IPageRequestModel paginationRequestModel, List<String> organizeChildLis);

	List<SysRoleDO> listByOrganizeIdList(List<String> organizeId);

	List<SysRoleDO> selectByOrganizeId(String organizedId);
	
	SysRoleDO selectSuperManage(String organize);

	String insert(SysRoleDO sysRoleDO, String userId);
	
	String insertSuperManage(SysRoleDO sysRoleDO, String userId);

	void update(SysRoleDO sysRoleDO, String userId);

	void shamDeleteByKey(String id, String userId);

	void delete(String id);

	void deleteByOrganizeIdCascade(String organizeId);
}
