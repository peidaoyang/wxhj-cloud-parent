/**
 * 
 */
package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SubmitFlightRequestDTO.java
 * @author: cya
 * @Date: 2020年2月5日 下午5:48:23 
 */
@Data
@ApiModel(value="新增/修改 班次请求对象")
public class SubmitFlightRequestDTO {
	@ApiModelProperty(value="班次id，修改时必填",example="35c803a1-92b1-478b-be5d-b7e44be18111")
	private String id;
	@ApiModelProperty(value="班次编号",example="0001")
	@NotBlank
	private String flightNumber;
	@ApiModelProperty(value="线路编号主键",example="35c803a1-92b1-478b-be5d-b7e44be18fa7")
	@NotBlank
	private String routeNumber;
	@ApiModelProperty(value="组织编号",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value="车号编号",example="00002")
	@Pattern(regexp = "^[京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼使领A-Z]{1}[A-Z]{1}[警京津沪渝冀豫云辽黑湘皖鲁新苏浙赣鄂桂甘晋蒙陕吉闽贵粤青藏川宁琼]{0,1}[A-Z0-9]{4}[A-Z0-9挂学警港澳]{1}$", message = "车牌错误")
	private String carNumber;
	@ApiModelProperty(value="开始时间",example="600")
	@NotNull
	private Integer startTime;
}
