/**
 * 
 */
package com.wxhj.cloud.platform.dto.response;



import javax.persistence.Id;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: SysRoleResponseDTO.java
 * @author: cya
 * @Date: 2019年11月7日 上午9:28:09 
 */
@Data
public class SysRoleResponseDTO {
	@Id
	private String id;
	private String organizeId;
	private Integer category=1;
	private String encode;
	private String fullName;
	private String type;
	private Integer isAllowEdit=0;
	private Integer isAllowDelete=0;
	private Integer sortCode;
	private Integer isEnabledMark=0;
	private String description;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creatorTime;
	private String creatorUserId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastModifyTime;
	private String lastModifyUserId;
	private String organizeName;
}
