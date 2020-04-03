/**
 * 
 */
package com.wxhj.cloud.account.vo;

import lombok.Data;

/**
 * @ClassName: ViewRechargeSummaryVO.java
 * @author: cya
 * @Date: 2020年2月28日 上午9:21:35 
 */
@Data
public class ViewRechargeSummaryVO {
	private String rechargeTime;
	private Integer count;
	private Double totalAmount;

	public void setTotalAmount(Double totalAmount) {
		this.totalAmount = totalAmount;
	}
	
	
}
