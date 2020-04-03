/**
 * 
 */
package com.wxhj.cloud.business.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @ClassName: RouteInfoDO.java
 * @author: cya
 * @Date: 2020年2月4日 下午3:26:21 
 */
@Table(name="route_info")
@Data
public class RouteInfoDO {
	@Id
	private String id;
	private String routeNumber;
	private String routeName;
	private String startSite;
	private String endSite;
	private String channelSite;
	private String organizeId;
}
