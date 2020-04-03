/** 
 * @fileName: SsoLogoutRequestDTO.java  
 * @author: pjf
 * @date: 2019年12月10日 下午2:39:25 
 */

package com.wxhj.cloud.platform.dto.request;

import javax.validation.constraints.NotNull;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SsoLogoutRequestDTO.java
 * @author pjf
 * @date 2019年12月10日 下午2:39:25   
*/
/**
 * @className SsoLogoutRequestDTO.java
 * @author pjf
 * @date 2019年12月10日 下午2:39:25
 */
@ToString
@Data
@ApiModel(description = "登出对象")
public class SsoLogoutRequestDTO {
	@ApiModelProperty(value = "登录的sessionId")
	@NotNull
	private String sessionId;

}
