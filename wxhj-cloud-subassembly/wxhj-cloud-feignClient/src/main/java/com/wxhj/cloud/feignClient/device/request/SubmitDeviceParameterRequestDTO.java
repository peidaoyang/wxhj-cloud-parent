package com.wxhj.cloud.feignClient.device.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel( description = "编辑设备参数请求对象")
public class SubmitDeviceParameterRequestDTO {
	@ApiModelProperty(value = "设备编号")
	private String deviceId;
	@ApiModelProperty(value = "场景编号")
	private String sceneId;
	@ApiModelProperty(value = "设备别名")
	private String deviceName;
	@ApiModelProperty(value = "组织编号")
	private String organizeId;
	@ApiModelProperty(value = "参数路径")
	private String parameterUrl;
	@ApiModelProperty(value = "参数版本")
	private String parameterVersion;
	@ApiModelProperty(value = "是否考勤")
	private Integer isAttendance;
	@ApiModelProperty(value = "是否门禁")
	private Integer isEntrance;
	@ApiModelProperty(value = "是否消费")
	private Integer isConsume;
	@ApiModelProperty(value = "是否班车")
	private Integer isFlight;
	@ApiModelProperty(value = "是否访客")
	private Integer isVisit;
	@ApiModelProperty(value = "进出标志,0:可进可出，1：近，2：出")
	private Integer InOutMark;

}
