package com.wxhj.cloud.account.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Data
@Table(name = "account_consume")
public class AccountConsumeDO {
	@Id
	private String consumeId;
	private String accountId;
	private String deviceId;
	private String orderNumber;
	private Integer consumeMoney;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date consumeDate;
	private String organizeId;
	private String sceneId;
	private Integer isRevoke;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date recordDatetime;
	//设备流水号
	private Long serialNumber;
}
