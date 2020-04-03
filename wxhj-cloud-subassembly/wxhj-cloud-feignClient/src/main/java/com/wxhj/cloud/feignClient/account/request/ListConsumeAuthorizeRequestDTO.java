/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ListConsumeAuthorizeRequestDTO.java
 * @author: cya
 * @Date: 2020年3月11日 下午4:39:40 
 */
@Data
@ApiModel("消费权限分页查询请求对象")
public class ListConsumeAuthorizeRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "模糊查询", example = "")
	@NotNull
	private String nameValue;
	@ApiModelProperty(value = "组织id", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value="每页条数")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value="页数")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value="排序字段", example="appyly_date asc/desc")
	private String orderBy;
}
