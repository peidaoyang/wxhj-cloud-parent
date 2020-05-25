/** 
 * @fileName: NowAttendanceAccountDO.java  
 * @author: pjf
 * @date: 2019年12月27日 上午10:20:58 
 */

package com.wxhj.cloud.business.domain;



import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import tk.mybatis.mapper.annotation.ColumnType;

import java.time.LocalDate;

/**
 * @className NowAttendanceAccountDO.java
 * @author pjf
 * @date 2019年12月27日 上午10:20:58   
*/
/**
 * @className NowAttendanceAccountDO.java
 * @author pjf
 * @date 2019年12月27日 上午10:20:58
 */
@Table(name = "now_attendance_account")
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
public class NowAttendanceAccountDO {
	@Id
	private String id;
	private String authorityGroupId;
	private String accountId;
	@Id
	private Integer serialNumber;
	private String attendanceDayId;
	private Integer attendanceType;
	@Id
	@ColumnType(jdbcType = JdbcType.DATE)
	private LocalDate datetime;
	private String name;
	private String organizeId;
	private String attendanceGroupName;
	private String attendanceDayName;
	private Integer sequence;
}