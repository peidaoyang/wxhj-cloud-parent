/**
 * @className DeviceResourceDO.java
 * @admin jwl
 * @date 2020年1月7日 上午11:55:39
 */
package com.wxhj.cloud.device.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @className DeviceResourceDO.java
 * @admin jwl
 * @date 2020年1月7日 上午11:55:39
 */
@Data
@Table(name = "device_resource")
public class DeviceResourceDO {
	@Id
	private String id;
	private String deviceId;
	private String versionId;
	private Date datetime;
	private Integer resourceType;
	private Integer sentState;
	private Integer fileSize;
	private String md5;
}
