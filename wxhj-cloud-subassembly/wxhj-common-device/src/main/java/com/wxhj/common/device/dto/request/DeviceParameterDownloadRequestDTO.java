package com.wxhj.common.device.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "设备参数请求对象")
public class DeviceParameterDownloadRequestDTO {
	@ApiModelProperty(value = "设备编号", example = "82012345")
	@NotNull
	private String deviceId;
	@ApiModelProperty(value = "组织编号", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotNull
	private String organizeId;
	@ApiModelProperty(value = "场景编号", example = "484fb259-21de-4c22-b01a-d9f3f124a975")
	@NotNull
	private String sceneId;
	@ApiModelProperty(value = "参数版本路径", example = "")
	private String parameterUrl;
	@ApiModelProperty(dataType = "Long",value = "参数版本号", example = "1")
	private Long parameterVersion;
	@ApiModelProperty(value = "设备别名", example = "样例1")
	@NotNull
	private String deviceName;
	@ApiModelProperty(value = "设备类型", example = "4")
	@Min(0)
	@Max(99)
	private Integer deviceType;

	//
	@ApiModelProperty(value = "是否考勤(0为不启用)")
	private Integer isAttendance;
	@ApiModelProperty(value = "是否门禁(0为不启用)")
	private Integer isEntrance;
	@ApiModelProperty(value = "是否消费(0为不启用)")
	private Integer isConsume;
	@ApiModelProperty(value = "是否班车(0为不启用)")
	private Integer isFlight;
	@ApiModelProperty(value = "是否访客(0为不启用)")
	private Integer isVisit;
	@ApiModelProperty(value = "进出标志,0:可进可出，1：近，2：出")
	private Integer InOutMark;
}
