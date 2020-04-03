/** 
 * @fileName: FileUploadRequestBO.java  
 * @author: pjf
 * @date: 2019年11月20日 下午3:54:29 
 */

package com.wxhj.cloud.file.dto;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className FileUploadRequestBO.java
 * @author pjf
 * @date 2019年11月20日 下午3:54:29
 */

@Data
@ApiModel( description = "文件上送请求对象")
public class FileUploadRequestDTO {
	@ApiModelProperty(value = "文件内容(base64之后的内容)", example = "")
	@NotBlank(message = "不能为空")
	String fileContent;
	@ApiModelProperty(value = "文件后缀名", example = ".jpg")
	@Pattern(regexp = "^.(txt|jpg|csv|apk|zip)$", message = "illegal suffix")
	String fileSuffix;
}
