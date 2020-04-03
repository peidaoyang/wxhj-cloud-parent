/**
 * 
 */
package com.wxhj.cloud.platform.dto.response;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: VerificaImgResponseDTO.java
 * @author: cya
 * @Date: 2020年3月19日 下午5:22:28 
 */
@AllArgsConstructor
@Data
public class VerificaImgResponseDTO {
	@ApiModelProperty(value="验证码图片base64格式")
	private String verificaImg;
	@ApiModelProperty(value="验证码key值")
	private String verificaKey;
}
