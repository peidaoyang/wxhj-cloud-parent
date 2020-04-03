package com.wxhj.cloud.feignClient.device.request;

import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;

/**
 * @ClassName: DownloadDevGlobPraRequestDTO.java
 * @author: cya
 * @Date: 2020年2月4日 下午1:55:46 
 */
public class DlodDevGlobPraRequestDTO {
	@ApiModelProperty(value = "组织编号")
	@Size(min=35)
	private String organizeId;
	@ApiModelProperty(value = "设备类型", example = "1")
	private Integer deviceType;
	@ApiModelProperty(value = "设备列表", example = "设备id，根据设备信息查询接口获取：deviceInfo/allDeviceInfo")
	private List<String> deviceIdListPlus;
	
	@ApiModelProperty(value = "参数文件地址", example = "非定时文件上传接口获取")
	@NotBlank(message="参数文件地址不能为空")
	private String parameterFileUrl;
	@ApiModelProperty(value = "参数类型", example = "枚举接口获取")
	private Integer parameterType;
	@ApiModelProperty(value = "场景编号", example = "场景接口获取:driverManage/scene")
	@NotNull
	private List<String> sceneIdList;
	@ApiModelProperty(value = "参数名称", example = "")
	private String fullName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "参数执行开始时间", example = "yyyy-MM-dd HH:mm:ss")
	private Date startDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value = "参数执行结束时间", example = "yyyy-MM-dd HH:mm:ss")
	private Date endDatetime;
	
}
