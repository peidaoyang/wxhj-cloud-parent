package com.wxhj.cloud.account.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "消费明细报表导出对象")
@ExcelDocumentAnnotation
public class AccountConsumeDetailedExcelVO {
	@ApiModelProperty(value = "用户ID")
	@ExcelColumnAnnotation(columnName = "accountConsume.accountId")
	private String accountId;
	@ApiModelProperty(value = "用户名称")
	@ExcelColumnAnnotation(columnName = "accountConsume.accountName")
	private String accountName;
	@ApiModelProperty(value = "消费记录编号")
	@ExcelColumnAnnotation(columnName = "accountConsume.consumeId")
	private String consumeId;
	@ApiModelProperty(value = "场景名称")
	@ExcelColumnAnnotation(columnName = "accountConsume.sceneName")
	private String sceneName;
	@ApiModelProperty(value = "场景Id")
	@ExcelColumnAnnotation(columnName = "accountConsume.sceneId")
	private String sceneId;
	@ApiModelProperty(value = "流水号")
	@ExcelColumnAnnotation(columnName = "accountConsume.orderNumber")
	private String orderNumber;
	@ApiModelProperty(value = "消费金额")
	@ExcelColumnAnnotation(columnName = "accountConsume.consumeMoney")
	private Integer consumeMoney;
	@ApiModelProperty(value = "消费时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelColumnAnnotation(columnName = "accountConsume.consumeDate")
	private Date consumeDate;
	@ApiModelProperty(value = "组织名称")
	@ExcelColumnAnnotation(columnName = "accountConsume.organizeName")
	private String organizeName;
	@ApiModelProperty(value = "组织Id")
	@ExcelColumnAnnotation(columnName = "accountConsume.organizeId")
	private String organizeId;
	@ApiModelProperty(value = "设备id")
	@ExcelColumnAnnotation(columnName = "accountConsume.deviceId")
	private String deviceId;
	@ApiModelProperty(value = "设备名称")
	@ExcelColumnAnnotation(columnName = "accountConsume.deviceName")
	private String deviceName;
}
