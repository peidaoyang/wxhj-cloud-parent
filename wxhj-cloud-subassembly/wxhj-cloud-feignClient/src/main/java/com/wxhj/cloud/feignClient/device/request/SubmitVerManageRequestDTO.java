/**
 * 
 */
package com.wxhj.cloud.feignClient.device.request;

import javax.validation.constraints.*;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SubmitVerType.java
 * @author: cya
 * @Date: 2020年1月2日 下午5:18:29 
 */
@Data
@Api(value="新增/修改版本更新对象")
public class SubmitVerManageRequestDTO {
	@ApiModelProperty(value = "id", example = "主键")
	private String id;
	
	@ApiModelProperty(value = "版本编号", example = "11.12.12.13")
	@Pattern(regexp = "^([0-9]{2})+(\\.[0-9]{2}){3}$", message = "versionNumber error")
	private String versionNumber;
	@ApiModelProperty(value = "组织id")
	private String organizeId;
	
	@ApiModelProperty(value = "版本类型")
	@Min(-1)
	@Max(30)
	private int deviceType;
	@ApiModelProperty(value="文件名",example="xxx.apk")
	@Pattern(regexp = "^.*?.(apk|zip)$", message = "illegal suffix")
	private String fileName;
	@ApiModelProperty(value="发布状态",example="0")
	@Min(0)
	@Max(1)
	private int releaseState;
	@ApiModelProperty(value="版本更新描述",example="用于xxxx")
	private String updateDescribe;
	@ApiModelProperty(value="资源类型",example="0")
	@Min(0)
	@Max(10)
	private Integer resourceType;
	@ApiModelProperty(value="当前登录用户id")
	@NotNull
	private String userId;

	@ApiModelProperty(value = "上传文件大小(kb为单位)")
	@Min(0)
	private Integer fileSize;
}
