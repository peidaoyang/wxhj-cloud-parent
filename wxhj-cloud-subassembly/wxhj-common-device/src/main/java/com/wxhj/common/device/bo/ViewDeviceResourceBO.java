package com.wxhj.common.device.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("设备资源参数对象")
public class ViewDeviceResourceBO {
	@ApiModelProperty(value="设备资源id")
	private String id;
	// pos机编号
	@ApiModelProperty(value="设备id")
	private String posId;
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	@ApiModelProperty(value="下发时间")
	private Date datetime;
	// 资源类型
	@ApiModelProperty(value="设备资源类型(程序包:1,语音包:2)")
	private Integer resourceType;
	// 下发类型
	@ApiModelProperty(value="下发状态(暂时不用)")
	private Integer sentState;
	// 参数版本
	@ApiModelProperty(value="版本号")
	private String versionNumber;
	// 下发文件名
	@ApiModelProperty(value="文件名")
	private String fileName;
	// 更新说明
	@ApiModelProperty(value="更新说明")
	private String updateDescribe;
	// 设备别名
	@ApiModelProperty(value="设备名称(暂时不用)")
	private String deviceName;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	// 设备类型
	@ApiModelProperty(value="设备类型")
	private Integer deviceType;
	@ApiModelProperty(value="资源文件的url")
	private String fileUrl1;

	@ApiModelProperty(value="文件大小")
	private Integer fileSize;
	@ApiModelProperty(value="文件MD5")
	private String md5;
}
