package com.wxhj.cloud.device.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
@ApiModel(description = "设备记录上传")
public class DeviceRecordRequestDTO {
	@ApiModelProperty(dataType = "Long", value = "记录时间戳(unix时间)",example="1583198575")
	@Min(946656000L)
	@Max(4102416000L)
	private Long recordTimeStamp;
	@ApiModelProperty(value = "记录内容(json)" )
	@NotNull
	private String data;
	@ApiModelProperty(value = "设备id",example="82012345")
	@NotNull
	private String deviceId;
	@ApiModelProperty(value = "记录类型(考勤:1,门禁:2,小额消费:3,访客:5,班车:4)",example="3")
	@Min(0)
	@Max(99)
	private Integer recordType;
	@ApiModelProperty(value = "组织id",example="29eec925-a8cd-4e58-af61-5b2347186df8")
	@NotNull
	private String organizeId;
	@ApiModelProperty(value = "是否补采记录默认为0",example="0")
	@Min(0)
	@Max(1)
	private Integer isSupplement;
	@ApiModelProperty(dataType = "Long", value = "设备流水号(每台设备不可重复)",example="10")
	@Min(0L)
	private Long serialNumber;
}
