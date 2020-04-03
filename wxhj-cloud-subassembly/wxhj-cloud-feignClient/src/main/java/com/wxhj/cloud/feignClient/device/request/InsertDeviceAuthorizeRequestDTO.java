package com.wxhj.cloud.feignClient.device.request;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
/**
 * @className InsertDeviceAuthorizeRequestDTO.java
 * @author jwl
 * @date 2019年11月29日 上午午9:29:53 
 */
@Data
@ToString
@ApiModel( description = "授权信息请求对象")
public class InsertDeviceAuthorizeRequestDTO {
	@ApiModelProperty(value = "授权类型",example="0")
	@NotNull(message = "不能为空")
	private Integer authorizeType;
	@ApiModelProperty(value = "授权编码",example="123")
	@NotNull(message = "不能为空")
	private String authorizeCode;
	
	@ApiModelProperty(value = "有效期限",example="2019-12-09 17:12:51")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date validTime;
}
