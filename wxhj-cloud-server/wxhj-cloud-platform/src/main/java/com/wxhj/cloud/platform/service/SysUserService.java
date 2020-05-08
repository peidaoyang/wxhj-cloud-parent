package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.MapOrganizeUserDO;
import com.wxhj.cloud.platform.domain.SysUserDO;

public interface SysUserService {
	boolean existByAccountId(String accountId);

//	IPageResponseModel selectPageByOrganizeId(IPageRequestModel paginationRequestModel, String keyValue,
//			String organizeId);

	String insertCascade(SysUserDO sysUserDo, String userid, List<MapOrganizeUserDO> mapOrganizeUserList);

	void updateCascade(SysUserDO sysUserDo, String userid, List<MapOrganizeUserDO> mapOrganizeUserList);

	void delete(String id);
	
	void deleteByOrganizeId(String organizeId);

	void reSetPassword(SysUserDO sysUserDO);
	
	SysUserDO select(String id);
	
	List<SysUserDO> select(List<String> userIdList);
	
	void update(SysUserDO sysUserDO, String userId);
}
