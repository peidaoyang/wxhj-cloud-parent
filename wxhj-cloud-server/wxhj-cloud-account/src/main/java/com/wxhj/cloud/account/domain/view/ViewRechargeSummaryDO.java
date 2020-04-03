/**
 * 
 */
package com.wxhj.cloud.account.domain.view;

import javax.persistence.Table;

import lombok.Data;

/**
 * @ClassName: viewRechargeSummary.java
 * @author: cya
 * @Date: 2020年2月4日 下午1:22:35 
 */
@Table(name="view_recharge_summary")
@Data
public class ViewRechargeSummaryDO {
	private String rechargeTime;
	private Integer count;
	private Integer totalAmount;
}
