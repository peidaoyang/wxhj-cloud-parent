package com.wxhj.cloud.feignClient.face.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel(value = "faceRegisterBatchRequestDTO", description = "人脸批量注册请求对象")
public class FaceRegisterBatchRequestDTO {
	@ApiModelProperty(value = "原图片名称")
	@NotBlank()
	private String originalImageName;
	@ApiModelProperty(value = "图片名称")
	@NotBlank()
	private String imageName;
	@ApiModelProperty(value = "组织id")
	@NotBlank()
	private String organizeId;
	@ApiModelProperty(value = "图片名称类型 1为手机号,2为身份证,3为其他")
	@Min(0)
	private Integer imageNameType;
}
