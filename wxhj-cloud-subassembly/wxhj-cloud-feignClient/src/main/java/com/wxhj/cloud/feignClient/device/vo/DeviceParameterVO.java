package com.wxhj.cloud.feignClient.device.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备参数返回对象")
public class DeviceParameterVO implements IOrganizeSceneModel{
	@ApiModelProperty(value="设备id")
	private String deviceId;
	@ApiModelProperty(value="参数url")
	private String parameterUrl;
	@ApiModelProperty(value="参数版本")
	private Long parameterVersion;
	@ApiModelProperty(value="设备名称")
	private String deviceName;
	@ApiModelProperty(value="组织id（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	@ApiModelProperty(value="场景id（不能排序）")
	private String sceneId;
	@ApiModelProperty(value="场景名称（不能排序）")
	private String sceneName;
	@ApiModelProperty(value="场景名设备类型（不能排序）")
	private Integer deviceType; 
}
