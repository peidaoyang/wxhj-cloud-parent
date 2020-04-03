/**
 * 
 */
package com.wxhj.cloud.business.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;

import lombok.Data;

/**
 * @ClassName: DriverInfoDO.java
 * @author: cya
 * @Date: 2020年2月4日 下午5:30:59 
 */
@Table(name="driver_info")
@Data
public class DriverInfoDO extends AbstractEntity<DriverInfoDO>
	implements ICreationAudited{
	@Id
	private String id;
	private String jobNumber;
	private String driverNumber;
	private String accountId;
	private String name;
	private String idNumber;
	private String organizeId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date creatorTime;
	private String creatorUserId;
}
