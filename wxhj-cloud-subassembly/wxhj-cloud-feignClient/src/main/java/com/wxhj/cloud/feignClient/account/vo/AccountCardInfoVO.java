package com.wxhj.cloud.feignClient.account.vo;

import com.wxhj.cloud.feignClient.bo.ICardNameOrganizeModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Optional;

/**
 * @author daxiong
 * @date 2020/5/26 4:59 下午
 */
@Data
public class AccountCardInfoVO implements ICardNameOrganizeModel {
    @ApiModelProperty("主键id")
    private String id;
    @ApiModelProperty("根组织id")
    private String organizeId;
    @ApiModelProperty("根组织id")
    private String organizeName;
    @ApiModelProperty("卡号")
    private String cardNumber;
    @ApiModelProperty("卡名称")
    private String cardName;
    @ApiModelProperty("卡类型")
    private Integer cardType;
    @ApiModelProperty("账户余额")
    private Double balance;

    public void setBalance(Double balance) {
        this.balance = Optional.ofNullable(balance).orElse(0D) / 100D;
    }
}
