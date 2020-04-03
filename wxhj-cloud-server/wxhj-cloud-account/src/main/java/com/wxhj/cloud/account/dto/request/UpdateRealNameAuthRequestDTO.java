/**
 * @className UpdateRealNameAuthRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 上午9:18:12
 */
package com.wxhj.cloud.account.dto.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className UpdateRealNameAuthRequestDTO.java
 * @admin jwl
 * @date 2019年12月13日 上午9:18:12
 */
@Data
@ToString
@ApiModel(value = "修改人脸状态请求对象")
public class UpdateRealNameAuthRequestDTO {
	@ApiModelProperty(value = "用户账号", example = "0000000028")
	private String accountId;
	@ApiModelProperty(value = "是否拥有人脸", example = "0")
	private Integer isApprove;
}
