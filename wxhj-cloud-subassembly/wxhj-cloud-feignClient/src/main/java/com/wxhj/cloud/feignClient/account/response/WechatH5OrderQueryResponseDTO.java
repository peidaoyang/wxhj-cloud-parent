package com.wxhj.cloud.feignClient.account.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("微信H5订单状态查询返回对象")
public class WechatH5OrderQueryResponseDTO {
    @ApiModelProperty("订单提交返回的appid")

    private String appid;
    @ApiModelProperty("订单提交返回的mchId")

    private String mchId;
    @ApiModelProperty("订单提交的outTradeNo")

    private String outTradeNo;
    @ApiModelProperty("订单提交的nonceStr")

    private String nonceStr;

    private boolean isSuccess;

    private String errMessage;
}
