package com.wxhj.cloud.feignClient.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
public class PersonRechargeVO {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录创建时间")
    private LocalDateTime creatorTime;
    @ApiModelProperty(value = "充值金额（单位元）")
    private Double amount;
    @ApiModelProperty(value = "账户余额（单位元）")
    private Double accountBalance;
    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "充值编号")
    private String id;
    @ApiModelProperty(value = "是否撤销")
    private Integer isRevoke;
    @ApiModelProperty(value = "卡类型")
    private Integer cardType;

    public void setCardType(Integer cardType) {
        if (cardType == null) {
            cardType = 0;
        }
        this.cardType = cardType;
    }

    public void setAmount(Double amount) {
        this.amount = amount/100.00;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance/100.00;
    }
}
