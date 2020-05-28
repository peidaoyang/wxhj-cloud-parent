package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/5/21 5:14 下午
 */
@Data
@ApiModel("组织卡优先级VO")
public class OrganizeCardPriorityVO {
    @ApiModelProperty("组织id")
    private String organizeId;
    @ApiModelProperty("卡类型")
    private Integer cardType;
    @ApiModelProperty("优先级，值越小，优先级越高")
    private Integer priority;
    @ApiModelProperty("卡名称")
    private String cardName;
}
