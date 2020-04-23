package com.wxhj.cloud.account.bo;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author wxpjf
 * @date 2020/4/23 13:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceBO {
    @ApiModelProperty(value = "账户")
    private String accountId;
    @ApiModelProperty(value = "账户余额")
    private Double balance;
}
