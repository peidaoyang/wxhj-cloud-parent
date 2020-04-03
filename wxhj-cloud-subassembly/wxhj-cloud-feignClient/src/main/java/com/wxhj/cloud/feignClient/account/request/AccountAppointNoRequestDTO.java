package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "accountAppointNoRequestDTO", description = "人脸批量注册请求对象")
public class AccountAppointNoRequestDTO {
	@ApiModelProperty(value = "查询编号类型 1为手机号,2为身份证,3为其他")
	@Min(0)
	private Integer noType;

	@ApiModelProperty(value = "组织id")
	@NotBlank()
	private String organizeId;

	@ApiModelProperty(value = "编号")
	@NotBlank()
	private String no;
}
