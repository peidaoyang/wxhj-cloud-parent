/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;



import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: RideInfoListByAccoutIdRequestDTO.java
 * @author: cya
 * @Date: 2020年2月7日 下午1:42:10 
 */
@Data
@ApiModel(value="app乘车记录查询请求对象")
public class RideInfoListByAccoutIdRequestDTO implements IPageRequestModel {
	@ApiModelProperty(value = "用户id", example = "0000000028")
	@NotNull
	private String accountId;
	@ApiModelProperty(value="开始时间",example="2019-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime beginTime;
	@ApiModelProperty(value="结束时间",example="2020-06-11 00:00:00")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime endTime;
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "creator_time")
	private String orderBy;
}
