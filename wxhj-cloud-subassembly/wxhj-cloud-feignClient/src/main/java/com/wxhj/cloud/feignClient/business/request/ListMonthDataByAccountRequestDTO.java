/**
 * @className ListMonthDataByAccountRequestDTO.java
 * @admin jwl
 * @date 2020年1月15日 下午2:02:55
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className ListMonthDataByAccountRequestDTO.java
 * @admin jwl
 * @date 2020年1月15日 下午2:02:55
 */
@Data
@ApiModel(value = "根据用户获取汇总报表请求对象")
public class ListMonthDataByAccountRequestDTO{
	@ApiModelProperty(value = "开始时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	@ApiModelProperty(value = "结束时间", example = "2019-12-22")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;
	@ApiModelProperty(value = "按周或月查询,周为0/月为1", example = "0")
	@Min(0)
	@Max(2)
	private Integer groupType;
//	@ApiModelProperty(value = "用户登录返回对象")
//	@NotNull
//	private AuthenticationTokenBO authenticationToken;
	
	@ApiModelProperty(value = "账户id", example = "0000000028")
	@Size(min=10,max=10)
	private String accountId;
	@ApiModelProperty(value = "账户id", example = "用户1")
	@NotNull
	private String name;
}
