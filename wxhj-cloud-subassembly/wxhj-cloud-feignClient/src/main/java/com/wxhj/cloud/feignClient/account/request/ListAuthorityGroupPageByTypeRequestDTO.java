/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Min;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: ListAuthorityGroupPageByTypeRequestDTO.java
 * @author: cya
 * @Date: 2020年3月13日 上午9:39:02
 */
@Data
public class ListAuthorityGroupPageByTypeRequestDTO extends CommonListPageRequestDTO {
//	@ApiModelProperty(value = "权限组名", example = "权限1")
//	private String nameValue;
//	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
//	@NotBlank(message = "不能为空")
//	private String organizeId;
//	@ApiModelProperty(value = "单页行数", example = "50")
//	@Min(1)
//	private Integer rows;
//	@ApiModelProperty(value = "当前页数", example = "1")
//	@Min(1)
//	private Integer page;
//	@ApiModelProperty(value = "排序字段", example = "organize_id")
//	@NotBlank(message = "不能为空")
//	private String orderBy;
	@ApiModelProperty(value = "类型", example = "3")
	@Min(0)
	private Integer Type;
}
