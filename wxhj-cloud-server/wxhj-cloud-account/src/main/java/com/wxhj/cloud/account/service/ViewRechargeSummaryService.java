package com.wxhj.cloud.account.service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.view.ViewRechargeSummaryDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @ClassName: ViewRechargeSummaryService.java
 * @author: cya
 * @Date: 2020年2月4日 下午1:30:27 
 */
public interface ViewRechargeSummaryService {
	PageInfo<ViewRechargeSummaryDO> listByDatePage(IPageRequestModel pageRequestModel,String beginTime,String endTime);
}
