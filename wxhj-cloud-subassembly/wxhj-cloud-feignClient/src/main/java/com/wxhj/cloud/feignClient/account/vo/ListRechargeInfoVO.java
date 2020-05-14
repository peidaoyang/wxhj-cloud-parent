package com.wxhj.cloud.feignClient.account.vo;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeUserModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: ListRechargeInfoVO.java
 * @author: cya
 * @Date: 2020年2月28日 上午9:09:55 
 */
@Data
public class ListRechargeInfoVO implements IOrganizeUserModel{
	@ApiModelProperty(value="充值编号")
	private String id;
	@ApiModelProperty(value="账户id")
	private String accountId;
	@ApiModelProperty(value="充值金额")
	private Double amount;
	@ApiModelProperty(value="手续费")
	private Double serviceAmount;
	@ApiModelProperty(value="账户余额")
	private Double accountBalance;
	@ApiModelProperty(value="充值方类型 0为默认（后台），1为app，2为小程序，3为其他第三方")
	private Integer type;
	@ApiModelProperty(value="充值类型 0为默认（后台），1为支付宝，2为微信，3为其他第三方")
	private Integer payType;
	@ApiModelProperty(value="第三方流水号")
	private String payNo;
	@ApiModelProperty(value="预充值流水号")
	private String prechargeNo;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="创建时间")
	private LocalDateTime creatorTime;
	
	@ApiModelProperty(value="操作人编号（不能排序）")
	private String creatorUserId;
	@ApiModelProperty(value="操作人员（不能排序）")
	private String creatorUserName;
	
	@ApiModelProperty(value="组织编号（不能排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不能排序）")
	private String organizeName;
	
	public void setAmount(Double amount) {
		this.amount = amount/100.00;
	}

	public void setServiceAmount(Double serviceAmount) {
		this.serviceAmount = serviceAmount/100.00;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance/100.00;
	}
}
