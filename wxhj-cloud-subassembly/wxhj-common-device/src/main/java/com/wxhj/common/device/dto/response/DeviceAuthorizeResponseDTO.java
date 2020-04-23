package com.wxhj.common.device.dto.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("设备授权请求对象")
@Data
public class DeviceAuthorizeResponseDTO {
	@ApiModelProperty(value = "授权id")
	private String id;
	@ApiModelProperty(value = "设备id")
	private String deviceId;
	@ApiModelProperty(value = "授权类型(百度:0)")
	private Integer authorizeType;
	@ApiModelProperty(value = "授权编码")
	private String authorizeCode;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "授权日期")
	private Date creatorTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "有效期限")
	private Date validTime;
}
