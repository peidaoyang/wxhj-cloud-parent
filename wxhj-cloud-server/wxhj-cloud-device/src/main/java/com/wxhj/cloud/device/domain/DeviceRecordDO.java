/** 
 * @fileName: DeviceRecordDO.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:00:35 
 */

package com.wxhj.cloud.device.domain;



import javax.persistence.Table;

import org.springframework.data.annotation.Id;

import com.wxhj.cloud.core.interfaces.IModelInitialization;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className DeviceRecordDO.java
 * @author pjf
 * @date 2020年2月11日 下午3:00:35   
*/
/**
 * @className DeviceRecordDO.java
 * @author pjf
 * @date 2020年2月11日 下午3:00:35
 */
@Data
@Table(name="device_record")
public class DeviceRecordDO implements IModelInitialization {
	@Id
	private Long id;
	// 记录时间
	private LocalDateTime receivedDatetime;
	// 记录时间戳
	private Long recordTimeStamp;

	private String data;

	private String deviceId;

	private Integer recordType;

	private String organizeId;

	private Integer isSupplement;

	private Integer dataState;

	private Long serialNumber;

	@Override
	public void initialization() {
		this.receivedDatetime = LocalDateTime.now();
		dataState = 0;
	}
}
