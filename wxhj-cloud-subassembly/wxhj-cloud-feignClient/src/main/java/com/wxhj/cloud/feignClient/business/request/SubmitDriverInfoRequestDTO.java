package com.wxhj.cloud.feignClient.business.request;

import javax.validation.constraints.NotBlank;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: SubmitDriverInfoRequestDTO.java
 * @author: cya
 * @Date: 2020年2月4日 下午6:18:32 
 */
@Data
@ApiModel(value="新增/修改 请求对象")
public class SubmitDriverInfoRequestDTO {
	@ApiModelProperty(value="主键id")
	private String id;
	@ApiModelProperty(value = "工号",example="000003")
	@NotBlank
	private String jobNumber;
	@ApiModelProperty(value = "驾驶证编号",example="000003")
	private String driverNumber;
	@ApiModelProperty(value = "账户id",example="0000000028")
	@NotBlank
	private String accountId;
	@ApiModelProperty(value = "司机姓名",example="测试司机")
	@NotBlank
	private String name;
	@ApiModelProperty(value = "身份证编号",example="110101199003079274")
	@NotBlank
	private String idNumber;
	@ApiModelProperty(value = "组织编号",example="f8b89131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String organizeId;
	@ApiModelProperty(value = "创建人员id",example="11189131-de13-4dc2-b5bb-b117e12c23bc")
	@NotBlank
	private String userId;

}
