/**
 * 
 */
package com.wxhj.cloud.feignClient.account.request;

import java.util.List;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

import com.wxhj.cloud.core.model.pagination.IPageRequestModel;

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
public class ListAccountPageByOrgRequestDTO implements IPageRequestModel{
	@ApiModelProperty(value = "账户姓名", example = "")
	private String nameValue;
	@ApiModelProperty(value = "组织id", example = "dfaea5be-8273-4bdd-bd6f-4f66eaadd509")
	@NotEmpty
	private List<String> organizeIdList;
	
	@ApiModelProperty(value = "单页行数", example = "50")
	@Min(1)
	private Integer rows;
	@ApiModelProperty(value = "当前页数", example = "1")
	@Min(1)
	private Integer page;
	@ApiModelProperty(value = "排序字段", example = "account_id")
	@NotBlank(message = "不能为空")
	private String orderBy;
}
