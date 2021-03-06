package com.wxhj.common.device.dto.request;



import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@ApiModel(description = "设备定时心跳请求对象")
public class DeviceHeartbeatRequestDTO {
	@ApiModelProperty(value = "设备编号", example = "82012345")
	@NotNull
	private String deviceId;
	@ApiModelProperty(value = "组织编号", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotNull
	private String organizeId;
	@ApiModelProperty(value = "场景编号", example = "484fb259-21de-4c22-b01a-d9f3f124a975")
	@NotNull
	private String sceneId;
	@ApiModelProperty(dataType = "Long", value = "设备版本", example = "1583130607")
	@Min(946656000L)
	@Max(4102416000L)
	private Long parameterVersion;
	@ApiModelProperty(value = "设备时间", example = "2020-02-15 09:24:01")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@NotNull
	private LocalDateTime deviceTime;
	@ApiModelProperty(value = "设备名称", example = "样例1")
	@NotNull
	private String deviceName;
	@ApiModelProperty(dataType = "Long",value = "人脸同步流水号", example = "5")
	@Min(-1L)
	private Long faceSerialNumber;
	@ApiModelProperty(value = "设备类型(0:4.3寸考勤门禁机1:7寸考勤门禁机2:8寸考勤门禁机3:车载机4:食堂消费机5:楼宇对接机6:商业支付机)", example = "1")
	@Min(0)
	@Max(99)
	private Integer deviceType;
	//

	@ApiModelProperty(value = "设备状态,默认为0，其他情况由设备自定义",example = "0")
	private Integer deviceState;

	@ApiModelProperty(value = "设备已下发人数", example = "0")
	private Integer isDownloadPeople;

}
