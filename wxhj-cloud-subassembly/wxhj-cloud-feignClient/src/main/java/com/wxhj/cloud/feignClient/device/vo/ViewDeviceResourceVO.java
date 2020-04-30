package com.wxhj.cloud.feignClient.device.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ViewDeviceResourceVO implements IOrganizeModel {
	@ApiModelProperty(value="设备资源信息主键")
	private String id;
	@ApiModelProperty(value="pos编号")
	private String deviceId;
	
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="下发时间")
	private Date datetime;
	
	@ApiModelProperty(value="资源类型")
	private Integer resourceType;
	@ApiModelProperty(value="下发状态（0未下发 1下发成功）")
	private Integer sentState;
	@ApiModelProperty(value="版本编号")
	private String versionNumber;
	@ApiModelProperty(value="文件名")
	private String fileName;
	@ApiModelProperty(value="更新说明")
	private String updateDescribe;
	@ApiModelProperty(value="设备名称")
	private String deviceName;
	@ApiModelProperty(value="设备类型")
	private Integer deviceType; 
	
	@ApiModelProperty(value="组织编号（无法排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（无法排序）")
	private String organizeName;// 组织名称
}
