/**
 * 
 */
package com.wxhj.cloud.business.domain;



import javax.persistence.Id;
import javax.persistence.Table;

import com.wxhj.cloud.core.interfaces.IDeviceRecord;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: RideInfoDO.java
 * @author: cya
 * @Date: 2020年2月6日 下午12:26:16
 */
@Table(name = "ride_info")
@Data
public class RideInfoDO implements IDeviceRecord {
	@Id
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
	// 班次id
	private String flightId;
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
	private LocalDateTime rideTime;


	private String routeName;
	private String startSite;
	private String endSite;
	private String channelSite;

	//设备流水号
	private Long serialNumber;
	// 场景编号
	private String sceneId;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime recordDatetime;

}
