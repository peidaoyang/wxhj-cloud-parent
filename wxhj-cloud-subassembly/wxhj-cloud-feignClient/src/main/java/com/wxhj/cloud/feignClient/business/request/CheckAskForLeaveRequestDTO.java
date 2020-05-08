package com.wxhj.cloud.feignClient.business.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel(value = "审核请假 请求对象")
public class CheckAskForLeaveRequestDTO {
    @ApiModelProperty(value = "主键")
    @NotNull
    private String id;
    @ApiModelProperty(value = "审核状态",example = "1:未审核，2：审核成功， 3：审核失败")
    @Min(1)
    private Integer type;
}
