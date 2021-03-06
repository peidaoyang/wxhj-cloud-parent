/** 
 * @fileName: DeviceStateDO.java  
 * @author: pjf
 * @date: 2020年2月11日 下午2:07:51 
 */

package com.wxhj.cloud.device.domain;



import javax.persistence.Table;

import org.springframework.data.annotation.Id;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className DeviceStateDO.java
 * @author pjf
 * @date 2020年2月11日 下午2:07:51
 */

@Data
@Table(name = "device_state")
public class DeviceStateDO {
	@Id
	private String deviceId;// 设备编号
	private String organizeId;// 组织编号
	private String sceneId;// 场景编号
	private Long parameterVersion;// 参数版本
	private Long faceSerialNumber;// 人脸同步流水号
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime deviceTime;// 设备时间
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastTime;// 最后连接时间
	private String deviceName;// 设备名称
	private Integer deviceType;

	private Integer deviceState;
	//已下发人数
	private Integer isDownloadPeople;

}
