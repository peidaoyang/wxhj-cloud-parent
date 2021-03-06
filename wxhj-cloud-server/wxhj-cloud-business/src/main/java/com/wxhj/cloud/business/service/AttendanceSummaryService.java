/** 
 * @fileName: AttendanceDayService.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:00:41 
 */

package com.wxhj.cloud.business.service;

import com.wxhj.cloud.business.domain.AttendanceSummaryDO;


import java.time.LocalDate;
import java.util.List;

/**
 * AttendanceSummaryService
 * @author daxiong
 * @date 2020/4/15 10:25 上午
 * @return
 */
public interface AttendanceSummaryService {

	/**
	 * 根据日期主键删除记录
	 * @author daxiong
	 * @date 2020/4/15 10:26 上午
	 * @param date	日期
	 * @return void
	 */
	void delete(LocalDate date);

	/**
	 * 根据账户id获取记录
	 * @author daxiong
	 * @date 2020/4/15 10:28 上午
	 * @param accountId
	 * @return java.util.List<com.wxhj.cloud.business.domain.AttendanceDayDO>
	 */
	List<AttendanceSummaryDO> listByAccountId(String accountId);

	/**
	 * 根据账户id和日期获取记录
	 * @author daxiong
	 * @date 2020/4/15 10:29 上午
	 * @param accountId
	 * @return AttendanceSummaryDO
	 */
	AttendanceSummaryDO selectByAccountAndDate(String accountId, LocalDate date);

	/**
	 * 插入记录
	 * @author daxiong
	 * @date 2020/4/15 10:29 上午
	 * @param attendanceSummaryDO
	 * @return void
	 */
	void insert(AttendanceSummaryDO attendanceSummaryDO);

	/**
	 * 批量插入记录
	 * @author daxiong
	 * @date 2020/4/15 10:29 上午
	 * @param attendanceSummaryList
	 * @return void
	 */
	void insertList(List<AttendanceSummaryDO> attendanceSummaryList);

	/**
	 * 更新记录
	 * @author daxiong
	 * @date 2020/4/15 10:29 上午
	 * @param attendanceSummaryDO
	 * @return void
	 */
	void update(AttendanceSummaryDO attendanceSummaryDO);

}
