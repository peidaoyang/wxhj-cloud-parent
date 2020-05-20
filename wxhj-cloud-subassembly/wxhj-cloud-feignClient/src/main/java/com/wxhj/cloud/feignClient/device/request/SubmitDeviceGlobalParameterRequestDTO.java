/**
 * @className SubmitDeviceGlobalParameterRequestDTO.java
 * @admin jwl
 * @date 2019年12月10日 下午1:01:16
 */
package com.wxhj.cloud.feignClient.device.request;


import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SubmitDeviceGlobalParameterRequestDTO.java
 * @admin jwl
 * @date 2019年12月10日 下午1:01:16
 */
@Data
@ToString
public class SubmitDeviceGlobalParameterRequestDTO {
	@ApiModelProperty(value = "id")
	private String id;
	@ApiModelProperty(value = "组织编号",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank(message="组织编号不能为空")
	private String organizeId;
	@ApiModelProperty(value = "设备类型", example = "1")
	@Max(10)
	@Min(-1)
	private Integer deviceType;
	@ApiModelProperty(value = "设备列表", example = "设备id，根据设备信息查询接口获取：deviceInfo/allDeviceInfo")
	@NotNull
	private List<String> deviceIdListPlus;
	
	@ApiModelProperty(value = "参数文件地址", example = "非定时文件上传接口获取")
	@NotBlank(message="参数文件地址不能为空")
	private String parameterFileUrl;
	@ApiModelProperty(value = "参数类型", example = "枚举接口获取")
	@NotNull
	private Integer parameterType;
	@ApiModelProperty(value = "场景编号", example = "场景接口获取:driverManage/scene")
	@NotNull
	private List<String> sceneIdList;
	@ApiModelProperty(value = "参数名称", example = "测试xxx")
	@NotBlank
	private String fullName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "参数执行开始时间", example = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime startDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "参数执行结束时间", example = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endDatetime;
}
