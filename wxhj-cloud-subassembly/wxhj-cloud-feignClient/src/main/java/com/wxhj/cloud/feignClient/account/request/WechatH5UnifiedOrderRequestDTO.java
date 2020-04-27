package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Data
@ToString
@ApiModel(description = "微信H5下单接口参考(https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_20&index=1)")
public class WechatH5UnifiedOrderRequestDTO {
    @ApiModelProperty(value="商户id")
    @NotBlank
    private String organizeId;
    @ApiModelProperty("随机数")
    @NotBlank
    private String nonceStr;
    @ApiModelProperty(value = "商品描述")
    @NotBlank
    private String body;
    @ApiModelProperty(value = "附加数据")
    private String attach;
    @ApiModelProperty(value = "商户订单号")
    @NotBlank
    private String outTradeNo;
    @ApiModelProperty(value = "货币类型", example = "CNY")
    private String feeType;
    @ApiModelProperty(value = "总金额(分)")
    private Integer totalFee;
    @ApiModelProperty(value = "终端IP(必须传正确的用户端IP,支持ipv4)")
    private String spbillCreateIp;
    @ApiModelProperty(value = "交易类型固定值", example = "MWEB")
    @NotBlank
    private String tradeType;
    @ApiModelProperty(value = "商品ID")
    private String productId;
    @ApiModelProperty(value = "用户标识")
    @NotBlank
    private String openid;
//    @ApiModelProperty(value = "场景信息", example = "{\"h5_info\": //h5支付固定传\"h5_info\" \n" +
//            "    {\"type\": \"\",  //场景类型\n" +
//            "     \"app_value\": \"\",  //应用名\n" +
//            "     \"package_value\": \"\"  //包名\n" +
//            "     }\n" +
//            "}")
    @ApiModelProperty(value = "场景信息",example = "h5_info:h5支付固定传h5_info;type:场景类型;app_value:应用名;package_value:包名")
    @NotBlank
    private String sceneInfo;
}
