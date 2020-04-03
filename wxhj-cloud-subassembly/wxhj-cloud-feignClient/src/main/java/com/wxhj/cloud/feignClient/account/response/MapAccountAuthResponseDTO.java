/**
 * @className MapAccountAuthResponseDTO.java
 * @admin jwl
 * @date 2020年1月16日 下午2:18:42
 */
package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className MapAccountAuthResponseDTO.java
 * @admin jwl
 * @date 2020年1月16日 下午2:18:42
 */
@Data
@ApiModel(value = "权限用户返回对象")
public class MapAccountAuthResponseDTO {
	private String id;
	private String authorityGroupId;
	private String accountId;
}
