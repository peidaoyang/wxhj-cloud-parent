package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@ApiModel(value = "消费撤销请求对象")
@Data
public class AccountRevokeRequestDTO {
    @ApiModelProperty(value = "消费编号")
    @NotBlank
    private String consumeId;
    @ApiModelProperty(value = "账户id")
    @NotBlank
    private String accountId;
    @ApiModelProperty(value = "消费金额（单位分）")
    @Min(0)
    private Integer consumeMoney;
    @ApiModelProperty(value = "撤销人员id")
    @NotNull
    private String creatorUserId;
    @ApiModelProperty(value = "卡类型")
    private Integer cardType;
}
