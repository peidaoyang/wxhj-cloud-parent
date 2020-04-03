/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VisitInfoListRequestDTO.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:42:00 
 */
@ApiModel(value="访客记录 查询请求对象")
@Data
public class VisitInfoListRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="开始时间",example="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	@ApiModelProperty(value="结束时间",example="yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;

}
