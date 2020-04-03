package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "新增/修改 公告休息请求对象")
public class SubmitAnnouncementRequestDTO {
	@ApiModelProperty(value = "主键",example="1202078c-3141-437c-97d7-385c953db111")
	private String id;
	
	@ApiModelProperty(value = "公告",example = "通知：xxxx")
	@NotBlank
	private String content;
	@ApiModelProperty(value = "组织id",example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
	
	@ApiModelProperty(value = "标题",example = "标题1")
	@NotBlank
	private String title;
	@ApiModelProperty(value = "登录用户的id")
	@NotBlank
	private String userId;
}
