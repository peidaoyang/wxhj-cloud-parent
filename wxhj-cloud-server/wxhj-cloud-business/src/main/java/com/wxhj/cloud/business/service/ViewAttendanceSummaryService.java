/**
 * @className ViewAttendanceSummaryService.java
 * @admin jwl
 * @date 2020年1月7日 上午8:44:50
 */
package com.wxhj.cloud.business.service;

import java.util.Date;
import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

/**
 * @className ViewAttendanceSummaryService.java
 * @admin jwl
 * @date 2020年1月7日 上午8:44:50
 */
public interface ViewAttendanceSummaryService {
	PageInfo<ViewAttendanceSummaryDO> listPage(IPageRequestModel pageRequestModel, Date beginTime, Date endTime,
			String name, String organizeId);
	List<ViewAttendanceSummaryDO> list( Date beginTime, Date endTime,
			String name, String organizeId);
	PageInfo<ViewAttendanceSummaryDO> listByAccountPage(IPageRequestModel pageRequestModel, Date beginTime,
			Date endTime, String accountId);
}
