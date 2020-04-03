package com.wxhj.cloud.feignClient.device.request;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "根据类型查询设备查询请求对象")
public class ListDeviceParameterRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value = "类型",example = "deviceId")
	private String type;
}
