/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import java.util.Date;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: VisitorInfoApp.java
 * @author: cya
 * @Date: 2020年2月11日 下午5:58:49 
 */
@ApiModel(value="app访客信息查询请求对象")
@Data
public class VisitorInfoAppRequestDTO extends CommonPageRequestDTO {
	@ApiModelProperty(value="accountId：受访者id")
	@NotBlank
	private String type;
	@ApiModelProperty(value="审核状态,0:未审核，1：通过，2：拒绝, -1：全部",example = "1")
	@Min(-1)
	@Max(4)
	private Integer isCheck;
	
	@ApiModelProperty(value="开始时间",example="2020-01-01")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date beginTime;
	@ApiModelProperty(value="结束时间",example="2020-01-01")
	@JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	private Date endTime;


}
