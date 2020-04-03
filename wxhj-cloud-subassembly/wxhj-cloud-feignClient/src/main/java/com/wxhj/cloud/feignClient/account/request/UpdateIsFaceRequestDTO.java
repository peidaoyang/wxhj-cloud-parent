/** 
 * @fileName: UpdateIsFaceRequestDTO.java  
 * @author: pjf
 * @date: 2019年11月27日 上午11:43:26 
 */

package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className UpdateIsFaceRequestDTO.java
 * @author pjf
 * @date 2019年11月27日 上午11:43:26   
*/
/**
 * @className UpdateIsFaceRequestDTO.java
 * @author pjf
 * @date 2019年11月27日 上午11:43:26
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value="更新是否人脸注册请求对象")
public class UpdateIsFaceRequestDTO {
	@ApiModelProperty(value="账户id",example="0000000028")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value="是否人脸注册",example="1")
	@NotNull
	private Integer isFace;
}
