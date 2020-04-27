/**
 * 
 */
package com.wxhj.cloud.business.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import com.wxhj.cloud.core.interfaces.IDeviceRecord;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @ClassName: VisitInfoDO.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:25:18 
 */
@Data
@Table(name="visit_info")
public class VisitInfoDO implements IDeviceRecord {
	@Id
	//订单编号
	private String orderNumber;
	//访客姓名
	private String name;
	//被访者id
	private String accountId;
	//设备编号
	private String deviceId;
	//可访问开始时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date beginTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//可访问结束时间
	private Date endTime;
	//组织id
	private String organizeId;
	//访问时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date visitorTime;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordDatetime;
	//设备流水号
	private Long serialNumber;
	// 场景编号
	private String sceneId;
}
