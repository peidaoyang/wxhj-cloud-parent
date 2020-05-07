package com.wxhj.cloud.feignClient.account.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("人脸修改 返回对象")
@Data
public class FaceChangeVO {
    @ApiModelProperty(value = "场景id")
    private String id;
    @ApiModelProperty(value = "最小流水号")
    private Long minIndex;
    @ApiModelProperty(value = "最大流水号")
    private Long maxIndex;

    @ApiModelProperty(value = "需要下发的人数")
    private Integer needDownPeople;
}
