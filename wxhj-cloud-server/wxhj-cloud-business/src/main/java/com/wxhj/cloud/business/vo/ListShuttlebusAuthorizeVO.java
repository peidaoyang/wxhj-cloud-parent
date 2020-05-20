/**
 * 
 */
package com.wxhj.cloud.business.vo;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: ListRouteAuthorizeVO.java
 * @author: cya
 * @Date: 2020年3月12日 下午1:41:56 
 */
@Data
public class ListShuttlebusAuthorizeVO implements IOrganizeModel{
	private String authorityId;
	private String routeId;
	private String organizeId;
	private String organizeName;
	
	private String fullname;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime appylyDate;
}
