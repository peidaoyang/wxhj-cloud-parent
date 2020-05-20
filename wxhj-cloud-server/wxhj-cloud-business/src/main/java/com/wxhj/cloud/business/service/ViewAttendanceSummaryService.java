/**
 * @className ViewAttendanceSummaryService.java
 * @admin jwl
 * @date 2020年1月7日 上午8:44:50
 */
package com.wxhj.cloud.business.service;


import java.time.LocalDate;
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
	PageInfo<ViewAttendanceSummaryDO> listPage(IPageRequestModel pageRequestModel, LocalDate beginTime, LocalDate endTime,
			String name, String organizeId);
	List<ViewAttendanceSummaryDO> list(LocalDate beginTime, LocalDate endTime,
									   String name, String organizeId);
	PageInfo<ViewAttendanceSummaryDO> listByAccountPage(IPageRequestModel pageRequestModel, LocalDate beginTime,
														LocalDate endTime, String accountId);
}
