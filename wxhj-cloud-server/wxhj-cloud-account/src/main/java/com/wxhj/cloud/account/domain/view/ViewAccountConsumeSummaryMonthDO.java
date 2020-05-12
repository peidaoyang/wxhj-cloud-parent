package com.wxhj.cloud.account.domain.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/11
 * @Version V1.0
 **/
@Data
@Table(name = "view_account_consume_summary_month")
public class ViewAccountConsumeSummaryMonthDO {
    @ApiModelProperty(value = "充值统计月份")
    @Id
    private Integer consumeMonth;
    @ApiModelProperty(value = "统计数量")
    private Integer count;
    @ApiModelProperty(value = "统计金额（单位分）")
    private Integer totalAmount;
    @ApiModelProperty(value = "账户Id")
    private String accountId;
}
