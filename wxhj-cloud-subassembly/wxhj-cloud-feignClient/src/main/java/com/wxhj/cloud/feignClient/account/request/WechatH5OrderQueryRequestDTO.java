package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("微信H5订单状态查询")
public class WechatH5OrderQueryRequestDTO {
    @ApiModelProperty("订单提交返回的appid")
    @NotBlank
    private String appid;
    @ApiModelProperty("订单提交返回的mchId")
    @NotBlank
    private String mchId;
    @ApiModelProperty("订单提交的outTradeNo")
    @NotBlank
    private String outTradeNo;
    @ApiModelProperty("订单提交的nonceStr")
    @NotBlank
    private String nonceStr;
}
