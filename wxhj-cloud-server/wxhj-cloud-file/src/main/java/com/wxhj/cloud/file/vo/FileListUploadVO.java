/**
 * 
 */
package com.wxhj.cloud.file.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @ClassName: fileListUploadResponseVO.java
 * @author: cya
 * @Date: 2019年12月5日 下午4:06:54
 */
@Data
@AllArgsConstructor
@ApiModel(description = "多文件上传返回给前端的信息对象")
public class FileListUploadVO {
	@ApiModelProperty(value = "上送来的文件名")
	String oldName;
	@ApiModelProperty(value = "保存到文件名")
	String newName;
}
