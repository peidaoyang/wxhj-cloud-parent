package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Data
@ApiModel(value = "交易汇总返回对象")
public class AccountSummrayReponseDTO {
    @ApiModelProperty(value = "消费总数")
    private Integer consumeTotal;
    @ApiModelProperty(value = "消费金额")
    private Double consumeMoney;
    @ApiModelProperty(value = "充值笔数")
    private Integer rechargeTotal;
    @ApiModelProperty(value = "充值总额")
    private Double rechargeMoney;
    @ApiModelProperty(value = "账户余额")
    private Double accountBalance;
    @ApiModelProperty(value = "账户id")
    private String accountId;


}
