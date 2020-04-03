package com.wxhj.cloud.feignClient.device.vo;

import java.util.Date;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListDeviceGlobalParameterVO implements IOrganizeSceneModel{
	@ApiModelProperty(value="设备全局参数主键")
	private String id;
	@ApiModelProperty(value="设备类型")
	private Integer deviceType;
	@ApiModelProperty(value="设备列表")
	private String deviceIdList;
	
	@ApiModelProperty(value="设备列表list（不能排序）")
	private List<String> deviceIdListPlus;
	@ApiModelProperty(value="参数文件地址")
	private String parameterFileUrl;
	@ApiModelProperty(value="时间戳")
	private Long timeStamp;
	@ApiModelProperty(value="参数类型")
	private Integer parameterType;
	
	@ApiModelProperty(value="场景编号（不能排序）")
	private String sceneId;
	@ApiModelProperty(value="场景名称（不能排序）")
	private String sceneName;
	
	
	@ApiModelProperty(value="全局参数名称")
	private String fullName;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="参数执行开始时间")
	private Date startDatetime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="参数执行结束时间")
	private Date endDatetime;
	
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	
	@ApiModelProperty(value="场景编号list（不能排序）")
	private List<String> sceneIdList;
}
