package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.view.ViewUserOrgRoleDO;

/**
 * @ClassName: ViewUserOrgRoleService.java
 * @author: cya
 * @Date: 2020年2月28日 下午5:36:05 
 */
public interface ViewUserOrgRoleService {
	List<ViewUserOrgRoleDO> select(List<String> orgranizeId,String userId);
	
	List<ViewUserOrgRoleDO> list(List<String> userIdList);
}
