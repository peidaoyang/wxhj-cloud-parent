/** 
 * @fileName: RealNameAuthenticationRequestDTO.java  
 * @author: pjf
 * @date: 2019年10月29日 下午3:43:27 
 */

package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className RealNameAuthenticationRequestDTO.java
 * @author pjf
 * @date 2019年10月29日 下午3:43:27
 */
@Data
@ApiModel(value = "realNameAuthenticationRequestBO", description = "实名认证请求对象")
public class RealNameAuthRequestDTO {
	@ApiModelProperty(value = "用户id", example = "00000001")
	@NotBlank(message = "不能为空")
	private String accountId;
	@ApiModelProperty(value = "用户姓名", example = "张三")
	@NotBlank(message = "不能为空")
	private String accountName;
	@ApiModelProperty(value = "身份证号", example = "110101199003079278")
	@Pattern(regexp = "^[1-9]\\d{5}(18|19|20|(3\\d))\\d{2}((0[1-9])|(1[0-2]))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$", message = "idNumber error")
	private String idNumber;
	@ApiModelProperty(value = "卡片正面uuid", example = "f6d23ebf-4063-4eb8-8c87-4075b0b3fdb7.jpg")
	@NotBlank(message = "不能为空")
	private String frontCardUuid;
	@ApiModelProperty(value = "卡片反面uuid", example = "9e35dc1e-ced3-47dc-a90c-28f5bc7688ff.jpg")
	@NotBlank(message = "不能为空")
	private String backCardUuid;
}
