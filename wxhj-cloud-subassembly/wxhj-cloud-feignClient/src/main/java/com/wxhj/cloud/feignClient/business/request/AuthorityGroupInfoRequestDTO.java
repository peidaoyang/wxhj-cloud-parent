/**
 * @className AuthorityGroupInfoRequestDTO.java
 * @admin jwl
 * @date 2019年12月23日 上午10:15:19
 */
package com.wxhj.cloud.feignClient.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className AuthorityGroupInfoRequestDTO.java
 * @admin jwl
 * @date 2019年12月23日 上午10:15:19
 */
@Data
@ApiModel(value= "权限组请求对象")
public class AuthorityGroupInfoRequestDTO {
	@ApiModelProperty(value = "权限组编号")
	private String authorityId;
	@ApiModelProperty(value = "权限组名称")
	private String fullName;
	@ApiModelProperty(value = "组织编号")
	private String organizeId;
}
