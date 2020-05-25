package com.wxhj.common.device.bo;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

@Data
@ApiModel("设备资源参数对象")
public class ViewDeviceResourceBO {
	@ApiModelProperty(value="设备资源id")
	private String id;
	@ApiModelProperty(value="设备id")
	private String deviceId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="下发时间")
	private LocalDate datetime;
	@ApiModelProperty(value="设备资源类型(程序包:1,语音包:2)")
	private Integer resourceType;
	@ApiModelProperty(value="下发状态(暂时不用)")
	private Integer sentState;
	@ApiModelProperty(value="版本号")
	private String versionNumber;
	@ApiModelProperty(value="文件名")
	private String fileName;
	@ApiModelProperty(value="更新说明")
	private String updateDescribe;
	@ApiModelProperty(value="设备名称(暂时不用)")
	private String deviceName;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	@ApiModelProperty(value="设备类型")
	private Integer deviceType;
	@ApiModelProperty(value="资源文件的url")
	private String fileUrl1;
	@ApiModelProperty(value="文件大小")
	private Integer fileSize;
	@ApiModelProperty(value="文件MD5")
	private String md5;
}
