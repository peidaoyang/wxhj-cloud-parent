/** 
 * @fileName: AccountRegisterResponseDTO.java  
 * @author: pjf
 * @date: 2019年10月29日 下午3:41:53 
 */

package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className AccountRegisterResponseDTO.java
 * @author pjf
 * @date 2019年10月29日 下午3:41:53
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class AccountRegisterResponseDTO {
	@ApiModelProperty(value="后台分配的账户id")
	private String accountId;
}
