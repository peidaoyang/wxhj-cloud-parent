/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: AppFlightListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月6日 下午4:38:28 
 */
@Data
public class AppFlightListRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "模糊查询", example = "测试班次")
	@NotNull
	private String nameValue;
	
	@ApiModelProperty(value="每页条数",example="1")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value="页数",example="1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value="排序字段", example="start_time desc")
	private String orderBy;
}
