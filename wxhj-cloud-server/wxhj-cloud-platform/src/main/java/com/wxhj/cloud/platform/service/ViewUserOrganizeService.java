package com.wxhj.cloud.platform.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.platform.domain.view.ViewUserOrganizeDO;

public interface ViewUserOrganizeService {
//	IPageResponseModel select(IPageRequestModel paginationRequestModel, String keyValue,
//			String organizeId);
	
	PageInfo<ViewUserOrganizeDO> select(IPageRequestModel paginationRequestModel, String keyValue,
			String organizeId);
}
