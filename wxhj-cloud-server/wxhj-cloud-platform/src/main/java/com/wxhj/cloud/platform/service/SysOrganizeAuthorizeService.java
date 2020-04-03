package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.platform.domain.SysOrganizeAuthorizeDO;


public interface SysOrganizeAuthorizeService {
	void insertList(List<SysOrganizeAuthorizeDO> sysOrganizeAuthorizeList,String userId);

	void inserDef(String organizeId, String userId);
	
	List<SysOrganizeAuthorizeDO> selectByOrganizeId(String id);
	void deleteByOrgListAndModuleList(List<String> organizeList,List<String> moduleList);
	
	void deleteByOrganizeId(String organizeId);
	void deleteByModuleId(String moduleId);
	
	
}
