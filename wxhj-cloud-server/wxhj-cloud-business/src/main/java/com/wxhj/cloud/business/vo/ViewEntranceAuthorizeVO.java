/** 
 * @fileName: ViewEntranceAuthorizeVO.java  
 * @author: pjf
 * @date: 2020年2月25日 下午4:40:15 
 */

package com.wxhj.cloud.business.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import lombok.Data;

/**
 * @className ViewEntranceAuthorizeVO.java
 * @author pjf
 * @date 2020年2月25日 下午4:40:15
 */

@Data
public class ViewEntranceAuthorizeVO implements IOrganizeModel {
	private String authorityId;
	private String entranceId;
	private String fullName;
	private String organizeId;
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	private Date applyDate;
	private String entranceGroupName;
	private Integer groupType;

	private String organizeName;
}
