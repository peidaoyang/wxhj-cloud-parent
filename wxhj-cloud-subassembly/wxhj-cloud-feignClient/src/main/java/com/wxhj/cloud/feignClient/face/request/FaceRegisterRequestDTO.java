/** 
 * @fileName: FaceRegisterRequestBO.java  
 * @author: pjf
 * @date: 2019年11月1日 下午2:41:12 
 */

package com.wxhj.cloud.feignClient.face.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className FaceRegisterRequestBO.java
 * @author pjf
 * @date 2019年11月1日 下午2:41:12   
*/

@Data
@ApiModel(value = "faceRegisterRequestDTO", description = "人脸注册请求对象")
public class FaceRegisterRequestDTO {
	@ApiModelProperty(value = "账户id", example = "0000000008")
	@Size(min=10,max=10)
	private String accountId;
	@ApiModelProperty(value = "图片名称",example = "测试1")
	@NotBlank()
	private String imageName;
}
