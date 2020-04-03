/**
 * 
 */
package com.wxhj.cloud.file.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import org.springframework.web.multipart.MultipartFile;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: FilesUploadRequestDTO.java
 * @author: cya
 * @Date: 2019年12月3日 下午3:48:05 
 */
@Data
@ApiModel( description = "多文件上送请求对象")
public class FilesUploadRequestDTO {
	@ApiModelProperty(value = "form-data方式提交的文件")
	@NotBlank(message = "不能为空")
	MultipartFile[] multipartFiles;
	
	@ApiModelProperty(value = "文件后缀名", example = ".jpg")
	@Pattern(regexp = "^.(txt|jpg|csv)$", message = "illegal suffix")
	String fileSuffix;
}
