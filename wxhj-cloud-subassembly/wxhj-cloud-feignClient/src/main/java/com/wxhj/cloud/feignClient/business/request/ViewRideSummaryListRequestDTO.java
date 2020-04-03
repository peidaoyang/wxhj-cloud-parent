/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: viewRideSummaryListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月6日 下午3:02:03 
 */
@Data
@ApiModel( value = "班次汇总分页查询请求对象")
public class ViewRideSummaryListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="开始时间",example="yyyy-MM-dd")
	private String beginTime;
	@NotBlank
	@ApiModelProperty(value="结束时间",example="yyyy-MM-dd")
	private String endTime;
}
