///**
// *
// */
//package com.wxhj.cloud.account.dto.response;
//
//
//
//import io.swagger.annotations.ApiModelProperty;
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//
//import lombok.Data;
//
///**
// * @ClassName: AppRechargeInfoResponseDTO.java
// * @author: cya
// * @Date: 2020年2月2日 下午2:49:39
// */
//@Data
//public class AppRechargeInfoResponseDTO {
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@ApiModelProperty(value = "记录创建时间")
//	private Date creatorTime;
//	@ApiModelProperty(value = "充值金额（单位元）")
//	private Double amount;
//	@ApiModelProperty(value = "账户余额（单位元）")
//	private Double accountBalance;
//	@ApiModelProperty(value = "账户id")
//	private String accountId;
//	@ApiModelProperty(value = "充值编号")
//	private String id;
//
//	public void setAmount(Double amount) {
//		this.amount = amount/100.00;
//	}
//
//	public void setAccountBalance(Double accountBalance) {
//		this.accountBalance = accountBalance/100.00;
//	}
//
//
//}
