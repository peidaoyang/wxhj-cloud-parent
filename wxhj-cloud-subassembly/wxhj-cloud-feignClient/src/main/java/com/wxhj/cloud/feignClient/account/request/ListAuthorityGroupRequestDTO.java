/**
 * @className ListAuthorityGroupRequestDTO.java
 * @admin jwl
 * @date 2019年12月20日 下午2:26:00
 */
package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @className ListAuthorityGroupRequestDTO.java
 * @admin jwl
 * @date 2019年12月20日 下午2:26:00
 */
@Data
@ApiModel(value = "通过编号获取权限组请求对象")
public class ListAuthorityGroupRequestDTO {
	private List<String> authorityId;
}
