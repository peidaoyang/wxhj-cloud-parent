/**
 * 
 */
package com.wxhj.cloud.business.domain.view;

import java.util.Date;

import javax.persistence.Table;

import org.apache.ibatis.type.JdbcType;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import tk.mybatis.mapper.annotation.ColumnType;

/**
 * @ClassName: ViewRideTotalDO.java
 * @author: cya
 * @Date: 2020年2月6日 下午2:39:31
 */
@Data
@Table(name = "view_ride_summary")
public class ViewRideSummaryDO {
	// 记录上传时间
	@ColumnType(jdbcType = JdbcType.DATE)
	@JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date rideTime;
	// 乘车人数
	private Integer rideCount;
	// 乘车金额
	private Integer totalAmount;
	// 班次id
	private String flightId;
	// 线路id
	private String routeNumber;
	// 车号
	private String carNumber;
	// 组织id
	private String organizeId;
}
