package com.wxhj.cloud.feignClient.business.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("访客明细模型")
public class VisitInfoListVO implements IOrganizeSceneModel {
	@ApiModelProperty("订单编号")
	private String orderNumber;
	@ApiModelProperty("访问者姓名")
	private String name;
	@ApiModelProperty("被访问者id")
	private String accountId;
	@ApiModelProperty("被访问者名称")
	private String accountName;
	@ApiModelProperty("设备编号")
	private String deviceId;
	@ApiModelProperty("设备名称")
	private String deviceName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("访客可访问起始时间")
	private Date beginTime;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("访客可访问结束时间")
	private Date endTime;
	@ApiModelProperty("组织编号")
	private String organizeId;
	@ApiModelProperty("组织名称")
	private String organizeName;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty("访客创建时间")
	private Date creatorTime;
	
	@ApiModelProperty("场景id")
	private String sceneId;
	@ApiModelProperty("场景名称")
	private String sceneName;

}
