package com.wxhj.cloud.device.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className DeviceAuthorizeDeleteDTO.java
 * @author jwl
 * @date 2019年11月29日 上午10:49:30
 */
@ToString
@Data
public class DeleteDeviceAuthorizeRequestDTO {
	@ApiModelProperty(value = "授权主键", example = "authorizeId")
	@NotBlank(message = "不能为空")
	String id;
}
