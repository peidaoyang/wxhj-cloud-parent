package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @ClassName: FlightDO.java
 * @author: cya
 * @Date: 2020年2月5日 下午3:42:06 
 */
@Data
@Table(name="flight_info")
public class FlightInfoDO {
	@Id
	private String id;
	private String flightNumber;
	private String routeNumber;
	private String organizeId;
	private String carNumber;
	private Integer startTime;
}
