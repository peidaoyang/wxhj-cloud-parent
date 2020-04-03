package com.wxhj.cloud.feignClient.device.vo;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class DeviceAuthorizeVO {
	@ApiModelProperty(value="授权id")
	private String id;
	@ApiModelProperty(value="设备id")
	private String deviceId;
	@ApiModelProperty(value="授权类型")
	private Integer authorizeType;
	@ApiModelProperty(value="授权编码")
	private String authorizeCode;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="授权日期")
	private Date creatorTime;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="有效日期")
	private Date validTime;
}
