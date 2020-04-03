/** 
 * @fileName: DeviceRecordResponseDTO.java  
 * @author: pjf
 * @date: 2020年2月21日 下午3:00:02 
 */

package com.wxhj.cloud.device.dto.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className DeviceRecordResponseDTO.java
 * @author pjf
 * @date 2020年2月21日 下午3:00:02   
*/
/** 
 * @className DeviceRecordResponseDTO.java
 * @author pjf
 * @date 2020年2月21日 下午3:00:02 
*/
@Data
@ApiModel(description = "设备响应返回")
public class DeviceRecordResponseDTO {
	@ApiModelProperty(dataType="Long",value = "记录时间戳(unix时间)",example = "1583298301")
	private Long recordTimeStamp;
	@ApiModelProperty(value = "设备id",example ="82012345")
	private String deviceId;
	@ApiModelProperty(value = "记录类型(考勤:1,门禁:2,小额消费:3,访客:5,班车:4)",example="3")
	private Integer recordType;
	@ApiModelProperty(value = "组织id",example = "29eec925-a8cd-4e58-af61-5b2347186df8")
	private String organizeId;
	@ApiModelProperty(value = "是否补采记录默认为0",example="0")
	private Integer isSupplement;
	@ApiModelProperty(dataType="Long",value = "设备流水号(单台设备不可重复)",example="4")
	private Long serialNumber;
}
