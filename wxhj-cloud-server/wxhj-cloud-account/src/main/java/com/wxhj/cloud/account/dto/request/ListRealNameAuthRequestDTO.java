/**
 * @className ListRealNameAuthRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 上午8:47:29
 */
package com.wxhj.cloud.account.dto.request;

import javax.validation.constraints.Min;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className ListRealNameAuthRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 上午8:47:29
 */
@Data
@ToString
public class ListRealNameAuthRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "条件参数", example = "")
	private String nameValue;
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "account_id")
	private String orderBy;
}
