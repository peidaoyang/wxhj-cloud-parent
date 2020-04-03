package com.wxhj.cloud.account.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @className AccountConsumeRocjetBO.java
 * @author jwl
 * @date 2020年1月31日 下午5:19:18
 */
@Data
public class AccountConsumeRocjetBO {
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	//消费时间
	private Date consumeDate;
	//订单号
	private String orderNumber;
	//账户id
	private String accountId;
	//消费金额(分)
	private Integer consumeMoney;
	//设备id
	private String deviceId;
	//组织id
	private String organizeId;
	//场景id
	private String sceneId;
	//设备名称
	private String deviceName;
}
