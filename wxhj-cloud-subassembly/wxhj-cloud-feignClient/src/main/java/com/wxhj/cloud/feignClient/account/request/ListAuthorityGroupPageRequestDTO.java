package com.wxhj.cloud.feignClient.account.request;

import javax.validation.constraints.Min;

import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
public class ListAuthorityGroupPageRequestDTO extends CommonListPageRequestDTO{
	@ApiModelProperty(value="权限组入口类型，0：默认值，1：考勤权限组，2：门禁入口，3：消费入口，4：班车入口")
	@Min(0)
	private Integer type;
}
