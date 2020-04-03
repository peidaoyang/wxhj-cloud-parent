/**
 * @className ListDeviceResourceRequestDTO.java
 * @admin jwl
 * @date 2020年1月7日 下午1:42:32
 */
package com.wxhj.cloud.feignClient.device.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className ListDeviceResourceRequestDTO.java
 * @admin jwl
 * @date 2020年1月7日 下午1:42:32
 */
@ApiModel(value = "设备资源请求对象")
@Data
public class ListDeviceResourceRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value = "设备类型",example = "0")
	@Min(-1)
	@Max(10)
	private Integer deviceType;
}
