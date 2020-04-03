package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.Min;

import com.esotericsoftware.kryo.NotNull;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "分页查下公告休息请求对象")
public class AnnouncementListRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "组织id",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	private String organizeId;
	
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "id")
	@NotNull
	private String orderBy;
	
}
