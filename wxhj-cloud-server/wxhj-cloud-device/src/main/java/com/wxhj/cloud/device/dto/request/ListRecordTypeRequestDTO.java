/** 
 * @fileName: ListRecordTypeRequestDTO.java  
 * @author: pjf
 * @date: 2020年2月11日 下午3:34:11 
 */

package com.wxhj.cloud.device.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @className ListRecordTypeRequestDTO.java
 * @author pjf
 * @date 2020年2月11日 下午3:34:11   
*/
/**
 * @className ListRecordTypeRequestDTO.java
 * @author pjf
 * @date 2020年2月11日 下午3:34:11
 */

@Data
@ApiModel(value = "第三方拉取记录请求对象")
public class ListRecordTypeRequestDTO implements IPageRequestModel {
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "id")
	@NotBlank(message = "不能为空")
	private String orderBy;
	@ApiModelProperty(value = "组织id", example = "")
	@NotBlank(message = "不能为空")
	private String organizeId;

}
