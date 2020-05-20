//package com.wxhj.cloud.platform.dto.response;
//
//
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
//
//import lombok.Data;
//
//@Data
//public class AnnouncementListResponseDTO implements IOrganizeModel{
//	//编号
//	private String id;
//	//内容简介
//	private String content;
//	//标题
//	private String title;
//	//组织主键
//	private String organizeId;
//	//组织名称
//	private String organizeName;
//	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
//	//创建时间
//	private Date creatorTime;
//	//创建人id
//	private String creatorUserId;
//}
