/** 
 * @fileName: AuthorityGroupInfoService.java  
 * @author: pjf
 * @date: 2019年11月18日 上午11:39:17 
 */

package com.wxhj.cloud.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AuthorityGroupInfoDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className AuthorityGroupInfoService.java
 * @author pjf
 * @date 2019年11月18日 上午11:39:17
 */

public interface AuthorityGroupInfoService {
//	PageInfo<AuthorityGroupInfoDO> listByOrganizeIdAndFullNamePage(String fullName, String organizeId,
//			IPageRequestModel pageRequestModel);
	
	
	
	
//	PageInfo<AuthorityGroupInfoDO> listByFullAndOrganizeAndTypePage(String fullName, String organizeId, Integer type,
//			IPageRequestModel pageRequestModel);

//	List<AuthorityGroupInfoDO> listByOrganizeId(String organizeId);
	
	List<AuthorityGroupInfoDO> listByOrgAndType(String organize,Integer type);

	List<AuthorityGroupInfoDO> list();

	List<AuthorityGroupInfoDO> listByOrganizeList(List<String> organizeList);

	String insertCascade(AuthorityGroupInfoDO authorityGroupInfo,List<String> sceneIdList,List<String> accountIdList);

	void updateCascade(AuthorityGroupInfoDO authorityGroupInfo,List<String> sceneIdList,List<String> accountIdList);

	void deleteById(String id);

	AuthorityGroupInfoDO select(String id);
}
