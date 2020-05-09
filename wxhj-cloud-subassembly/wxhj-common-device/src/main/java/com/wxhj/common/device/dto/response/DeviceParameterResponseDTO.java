package com.wxhj.common.device.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@ToString
@Data
@ApiModel("设备参数返回模型")
public class DeviceParameterResponseDTO {
	@ApiModelProperty(value = "设备id")
	private String deviceId;
	@ApiModelProperty(value = "组织id")
	private String organizeId;
	@ApiModelProperty(value = "场景id")
	private String sceneId;
	@ApiModelProperty(value = "参数文件的路径(该字段为文件名)")
	private String parameterUrl;
	@ApiModelProperty(value = "参数版本")
	private Long parameterVersion;
	@ApiModelProperty(value = "设备名称")
	private String deviceName;
	@ApiModelProperty(value = "设备类型")
	private Integer deviceType;
	@ApiModelProperty(value = "参数文件的路径")
	private String parameterUrl1;
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
