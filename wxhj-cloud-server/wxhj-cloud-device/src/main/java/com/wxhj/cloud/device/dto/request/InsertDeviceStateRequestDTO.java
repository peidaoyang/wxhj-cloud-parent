package com.wxhj.cloud.device.dto.request;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
@Data
@ToString
public class InsertDeviceStateRequestDTO {
	@ApiModelProperty(value = "设备编号", example = "d3d7ad10-2f26-47cf-8c03-52439f587dc3")
	private String deviceId;
	@ApiModelProperty(value = "组织编号", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	private String organizeId;
	@ApiModelProperty(value = "场景编号", example = "289d238b-a486-4d21-a0cc-af4731499379")
	private String sceneId;
	@ApiModelProperty(value = "版本编号", example = "1")
	private String parameterVersion;
	@ApiModelProperty(value = "人脸同步流水号", example = "1")
	private Long faceSerialNumber;
	@ApiModelProperty(value = "设备时间", example = "2019-12-02 16:48:20")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date deviceTime;
	@ApiModelProperty(value = "最后连接时间", example = "2019-12-02 16:48:20")
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date lastTime;
}
