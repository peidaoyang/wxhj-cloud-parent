/** 
 * @fileName: DeviceInitializeResponseDTO.java  
 * @author: pjf
 * @date: 2020年3月4日 上午8:58:01 
 */

package com.wxhj.cloud.device.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className DeviceInitializeResponseDTO.java
 * @author pjf
 * @date 2020年3月4日 上午8:58:01
 */

@Data
@ApiModel("设备初始化返回对象")
public class DeviceInitializeResponseDTO {
	@ApiModelProperty("后台唯一编号")
	private String id;
	@ApiModelProperty("设备id")
	private String deviceId;
	@ApiModelProperty("设备imei")
	private String imei;
	@ApiModelProperty("设备编号")
	private String deviceModel;
	@ApiModelProperty("设备类型")
	private Integer deviceType;
}
