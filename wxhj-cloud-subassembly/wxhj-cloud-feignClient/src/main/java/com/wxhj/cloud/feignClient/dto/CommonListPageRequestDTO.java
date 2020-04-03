package com.wxhj.cloud.feignClient.dto;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.utils.HumpUtil;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@ToString
@ApiModel(value = "通用带组织id的分页查询")
@NoArgsConstructor
@AllArgsConstructor
@Data
public class CommonListPageRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "查询内容", example = "")
	protected String nameValue;
	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotBlank(message = "不能为空")
	protected String organizeId;
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	protected Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	protected Integer page;
	@ApiModelProperty(value = "排序字段")
	@NotBlank(message = "不能为空")
	protected String orderBy;

	
	@Override
	public void setOrderBy(String orderBy) {
		this.orderBy = HumpUtil.humpToLine(orderBy);
	}
	
}
