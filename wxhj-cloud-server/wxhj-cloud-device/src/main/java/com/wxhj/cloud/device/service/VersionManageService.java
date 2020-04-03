/**
 * 
 */
package com.wxhj.cloud.device.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.VersionManageDO;

/**
 * @ClassName: VersionManageService.java
 * @author: cya
 * @Date: 2020年1月2日 下午4:42:24 
 */
public interface VersionManageService {
	PageInfo<VersionManageDO> versionManageListPage(IPageRequestModel pageRequestModel, Integer deviceType);
	PageInfo<VersionManageDO> versionManageListPage(IPageRequestModel pageRequestModel,String organizeId,int deviceType);
	
	VersionManageDO selectById(String versionId);

	
	List<VersionManageDO> listByIdList(List<String> versionId);

	
	String insert(VersionManageDO versionManageDO,String userId);
	void updateByReleaseState(String id,String userId);
	void deleteByIdList(List<String> idList);
}
