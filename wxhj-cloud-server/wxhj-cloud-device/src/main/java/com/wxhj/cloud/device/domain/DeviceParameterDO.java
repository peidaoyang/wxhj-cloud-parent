/** 
 * @fileName: DeviceParameterDO.java  
 * @author: pjf
 * @date: 2019年11月28日 下午2:14:54 
 */

package com.wxhj.cloud.device.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.ToString;

/**
 * @className DeviceParameterDO.java
 * @author pjf
 * @date 2019年11月28日 下午2:14:54
 */
@Table(name = "device_parameter")
@ToString
@Data
public class DeviceParameterDO {
	@Id
	private String deviceId;
	private String organizeId;
	private String sceneId;
	private String parameterUrl;
	private Long parameterVersion;
	private String deviceName;
	private Integer deviceType; 
}
