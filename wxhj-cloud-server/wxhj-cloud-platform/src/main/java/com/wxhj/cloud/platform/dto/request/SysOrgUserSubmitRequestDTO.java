package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: SysOrgUserSubmitRequestDTO.java
 * @author: cya
 * @Date: 2020年3月10日 上午9:14:42 
 */
@ToString
@Data
@ApiModel(description = "修改/新增组织用户请求对象")
public class SysOrgUserSubmitRequestDTO {
	@ApiModelProperty(value = "姓名", example = "张三")
	@NotBlank
	private String realName;
	@ApiModelProperty(value = "手机", example = "13912345678")
	@NotBlank
	private String mobilePhone;
	@ApiModelProperty(value = "身份证号", example = "110101199003079278")
	@Pattern(regexp = "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "idNumber error")
	private String idNumber;
	@ApiModelProperty(value = "性别0为男1为女", example = "0")
	@Min(value = 0)
	@Max(value = 1)
	private Integer sex;
}
