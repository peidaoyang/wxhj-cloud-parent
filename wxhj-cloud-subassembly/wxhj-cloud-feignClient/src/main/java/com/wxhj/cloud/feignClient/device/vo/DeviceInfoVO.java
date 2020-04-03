package com.wxhj.cloud.feignClient.device.vo;

import com.wxhj.cloud.feignClient.bo.IPlatformEnumModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceInfoVO implements IPlatformEnumModel {
	@ApiModelProperty(value="设备id")
	private String id;
	@ApiModelProperty(value="设备编号")
	private String deviceId;
	@ApiModelProperty(value="imei号")
	private String imei;
	@ApiModelProperty(value="设备型号")
	private String deviceModel;
	
	@ApiModelProperty(value="枚举类型（不能排序）")
	private Integer enumType;
	@ApiModelProperty(value="枚举名称（不能排序）")
	private String enumTypeName;
	
	public void setDeviceType(Integer deviceType) {
		this.enumType = deviceType;
	}
}
