/**
 * 
 */
package com.wxhj.cloud.business.domain.view;

import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

/**
 * @ClassName: ViewFlightRouteDO.java
 * @author: cya
 * @Date: 2020年2月6日 下午6:10:21
 */
@Data
@Table(name = "view_flight_route")
public class ViewFlightRouteDO {
	@Id
	private String id;
	private String flightNumber;
	private String routeNumber;
	private String organizeId;
	private String carNumber;
	private Integer startTime;
	private Integer endTime;
	private String startSite;
	private String endSite;
	private String channelSite;
	private String routeName;
}
