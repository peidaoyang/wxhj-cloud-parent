/** 
 * @fileName: SysOrganizeBO.java  
 * @author: pjf
 * @date: 2020年2月5日 下午12:22:06 
 */

package com.wxhj.cloud.feignClient.platform.bo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @className SysOrganizeBO.java
 * @author pjf
 * @date 2020年2月5日 下午12:22:06   
*/
/** 
 * @className SysOrganizeBO.java
 * @author pjf
 * @date 2020年2月5日 下午12:22:06 
*/
@Data
public class SysOrganizeBO {
	private String id;
	private String parentId;
	private Integer layers;
	private String encode;
	private String fullName;
	private String shortName;
	private String categoryId;
	private String managerId;
	private String telephone;
	private String mobilePhone;
	private String wechat;
	private String fax;
	private String email;
	private String areaid;
	private String address;
	private Integer isAllowEdit;
	private Integer isAllowDelete;
	private Integer sortCode;
	private Integer isDeleteMark;
	private Integer isEnabledMark;
	private String description;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private String creatorUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	private String lastModifyUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date deleteTime;
	private String deleteUserId;
}
