/** 
 * @fileName: ViewAttendanceAsyncSummaryDO.java  
 * @author: pjf
 * @date: 2020年2月20日 下午2:21:05 
 */

package com.wxhj.cloud.business.domain.view;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className ViewAttendanceAsyncSummaryDO.java
 * @author pjf
 * @date 2020年2月20日 下午2:21:05   
*/
/**
 * @className ViewAttendanceAsyncSummaryDO.java
 * @author pjf
 * @date 2020年2月20日 下午2:21:05
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ViewAttendanceAsyncSummaryDO {
	//日期范围
	private String dateStr;
	//组织id
	private String organizeId;
	//用户id
	private String accountId;
	//姓名
	private String name;

	// 上班总天数
	private Integer attendanceTotle;
	// 上班准点总天数
	private Integer upTimeState0;
	// 上班迟到总天数
	private Integer upTimeState1;
	// 上班未打卡总天数
	private Integer upTimeState2;
	// 上班迟到总分钟
	private Integer upMatchingTime;
	// 下班准点总天数
	private Integer downTimeState0;
	// 下班迟到总天数
	private Integer downTimeState1;
	// 下班未打卡总天数
	private Integer downTimeState2;
	// 下班迟到总分钟
	private Integer downMatchingTime;
}
