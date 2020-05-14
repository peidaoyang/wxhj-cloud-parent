/**
 * 
 */
package com.wxhj.cloud.account.vo;



import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: ListConsumeAuthorizeVO.java
 * @author: cya
 * @Date: 2020年3月11日 下午4:44:22 
 */
@Data
public class ListConsumeAuthorizeVO implements IOrganizeModel{
	private String authorityId;
	private String organizeId;
	private String organizeName;
	
	private String fullname;
	
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime appylyDate;
}
