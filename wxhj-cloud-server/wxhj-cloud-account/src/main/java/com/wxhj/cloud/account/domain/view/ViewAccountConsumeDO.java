/** 
 * @fileName: ViewAccountConsume.java  
 * @author: pjf
 * @date: 2020年2月5日 上午11:01:57 
 */

package com.wxhj.cloud.account.domain.view;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @className ViewAccountConsume.java
 * @author pjf
 * @date 2020年2月5日 上午11:01:57
 */

@Data
@Table(name = "view_account_consume")
public class ViewAccountConsumeDO {
	@Id
	private String consumeId;
	private String accountName;
	private String accountId;
	private String deviceId;
	private String orderNumber;
	private Integer consumeMoney;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date consumeDate;
	private String organizeId;
	private String sceneId;
	private String deviceName;
	private Integer isRevoke;
}
