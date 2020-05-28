package com.wxhj.cloud.account.vo;




import com.wxhj.cloud.feignClient.bo.ICardNameOrganizeModel;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeUserModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * @ClassName: RechargeExcelResponseDTO.java
 * @author: cya
 * @Date: 2020年2月1日 下午3:14:14 
 */
@Data
@ToString
@ApiModel(description = "账户充值明细报表")
@ExcelDocumentAnnotation
public class RechargeExcelVO implements IOrganizeUserModel, ICardNameOrganizeModel {
	@ApiModelProperty(value = "充值流水号")
	@ExcelColumnAnnotation(columnName = "recharge.id")
	private String id;
	@ApiModelProperty(value = "账户编号")
	@ExcelColumnAnnotation(columnName = "recharge.accountId")
	private String accountId;
	@ApiModelProperty(value = "充值金额")
	@ExcelColumnAnnotation(columnName = "recharge.amount")
	private Double amount;
	@ApiModelProperty(value = "手续费")
	@ExcelColumnAnnotation(columnName = "recharge.serviceAmount")
	private Double serviceAmount;
	@ApiModelProperty(value = "充值方类型")
	@ExcelColumnAnnotation(columnName = "recharge.type")
	private Integer type;
	@ApiModelProperty(value = "充值类型")
	@ExcelColumnAnnotation(columnName = "recharge.payType")
	private Integer payType;
	@ApiModelProperty(value = "卡类型")
	private Integer cardType;
	@ApiModelProperty(value = "卡名称")
	@ExcelColumnAnnotation(columnName = "recharge.cardName")
	private String cardName;
	@ApiModelProperty(value = "创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ExcelColumnAnnotation(columnName = "recharge.creatorTime")
	private LocalDateTime creatorTime;
	@ApiModelProperty(value ="用户名")
	@ExcelColumnAnnotation(columnName = "recharge.name")
	private String name;
	
	@ApiModelProperty(value = "组织名称")
	@ExcelColumnAnnotation(columnName = "recharge.organizeName")
	private String organizeName;
	@ApiModelProperty(value = "组织Id")
	@ExcelColumnAnnotation(columnName = "recharge.organizeId")
	private String organizeId;
	@ApiModelProperty(value = "用户Id")
	@ExcelColumnAnnotation(columnName = "recharge.creatorUserId")
	private String creatorUserId;
	@ApiModelProperty(value = "用户名称")
	@ExcelColumnAnnotation(columnName = "recharge.creatorUserName")
	private String creatorUserName;

	public void setAmount(Double amount) {
		this.amount = amount/100.00;
	}

	public void setServiceAmount(Double serviceAmount) {
		this.serviceAmount = serviceAmount/100.00;
	}
	
	
}
