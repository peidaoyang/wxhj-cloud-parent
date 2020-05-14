package com.wxhj.cloud.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.feignClient.bo.IAccountOrganizeModel;
import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Table;
import java.time.LocalDate;


/**
 * @author daxiong
 * @date 2020/4/14 2:49 下午
 * @return
 */
@Data
@Table(name = "attendance_summary")
public class AttendanceSummaryDO implements IAccountOrganizeModel {
	/**
	 * 日期
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ColumnType(jdbcType = JdbcType.DATE)
	private LocalDate datetime;
	/**
	 * 考勤权限组id
	 */
	private String authorityGroupId;
	/**
	 * 用户id
	 */
	private String accountId;
	/**
	 * 班次类型，0：正常；1：休息；2：请假；3：出差；4：其他
	 */
	private Integer attendanceType;
	/**
	 * 用户名
	 */
	private String accountName;
	/**
	 * 权限组名称
	 */
	private String authorityGroupName;
	/**
	 * 根组织id
	 */
	private String organizeId;
	/**
	 * 子组织id
	 */
	private String childOrganizeId;

	/**
	 * 第一时间段
	 */
	private Integer sequence1;
	/**
	 * 第一时间段上班时间
	 */
	private Integer upTime1;
	/**
	 * 第一时间段下班时间
	 */
	private Integer downTime1;
	/**
	 * 第一时间段班次状态，-1：无该班次；0：正常；1：休息；2：请假；3：出差；4：其他
	 */
	private Integer timeState1;
	/**
	 * 请假时长1
	 */
	private Integer leaveTime1;
	/**
	 * 出差时长1
	 */
	private Integer travelTime1;
	/**
	 * 上班时长1
	 */
	private Integer workTime1;

	/**
	 * 第二时间段
	 */
	private Integer sequence2;
	/**
	 * 第二时间段上班时间
	 */
	private Integer upTime2;
	/**
	 * 第二时间段下班时间
	 */
	private Integer downTime2;
	/**
	 * 第二时间段班次状态，-1：无该班次；0：正常；1：休息；2：请假；3：出差；4：其他
	 */
	private Integer timeState2;
	/**
	 * 请假时长2
	 */
	private Integer leaveTime2;
	/**
	 * 出差时长2
	 */
	private Integer travelTime2;
	/**
	 * 上班时长2
	 */
	private Integer workTime2;

	/**
	 * 第三时间段
	 */
	private Integer sequence3;
	/**
	 * 第三时间段上班时间
	 */
	private Integer upTime3;
	/**
	 * 第三时间段下班时间
	 */
	private Integer downTime3;
	/**
	 * 第三时间段班次状态，-1：无该班次；0：正常；1：休息；2：请假；3：出差；4：其他
	 */
	private Integer timeState3;
	/**
	 * 请假时长3
	 */
	private Integer leaveTime3;
	/**
	 * 出差时长3
	 */
	private Integer travelTime3;
	/**
	 * 上班时长3
	 */
	private Integer workTime3;
	/**
	 * 请假总时长
	 */
	private Integer leaveTimeTotal;
	/**
	 * 出差总时长
	 */
	private Integer travelTimeTotal;
	/**
	 * 上班总时长
	 */
	private Integer workTimeTotal;
}
