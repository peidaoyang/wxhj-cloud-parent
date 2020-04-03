/** 
 * @fileName: RideInfoBO.java  
 * @author: pjf
 * @date: 2020年3月5日 下午5:14:52 
 */

package com.wxhj.cloud.business.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @className RideInfoBO.java
 * @author pjf
 * @date 2020年3月5日 下午5:14:52
 */

@Data
public class RideInfoBO {
	// 订单编号
	private String orderNumber;
	// 实际扣款金额
	private Integer amount;
	// 票价
	private Integer price;
	// 用户id
	private String accountId;
	// 用户姓名
	private String accountName;
	// 线路id
	private String routeNumber;
	// 车号
	private String carNumber;
	// 组织id
	private String organizeId;
	// 设备id
	private String deviceId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	// 乘车时间
	private Date rideTime;
	
}
