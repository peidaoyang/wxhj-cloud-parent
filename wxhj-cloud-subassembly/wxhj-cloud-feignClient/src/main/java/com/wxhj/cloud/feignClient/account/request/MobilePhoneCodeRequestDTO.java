/** 
 * @fileName: MobilePhoneCodeRequestDTO.java  
 * @author: pjf
 * @date: 2019年10月29日 下午2:00:44 
 */

package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(value = "mobilePhoneCodeRequestBO", description = "获取手机验证码请求对象")
public class MobilePhoneCodeRequestDTO {
	@ApiModelProperty(value = "手机号", example = "13922222222")
	@Pattern(regexp = "^[1]([3-9])[0-9]{9}$", message = "mobilePhone error")
	private String mobilePhone;
}
