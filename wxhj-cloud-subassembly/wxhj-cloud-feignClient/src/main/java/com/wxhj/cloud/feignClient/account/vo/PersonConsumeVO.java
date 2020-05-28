package com.wxhj.cloud.feignClient.account.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.ICardNameOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDateTime;


@Data
@ApiModel(value = "个人消费信息查询返回对象")
public class PersonConsumeVO implements ICardNameOrganizeModel {
    @ApiModelProperty(value = "用户ID")
    private String accountId;
    @ApiModelProperty(value = "用户名称")
    private String accountName;
    @ApiModelProperty(value = "消费记录编号")
    private String consumeId;
    @ApiModelProperty(value = "流水号")
    private String orderNumber;
    @ApiModelProperty(value = "消费金额（单位元）")
    private Double consumeMoney;
    @ApiModelProperty(value = "消费笔数")
    private Integer consumeCount;
    @ApiModelProperty(value = "消费时间")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consumeDate;
    @ApiModelProperty(value = "是否撤销")
    private Integer isRevoke;
    @ApiModelProperty(value = "卡类型")
    private Integer cardType;
    @ApiModelProperty(value = "卡名称")
    private String cardName;
    @ApiModelProperty(value = "根组织id")
    private String organizeId;

    public void setConsumeMoney(Double consumeMoney) {
        this.consumeMoney = consumeMoney/100.00;
    }
}
