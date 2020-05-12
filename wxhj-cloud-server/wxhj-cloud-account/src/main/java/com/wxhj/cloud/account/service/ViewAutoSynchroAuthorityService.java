package com.wxhj.cloud.account.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewAutoSynchroAuthorityDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

public interface ViewAutoSynchroAuthorityService {
	List<ViewAutoSynchroAuthorityDO> list(String organizeId,Integer type,Integer autoSychro);

	List<ViewAutoSynchroAuthorityDO> listByIdList(List<String> idList);

	PageInfo<ViewAutoSynchroAuthorityDO> listByFullAndOrganizeAndTypePage(String fullName, String organizeId,
				Integer type,IPageRequestModel pageRequestModel);
}
