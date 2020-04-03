package com.wxhj.cloud.device.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "设备授权编号请求对象")
public class DeviceAuthorizeDownloadRequestDTO {
	@ApiModelProperty(value = "设备编号", example = "82012345")
	@NotBlank
	private String deviceId;
	@ApiModelProperty(value = "授权类别", example ="0")
	@Min(0)
	@Max(10)
	private Integer authorizeType;
}
