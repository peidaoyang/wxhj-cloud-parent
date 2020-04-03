/** 
 * @fileName: DeleteMapAccountAuthRequestDTO.java  
 * @author: pjf
 * @date: 2019年11月6日 下午4:59:13 
 */

package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className DeleteMapAccountAuthRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 下午4:59:13   
*/
/**
 * @className DeleteMapAccountAuthRequestDTO.java
 * @author pjf
 * @date 2019年11月6日 下午4:59:13
 */
@Data
@ToString
@ApiModel(value = "deleteMapAccountAuthRequest", description = "MapAccountAuth删除请求对象")
public class DeleteMapAccountAuthRequestDTO {
	@ApiModelProperty(value = "权限组对应id")
	@NotBlank(message = "authorityGroupId can not be empty")
	private String authorityGroupId;
	@ApiModelProperty(value = "用户id")
	@NotBlank(message = "accountId can not be empty")
	private String accountId;
}
