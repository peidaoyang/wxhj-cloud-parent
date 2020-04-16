package com.wxhj.cloud.device.dto.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel(value = "微信二维码在线支付请求模型")

public class WechatQrOnlineRequestDTO {

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录日期时间")
    private Date recordDatetime;
    @ApiModelProperty(value = "设备号_设备流水号_时间戳")
    private String orderNumber;

    public  String getOutTradeNo(){
        return  this.orderNumber;
    }


    @ApiModelProperty(value = "设备流水号")
    private Long serialNumber;

    //@ApiModelProperty(value = "用户id")
    //private String accountId;
    //场景id
    @ApiModelProperty(value = "场景id")
    private String sceneId;
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    //
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    //
    //
    //private String appid;
    //商户号
    //private String mchId;
    //随机字符串
    @ApiModelProperty(value = "随机字符串")
    private String nonceStr;
    @ApiModelProperty(value = "商品描述")
    private String body;
    //商品详情
    @ApiModelProperty(value = "商品详情")
    private String detail;
    //订单金额
    @ApiModelProperty(value = "订单金额")
    private Integer totalFee;
    //付款码
    @ApiModelProperty(value = "付款码")
    private String authCode;
}
