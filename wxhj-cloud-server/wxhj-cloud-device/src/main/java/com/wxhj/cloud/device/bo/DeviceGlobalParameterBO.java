/** 
 * @fileName: DeviceGlobalParameterBO.java  
 * @author: pjf
 * @date: 2020年3月2日 下午3:19:37 
 */

package com.wxhj.cloud.device.bo;

import java.util.Date;

import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className DeviceGlobalParameterBO.java
 * @author pjf
 * @date 2020年3月2日 下午3:19:37   
*/
/** 
 * @className DeviceGlobalParameterBO.java
 * @author pjf
 * @date 2020年3月2日 下午3:19:37 
*/
@Data
@ApiModel("设备全局参数返回对象")
public class DeviceGlobalParameterBO {
	@ApiModelProperty(value="参数id")
	private String id;
	// 参数文件地址
	@ApiModelProperty(value="参数文件地址(该字段当前为文件名称)")
	private String parameterFileUrl;
	// unix时间戳
	@ApiModelProperty(value="参数文件生成的时间戳")
	private Long timeStamp;
	// 参数类型
	@ApiModelProperty(value="参数文件类型(门禁规则:0)")
	private Integer parameterType;
	// 参数名称
	@ApiModelProperty(value="参数名称")
	private String fullName;
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="参数文件启用时间戳")
	private Long startDatetimeStamp;
	
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="参数文件失效时间戳")
	private Long endDatetimeStamp;

	@ApiModelProperty(value="参数文件地址(该字段为文件外网访问的url)")
	private String parameterFileUrl1;
}
