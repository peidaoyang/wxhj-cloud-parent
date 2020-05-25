package com.wxhj.cloud.feignClient.account.vo;



import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

import java.time.LocalDate;

/**
 * @ClassName: AppRechargeInfoResponseDTO.java
 * @author: cya
 * @Date: 2020年2月2日 下午2:49:39
 */
@ApiModel("app充值信息返回对象")
@Data
public class AppRechargeInfoVO implements IOrganizeModel {
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录创建时间")
    private LocalDate creatorTime;
    @ApiModelProperty(value = "充值金额（单位元）")
    private Double amount;
    @ApiModelProperty(value = "账户余额（单位元）")
    private Double accountBalance;
    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "充值编号")
    private String id;
    @ApiModelProperty(value = "组织Id")
    private String organizeId;
    @ApiModelProperty(value = "组织名称（不能排序）")
    private String organizeName;
    @ApiModelProperty(value = "充值方类型 0为默认（后台），1为app，2为小程序，3为其他第三方")
    private Integer type;
    @ApiModelProperty(value = "充值类型 0为默认（后台），1为支付宝，2为微信，3为其他第三方")
    private Integer payType;


    public void setAmount(Double amount) {
        this.amount = amount/100.00;
    }

    public void setAccountBalance(Double accountBalance) {
        this.accountBalance = accountBalance/100.00;
    }


}
