/**
 * 
 */
package com.wxhj.cloud.account.dto.response;

import java.util.Date;

import lombok.Data;

/**
 * @ClassName: AppAccountInfoResponseDTO.java
 * @author: cya
 * @Date: 2020年2月3日 下午12:11:12 
 */
@Data
public class AppAccountInfoResponseDTO {
	private String accountId;
	private Integer accountType;
	private Date createTime;
	private Double rechargeTotalAmount;
	private Double consumeTotalAmount;
	private Integer consumeTotalFrequency;
	private Double accountBalance;
	private Date accountValidity;
	private String name;
	private Integer sex;
	private String phoneNumber;
	private String memo;
	private String organizeId;
	private String idNumber;
	private Integer isReal;
	private Integer isFace;
	
	public void setRechargeTotalAmount(Double rechargeTotalAmount) {
		this.rechargeTotalAmount = rechargeTotalAmount/100.00;
	}

	public void setConsumeTotalAmount(Double consumeTotalAmount) {
		this.consumeTotalAmount = consumeTotalAmount/100.00;
	}
	
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance/100.00;
	}
}
