/**
 * 
 */
package com.wxhj.cloud.platform.service;

import java.util.List;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.view.ViewRoleOrganizeDO;

/**
 * @ClassName: ViewRoleOrganizeService.java
 * @author: cya
 * @Date: 2019年11月25日 上午11:13:59 
 */
public interface ViewRoleOrganizeService {
//	IPageResponseModel listByOrgId(String keyWord, String organizeId, IPageRequestModel paginationRequestModel);
	IPageResponseModel listByOrgId(String keyWord, List<String> organizeIdList, IPageRequestModel paginationRequestModel);
	
	List<ViewRoleOrganizeDO> listByOrgList(List<String> orgList);
	List<ViewRoleOrganizeDO> listByOrgId(String orgId);
	//吕佳俊专用
	boolean exitsRoleByOrg(String orgId);
}
