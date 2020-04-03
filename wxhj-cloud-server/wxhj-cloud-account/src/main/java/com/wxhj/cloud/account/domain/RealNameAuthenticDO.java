/**
 * @className RealNameAuthenticDO.java
 * @admin jwl
 * @date 2019年12月12日 上午11:22:14
 */
package com.wxhj.cloud.account.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;
import lombok.ToString;

/**
 * @className RealNameAuthenticDO.java
 * @admin jwl
 * @date 2019年12月12日 上午11:22:14
 */
@Table(name = "real_name_authentic")
@Data
@ToString
public class RealNameAuthenticDO {
	@Id
	private String accountId;
	private String accountName;
	private String idNumber;
	private String frontCardUuid;
	private String backCardUuid;
	private Integer isApprove;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private Date approveTime;
}
