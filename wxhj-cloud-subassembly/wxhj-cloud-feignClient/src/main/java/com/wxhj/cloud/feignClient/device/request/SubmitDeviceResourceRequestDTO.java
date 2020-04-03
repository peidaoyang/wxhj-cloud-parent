/**
 * @className SubmitDeviceResourceRequestDTO.java
 * @admin jwl
 * @date 2020年1月7日 下午1:21:33
 */
package com.wxhj.cloud.feignClient.device.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className SubmitDeviceResourceRequestDTO.java
 * @admin jwl
 * @date 2020年1月7日 下午1:21:33
 */
@ApiModel(value = "编辑设备资源请求对象")
@Data
public class SubmitDeviceResourceRequestDTO {
	@ApiModelProperty(value ="设备编号")
	private List<String> posId;
	@ApiModelProperty(value ="版本编号",example="2d4ed85a-cdd4-41b4-8a2f-6314c6681d84")
	private String versionId;
}
