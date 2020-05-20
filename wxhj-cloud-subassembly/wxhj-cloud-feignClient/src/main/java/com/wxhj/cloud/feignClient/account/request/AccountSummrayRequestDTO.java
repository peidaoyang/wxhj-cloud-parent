package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
@Data
@ApiModel(value = "交易汇总查询")
public class AccountSummrayRequestDTO {
    @ApiModelProperty(value = "账户id",example = "000097")
    @NotNull
    private String accountId;
    @ApiModelProperty(value = "年月(格式:yyyyMM)",example = "202005")
    @NotNull
    private Integer month;
}
