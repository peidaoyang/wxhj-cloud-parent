package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class AccountOneVO {
	@ApiModelProperty(value="账户id")
	private String accountId;
	@ApiModelProperty(value="手机号")
	private String phoneNumber;
	@ApiModelProperty(value="姓名")
	private String name;
	@ApiModelProperty(value="身份证")
	private String idNumber;
	@ApiModelProperty(value="性别")
	private Integer sex;
	@ApiModelProperty(value="账户类型")
	private Integer accountType;
	@ApiModelProperty(value="是否实名制")
	private Integer isReal;
	@ApiModelProperty(value="是否人脸注册")
	private Integer isFace;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	

}