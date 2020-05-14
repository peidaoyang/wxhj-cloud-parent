/**
 * @className ViewAttendanceSummaryDO.java
 * @admin jwl
 * @date 2020年1月7日 上午8:42:03
 */
package com.wxhj.cloud.business.domain.view;



import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

import java.time.LocalDate;

/**
 * @className ViewAttendanceSummaryDO.java
 * @admin jwl
 * @date 2020年1月7日 上午8:42:03
 */
@Data
@Table(name = "view_attendance_summary")
@ExcelDocumentAnnotation
public class ViewAttendanceSummaryDO {
	// 编号
	@ExcelColumnAnnotation(columnName = "attendanceSummary.id")
	private String id;
	// 考勤权限组编号
	@ExcelColumnAnnotation(columnName = "attendanceSummary.authorityGroupId")
	private String authorityGroupId;
	// 用户账号
	@ExcelColumnAnnotation(columnName = "attendanceSummary.accountId")
	private String accountId;
	// 用户名
	@ExcelColumnAnnotation(columnName = "attendanceSummary.name")
	private String name;
	// 组织id
	@ExcelColumnAnnotation(columnName = "attendanceSummary.organizeId")
	private String organizeId;
	@ExcelColumnAnnotation(columnName = "attendanceSummary.attendanceGroupName")
	
	private String attendanceGroupName; //权限组名称
	@ExcelColumnAnnotation(columnName = "attendanceSummary.attendanceDayName")
	private String attendanceDayName; //
	// 班次编号
	@ExcelColumnAnnotation(columnName = "attendanceSummary.attendanceDayId")
	private String attendanceDayId;
	// 班次类型
	@ExcelColumnAnnotation(columnName = "attendanceSummary.attendanceType")
	private String attendanceType;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ExcelColumnAnnotation(columnName = "attendanceSummary.datetime")
	// 时间
	@ColumnType(jdbcType = JdbcType.DATE)
	private LocalDate datetime;
	// 第一时间段
	@ExcelColumnAnnotation(columnName = "attendanceSummary.sequence1")
	private String sequence1;
	// 第一时间段上班时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upTime1")
	private String upTime1;
	// 第一时间段上班匹配时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upMatchingTime1")
	private String upMatchingTime1;
	// 第一时间段上班差
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upMatchingTimeDisparity1")
	private Integer upMatchingTimeDisparity1;
	// 第一时间段上班状态
	// 0为正常,1为迟到或早退,2为缺勤
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upTimeState1")
	private String upTimeState1;
	// 第一时间段下班时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downTime1")
	private String downTime1;
	// 第一时间段下班匹配时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downMatchingTime1")
	private String downMatchingTime1;
	// 第一时间段下班差
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downMatchingTimeDisparity1")
	private Integer downMatchingTimeDisparity1;
	// 第一时间段下班状态
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downTimeState1")
	private String downTimeState1;
	@ExcelColumnAnnotation(columnName = "attendanceSummary.sequence2")
	private String sequence2;// 第二时间段
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upTime2")
	private String upTime2;// 第二时间段上班时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upMatchingTime2")
	private String upMatchingTime2;// 第二时间段上班匹配时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upMatchingTimeDisparity2")
	private Integer upMatchingTimeDisparity2;// 第二时间段上班差
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upTimeState2")
	private String upTimeState2;// 第二时间段上班状态
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downTime2")
	private String downTime2;// 第二时间段下班时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downMatchingTime2")
	private String downMatchingTime2;// 第二时间段下班匹配时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downMatchingTimeDisparity2")
	private Integer downMatchingTimeDisparity2;// 第二时间段下班差
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downTimeState2")
	private String downTimeState2;// 第二时间段下班状态
	@ExcelColumnAnnotation(columnName = "attendanceSummary.sequence3")
	private String sequence3;// 第三时间段
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upTime3")
	private String upTime3;// 第三时间段上班时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upMatchingTime3")
	private String upMatchingTime3;// 第三时间段上班匹配时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upMatchingTimeDisparity3")
	private Integer upMatchingTimeDisparity3;// 第三时间段上班差
	@ExcelColumnAnnotation(columnName = "attendanceSummary.upTimeState3")
	private String upTimeState3;// 第三时间段上班状态
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downTime3")
	private String downTime3;// 第三时间段下班时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downMatchingTime3")
	private String downMatchingTime3;// 第三时间段下班匹配时间
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downMatchingTimeDisparity3")
	private Integer downMatchingTimeDisparity3;// 第三时间段下班差
	@ExcelColumnAnnotation(columnName = "attendanceSummary.downTimeState3")
	private String downTimeState3;// 第三时间段下班状态
}
