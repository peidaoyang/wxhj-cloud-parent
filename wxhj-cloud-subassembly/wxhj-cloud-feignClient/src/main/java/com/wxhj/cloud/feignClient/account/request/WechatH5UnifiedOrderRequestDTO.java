package com.wxhj.cloud.feignClient.account.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotBlank;

@Data
@ApiModel("微信H5下单接口参考(https://pay.weixin.qq.com/wiki/doc/api/H5.php?chapter=9_20&index=1)")
public class WechatH5UnifiedOrderRequestDTO {
    //    @NotBlank
//    //公众账号ID
//    private String appid;
//    @NotBlank
//    //商户号
//    private String mchId;
    @ApiModelProperty("商户id")
    @NotBlank
    private String organizeId;
    @ApiModelProperty("随机数")
    @NotBlank
    private String nonceStr;
    @ApiModelProperty(name = "商品描述")
    @NotBlank
    private String body;
    @ApiModelProperty(name = "附加数据")
    private String attach;
    @ApiModelProperty(name = "商户订单号")
    @NotBlank
    private String outTradeNo;
    @ApiModelProperty(name = "货币类型", example = "CNY")
    private String feeType;
    @ApiModelProperty(name = "总金额(分)")
    private Integer totalFee;
    @ApiModelProperty(name = "终端IP(必须传正确的用户端IP,支持ipv4)")
    private String spbillCreateIp;
    @ApiModelProperty(name = "交易类型固定值", example = "MWEB")
    @NotBlank
    private String tradeType;
    @ApiModelProperty(name = "商品ID")
    private String productId;
    @ApiModelProperty(name = "用户标识")
    @NotBlank
    private String openid;
    @ApiModelProperty(name = "场景信息", example = "{\"h5_info\": //h5支付固定传\"h5_info\" \n" +
            "    {\"type\": \"\",  //场景类型\n" +
            "     \"app_name\": \"\",  //应用名\n" +
            "     \"package_name\": \"\"  //包名\n" +
            "     }\n" +
            "}")
    @NotBlank
    private String sceneInfo;
}
