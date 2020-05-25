package com.wxhj.cloud.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewAutoSynchroAuthorityDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

public interface ViewAutoSynchroAuthorityService {
	List<ViewAutoSynchroAuthorityDO> list(String organizeId,Integer type,Integer autoSychro);

	List<ViewAutoSynchroAuthorityDO> listByOrgIdAndAutoSychro(String organizeId,Integer autoSychro);

	List<ViewAutoSynchroAuthorityDO> listByIdList(List<String> idList);

	/**
	 * 筛选出不包含本身的权限组数量
	 * @param id
	 * @param organizeId 组织Id
	 * @param type 权限组类型
	 * @param autoSychro 自动同步类型
	 * @return
	 */
	int listNotInId(String id,String organizeId, Integer type, Integer autoSychro);

	PageInfo<ViewAutoSynchroAuthorityDO> listByFullAndOrganizeAndTypePage(String fullName, String organizeId,
				Integer type,IPageRequestModel pageRequestModel);
}
