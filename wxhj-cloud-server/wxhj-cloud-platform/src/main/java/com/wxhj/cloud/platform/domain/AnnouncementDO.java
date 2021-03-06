package com.wxhj.cloud.platform.domain;



import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.driud.infrastructure.AbstractEntity;
import com.wxhj.cloud.driud.infrastructure.ICreationAudited;

import lombok.Data;

import java.time.LocalDateTime;

@Data
@Table(name = "announcement")
public class AnnouncementDO extends AbstractEntity<AnnouncementDO> implements ICreationAudited{
	@Id
	private String id;
	private String content;
	private String title;
	private String organizeId;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creatorTime;
	private String creatorUserId;
}
