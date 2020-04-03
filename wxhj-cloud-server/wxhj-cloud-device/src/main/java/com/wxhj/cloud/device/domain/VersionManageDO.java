package com.wxhj.cloud.device.domain;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;
import com.wxhj.cloud.driud.infrastructure.IModificationAudited;

import lombok.Data;

/**
 * @ClassName: VersionManage.java
 * @author: cya
 * @Date: 2020年1月2日 下午4:36:13 
 */
@Data
@Table(name="version_manage")
public class VersionManageDO extends AbstractEntity<VersionManageDO> 
	implements ICreationAudited,IModificationAudited{
	@Id
	private String id;
	private String versionNumber;
	private Integer deviceType;
	private String fileName;
	private Integer releaseState;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	private String creatorUserId;
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	private String lastModifyUserId;
	private String updateDescribe;
	private Integer resourceType;
	private String organizeId;
	
}
