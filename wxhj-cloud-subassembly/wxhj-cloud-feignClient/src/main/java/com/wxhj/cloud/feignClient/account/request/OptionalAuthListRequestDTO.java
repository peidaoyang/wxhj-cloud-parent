/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: OptionalAuthListRequestDTO.java
 * @author: cya
 * @Date: 2020年3月17日 下午4:46:37 
 */
@Data
@ApiModel(value="组织及子组织可选权限组id 请求对象")
public class OptionalAuthListRequestDTO {
	@ApiModelProperty(value="根组织id",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String orgId;
	@ApiModelProperty(value="当前组织id",example="c001c8e6-e0ec-4085-a86e-609aae846969")
	@NotBlank
	private String currentOrgId;
}
