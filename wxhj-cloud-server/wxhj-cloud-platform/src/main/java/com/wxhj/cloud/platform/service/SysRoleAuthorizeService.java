/** 
 * @fileName: SysRoleAuthorizeService.java  
 * @author: pjf
 * @date: 2019年10月16日 下午1:40:22 
 */

package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.SysRoleAuthorizeDO;

/**
 * @className SysRoleAuthorizeService.java
 * @author pjf
 * @date 2019年10月16日 下午1:40:22
 */

public interface SysRoleAuthorizeService {
	List<SysRoleAuthorizeDO> select();

	List<SysRoleAuthorizeDO> selectByRoleId(String roleId);

	void deleteByRoleId(String roleId);

	void deleteByModuleId(String moduleId);

	void deleteByIdList(List<String> isList);
	
	void deleteByOrganizeId(String organizeId);

	void deleteByRoleIdListAndModuleIdList(List<String> roleIdList, List<String> moduleIdList);

	void insertList(List<SysRoleAuthorizeDO> sysRoleAuthorizeList, String userId);

}
