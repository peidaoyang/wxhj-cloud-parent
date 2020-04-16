/** 
 * @fileName: ViewAttendanceAsyncSummaryMapper.java  
 * @author: pjf
 * @date: 2020年2月20日 下午2:28:05 
 */

package com.wxhj.cloud.business.mapper.view;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.wxhj.cloud.business.domain.view.ViewAttendanceAsyncSummaryDO;

/**
 * @className ViewAttendanceAsyncSummaryMapper.java
 * @author pjf
 * @date 2020年2月20日 下午2:28:05
 */

//@mapper
public interface ViewAttendanceAsyncSummaryMapper {
	List<ViewAttendanceAsyncSummaryDO> listByDatetimeAndOrganize(String startDate, String endDate, String organizeId);

	List<ViewAttendanceAsyncSummaryDO> listByDatetimeAndAccount(String startDate, String endDate, String accountId);
}
