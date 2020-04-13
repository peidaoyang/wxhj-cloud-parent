/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: ListAccountPageByOrgRequestDTO.java
 * @author: cya
 * @Date: 2020年3月20日 下午1:30:38 
 */
@Data
@ToString
@ApiModel( description = "账户及子账户查询请求对象")
public class ListAccountPageByOrgRequestDTO extends CommonPageRequestDTO {
	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotEmpty
	private List<String> organizeIdList;

	@ApiModelProperty(value = "请求类型",example = "手机号：phoneNumber，姓名：name，其他编码：otherCode")
	private String type;
}
