/**
 * @className AccountLoginOrganizeRequestDTO.java
 * @admin jwl
 * @date 2019年12月11日 上午11:25:51
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className AccountLoginOrganizeRequestDTO.java
 * @admin jwl
 * @date 2019年12月11日 上午11:25:51
 */
@Data
@ToString
@ApiModel(value = "用户登录查询组织请求对象")
public class AccountLoginOrganizeRequestDTO {
	@ApiModelProperty(value = "用户名",example = "13922222222")
	@NotBlank
	private String userName;
}
