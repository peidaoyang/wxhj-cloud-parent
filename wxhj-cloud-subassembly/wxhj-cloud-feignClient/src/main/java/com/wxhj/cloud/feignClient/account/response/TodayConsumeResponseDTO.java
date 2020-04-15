package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Data
@ApiModel(description = "消费统计返回对象")
public class TodayConsumeResponseDTO {
    @ApiModelProperty(value = "今日消费总额")
    private Double todayConsumeMoney;
    @ApiModelProperty(value = "今日消费总笔数")
    private Integer todayConsumeCount;

    @ApiModelProperty(value = "今日充值总数")
    private Double todayRechargeMoney;

    @ApiModelProperty(value = "今日充值总笔数")
    private Integer todayRechargeCount;
}
