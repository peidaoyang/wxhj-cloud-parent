/**
 * 
 */
package com.wxhj.cloud.business.vo;

import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import lombok.Data;

/**
 * @ClassName: ViewAttendanceAsyncSummaryVO.java
 * @author: cya
 * @Date: 2020年2月24日 下午3:47:21 
 */
//@ExcelDocumentAnnotation
//@Data
//public class ViewAttendanceAsyncSummaryVO implements IOrganizeModel{
//	@ExcelColumnAnnotation(columnName="attendanceSummary.dateStr")
//	private String dateStr;
//	@ExcelColumnAnnotation(columnName="attendanceSummary.organizeId")
//	private String organizeId;
//	@ExcelColumnAnnotation(columnName="attendanceSummary.organizeName")
//	private String organizeName;
//	@ExcelColumnAnnotation(columnName="attendanceSummary.accountId")
//	private String accountId;
//	@ExcelColumnAnnotation(columnName="attendanceSummary.name")
//	private String name;
//	// 上班总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.attendanceTotle")
//	private Integer attendanceTotle;
//	// 上班准点总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.upTimeState0")
//	private Integer upTimeState0;
//	// 上班迟到总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.upTimeState1")
//	private Integer upTimeState1;
//	// 上班未打卡总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.upTimeState2")
//	private Integer upTimeState2;
//	// 上班迟到总分钟
//	@ExcelColumnAnnotation(columnName="attendanceSummary.upMatchingTime")
//	private Integer upMatchingTime;
//	// 下班准点总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.downTimeState0")
//	private Integer downTimeState0;
//	// 下班迟到总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.downTimeState1")
//	private Integer downTimeState1;
//	// 下班未打卡总天数
//	@ExcelColumnAnnotation(columnName="attendanceSummary.downTimeState2")
//	private Integer downTimeState2;
//	// 下班迟到总分钟
//	@ExcelColumnAnnotation(columnName="attendanceSummary.downMatchingTime")
//	private Integer downMatchingTime;
//}
