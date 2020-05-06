package com.wxhj.cloud.feignClient.device.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceStateVO implements IOrganizeSceneModel {
	@ApiModelProperty(value="设备编号")
	private String deviceId;
	
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	
	@ApiModelProperty(value="场景名称")
	private String sceneName;
	
	@ApiModelProperty(value="场景编号")
	private String sceneId;
	
	@ApiModelProperty(value="参数版本")
	private String parameterVersion;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="设备时间")
	private Date deviceTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="最后连接时间")
	private Date lastTime;
	
	@ApiModelProperty(value="设备名称")
	private String deviceName;

	@ApiModelProperty(value = "最大下发流水号")
	private Long faceChangeMaxIndex;

	@ApiModelProperty(value="人脸同步流水号")
	private Long faceSerialNumber;
}
