/**
 * @className DeviceVersionStateRequestDTO.java
 * @admin jwl
 * @date 2020年1月8日 上午9:31:19
 */
package com.wxhj.common.device.dto.request;

import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className DeviceVersionStateRequestDTO.java
 * @admin jwl
 * @date 2020年1月8日 上午9:31:19
 */
@Data
public class DeviceVersionStateRequestDTO {
	@ApiModelProperty(value = "设备编号", example = "82012345")
	@NotNull
	private String deviceId;
	@ApiModelProperty(value = "版本id", example = "0")
	@NotNull
	private String id;
}
