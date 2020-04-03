package com.wxhj.cloud.device.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className DeviceInfoDO.java
 * @author jwl
 * @date 2019年11月28日 下午4:06:23
 *
 */
@Table(name = "device_info")       
@ToString
@Data
public class DeviceInfoDO {
	@Id
	private String id;
	//设备id
	private String deviceId;
	//imei号
	private String imei;
	//设备型号
	private String deviceModel;
	//设备类型
	private Integer deviceType;
}
