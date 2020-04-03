/**
 * @className listEntranceGroupRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午5:38:30
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.Min;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className listEntranceGroupRequestDTO.java
 * @admin jwl
 * @date 2020年1月10日 下午5:38:30
 */
@Data
public class ListEntranceGroupRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "条件参数", example = "")
	private String nameValue;
	@ApiModelProperty(value = "组织编号", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	private String organizeId;
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "id")
	private String orderBy;
}
