/**
 * 
 */
package com.wxhj.cloud.account.dto.response;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @ClassName: AppRechargeInfoResponseDTO.java
 * @author: cya
 * @Date: 2020年2月2日 下午2:49:39 
 */
@Data
public class AppRechargeInfoResponseDTO {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private Double amount;
	private Double accountBalance;

	public void setAmount(Double amount) {
		this.amount = amount;
	}

	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance;
	}
	
	
}
