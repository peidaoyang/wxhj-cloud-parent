package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("平台新增账户请求对象")
public class SubmitAccountRequestDTO {
	@ApiModelProperty(value = "手机号", example = "13922222222")
	@Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "mobilePhone error")
	private String phoneNumber;
	@ApiModelProperty(value = "真实姓名", example = "张三")
	@NotBlank(message = "realName can not be empty")
	private String name;
	@ApiModelProperty(value = "身份证号", example = "110101199003079278")
	@Pattern(regexp = "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "idNumber error")
	private String idNumber;
	@ApiModelProperty(value = "性别0为男1为女", example = "0")
	@Min(value = 0)
	@Max(value = 1)
	private Integer sex;
	@ApiModelProperty(value = "账户类型", example = "0")
	@Min(value = 0)
	@Max(value = 9)
	private Integer accountType;
}
