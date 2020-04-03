package com.wxhj.cloud.feignClient.device.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * @className SubmitDeviceInfoRequestDTO.java
 * @author jwl
 * @date 2019年11月29日 上午午8:42:45 
 */
@Data
@ToString
@ApiModel( description = "设备信息请求对象")
public class SubmitDeviceInfoRequestDTO {
	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "设备id")
	private String deviceId;
	@ApiModelProperty(value = "imei号")
	@NotBlank(message="不能为空")
	private String imei;
	@ApiModelProperty(value = "设备型号")
	@NotBlank(message="不能为空")
	private String deviceModel;
	@ApiModelProperty(value = "设备类型")
	private Integer deviceType;
}
