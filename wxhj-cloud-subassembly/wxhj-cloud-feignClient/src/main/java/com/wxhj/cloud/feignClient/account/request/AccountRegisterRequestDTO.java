/** 
 * @fileName: AccountRegisterRequestDTO.java  
 * @author: pjf
 * @date: 2019年10月29日 下午3:00:39 
 */

package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @className AccountRegisterRequestDTO.java
 * @author pjf
 * @date 2019年10月29日 下午3:00:39
 */
@Data
@ApiModel(value = "accountRegisterRequestBO", description = "用户注册请求对象")
@AllArgsConstructor
public class AccountRegisterRequestDTO {
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

	@ApiModelProperty(value = "商户id", example = "255805eb-0c76-46c2-b5d4-25c4829e8594")
	@NotBlank(message = "organizeId can not be empty")
	private String organizeId;
	@ApiModelProperty(value = "子商户id", example = "255805eb-0c76-46c2-b5d4-25c4829e8594")
	@NotBlank(message = "organizeId can not be empty")
	private String childOrganizeId;
	
	@ApiModelProperty(value="其他编号（学号/工号 之类）",example="abcdefg")
	private String otherCode;

	@ApiModelProperty(value = "卡号",example = "sb0000317")
	private String cardNumber;
}
