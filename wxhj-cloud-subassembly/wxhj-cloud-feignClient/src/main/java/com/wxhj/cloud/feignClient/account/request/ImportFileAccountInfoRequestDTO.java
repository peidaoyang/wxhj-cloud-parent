/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ImportFileAccountInfoRequestDTO.java
 * @author: cya
 * @Date: 2019年12月11日 上午10:54:22 
 */
@Data
@ApiModel(description = "通过文件名批量注册文件内的账户信息")
public class ImportFileAccountInfoRequestDTO {
	@ApiModelProperty(value = "文件", example = "123.xlsx")
	@NotBlank
	private String fileName;
	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value = "根组织的id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotBlank
	private String childOrganizeId;
	@ApiModelProperty(value = "账户类型", example = "0")
	@Min(value = 0)
	@Max(value = 9)
	private Integer accountType;
}
