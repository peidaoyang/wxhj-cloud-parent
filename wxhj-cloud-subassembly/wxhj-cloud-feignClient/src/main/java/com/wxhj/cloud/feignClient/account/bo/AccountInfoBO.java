/** 
 * @fileName: AccountInfoBO.java  
 * @author: pjf
 * @date: 2020年2月6日 下午2:50:46 
 */

package com.wxhj.cloud.feignClient.account.bo;



import lombok.Data;

import java.time.LocalDateTime;

/**
 * @className AccountInfoBO.java
 * @author pjf
 * @date 2020年2月6日 下午2:50:46   
*/
/**
 * @className AccountInfoBO.java
 * @author pjf
 * @date 2020年2月6日 下午2:50:46
 */
@Data
public class AccountInfoBO {
	private String accountId;
	private String phoneNumber;
	private String name;
	private String idNumber;
	private Integer sex;
	private Integer accountType;
	private String organizeId;
	private LocalDateTime createTime;
	private Integer rechargeTotalAmount;
	private Integer consumeTotalAmount;
	private Integer consumeTotalFrequency;
	private Double accountBalance;
	private LocalDateTime accountValidity;
	private String memo;
	private String userPassword;
	private String userSecretKey;
	private Integer isReal;
	private Integer isFace;
	private String childOrganizeId;
	public void setAccountBalance(Double accountBalance) {
		this.accountBalance = accountBalance/100.00;
	}
	
	
}
