/**
 * @className DeviceGlobalParameterDO.java
 * @admin jwl
 * @date 2019年12月10日 上午11:48:47
 */
package com.wxhj.cloud.device.domain;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.google.common.base.Strings;

import lombok.Data;
import lombok.ToString;

/**
 * @className DeviceGlobalParameterDO.java
 * @admin jwl
 * @date 2019年12月10日 上午11:48:47
 */
@Table(name = "device_global_parameter")
@Data
@ToString
public class DeviceGlobalParameterDO {
	@Id
	private String id;
	// 组织编号
	private String organizeId;
	// 设备类型
	private Integer deviceType;
	// 设备列表
	private String deviceIdList;
	// 参数文件地址
	private String parameterFileUrl;
	// unix时间戳
	private Long timeStamp;
	// 参数类型
	private Integer parameterType;
	// 场景编号
	private String sceneId;
	// 参数名称
	private String fullName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDatetime;

	public void setDeviceIdListPlus(List<String> deviceIdListPlus) {
		if (deviceIdListPlus != null) {
			if (deviceIdListPlus.stream().filter(q -> "*".equals(q)).count() > 0) {
				deviceIdList = "*";
			} else {
				deviceIdList = String.join(",", deviceIdListPlus);
			}
		}
	}

	public List<String> getDeviceIdListPlus() {
		if (!Strings.isNullOrEmpty(deviceIdList)) {
			return Arrays.asList(deviceIdList.split(","));
		}
		return new ArrayList<String>();
	}

	public void setSceneIdList(List<String> sceneIdList) {
		if (sceneIdList != null) {
			if (sceneIdList.stream().filter(q -> "*".equals(q)).count() > 0) {

			} else {
				sceneId = String.join(",", sceneIdList);
			}
		}

	}

	public List<String> getSceneIdList() {
		if (!Strings.isNullOrEmpty(sceneId)) {
			return Arrays.asList(sceneId.split(","));
		}
		return new ArrayList<String>();
	}
}
