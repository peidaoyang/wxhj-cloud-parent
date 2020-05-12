package com.wxhj.cloud.feignClient.business.request;

import com.wxhj.cloud.feignClient.dto.CommonPageRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Data
@ApiModel("根据账户id查询请假信息")
public class ListAskForLeaveByAccountIdRequestDTO extends CommonPageRequestDTO {
    @ApiModelProperty(value = "账户id",example = "000057")
    @NotNull
    private String accountId;

    @ApiModelProperty(value = "审核状态 1：未审核，2：审核成功，3：审核失败",example = "0")
    @Min(0)
    private Integer status;
}
