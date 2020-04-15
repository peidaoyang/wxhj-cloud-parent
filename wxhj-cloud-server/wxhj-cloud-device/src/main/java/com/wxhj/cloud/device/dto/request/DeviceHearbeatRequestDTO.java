package com.wxhj.cloud.device.dto.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(description = "设备定时心跳请求对象")
public class DeviceHearbeatRequestDTO {
	@ApiModelProperty(value = "设备编号", example = "82012345")
	@NotNull
	private String deviceId;
	@ApiModelProperty(value = "组织编号", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotNull
	private String organizeId;
	@ApiModelProperty(value = "场景编号", example = "484fb259-21de-4c22-b01a-d9f3f124a975")
	@NotNull
	private String sceneId;
	@ApiModelProperty(dataType = "Long", value = "设备版本", example = "1583130607")
	@Min(946656000L)
	@Max(4102416000L)
	private Long parameterVersion;
	@ApiModelProperty(value = "设备时间", example = "2020-02-15 09:24:01")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	private Date deviceTime;
	@ApiModelProperty(value = "设备名称", example = "样例1")
	@NotNull
	private String deviceName;
	@ApiModelProperty(dataType = "Long",value = "人脸同步流水号", example = "5")
	@Min(-1L)
	private Long faceSerialNumber;
	@ApiModelProperty(value = "设备类型(考勤:0,门禁:1,闸机控制门禁:2,班车:3,食堂:4,语音对讲门禁:5,人证比对:6)", example = "1")
	@Min(0)
	@Max(99)
	private Integer deviceType;
	//

}
