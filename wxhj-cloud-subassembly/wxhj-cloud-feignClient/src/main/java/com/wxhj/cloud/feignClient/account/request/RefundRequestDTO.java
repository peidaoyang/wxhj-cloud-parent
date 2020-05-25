package com.wxhj.cloud.feignClient.account.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;



@Data
@ToString
@ApiModel(description = "退款请求对象")
public class RefundRequestDTO {
    @ApiModelProperty(value = "退款订单号",example = "123123123")
    private String refundId;
    @ApiModelProperty(value = "账户id",example = "000027")
    private String accountId;
    @ApiModelProperty(value = "退款金额",example = "20")
    private Integer amount;
    @ApiModelProperty(value = "退款（0：后台，1：微信，2：支付宝，3：其他）")
    private Integer type;
    @ApiModelProperty(value = "操作人员id")
    private String creatorUserId;
    @ApiModelProperty(value = "卡类型")
    private Integer cardType;
}
