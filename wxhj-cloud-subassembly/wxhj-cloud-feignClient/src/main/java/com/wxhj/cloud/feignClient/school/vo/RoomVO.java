package com.wxhj.cloud.feignClient.school.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@ApiModel(value = "房间信息查询返回对象")
public class RoomVO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "房间号")
    private Integer number;
    @ApiModelProperty(value = "床位数")
    private Integer bedNumber;
    @ApiModelProperty(value = "房间号")
    private Integer floor;
    @ApiModelProperty(value = "宿舍类型")
    private Integer type;
    @ApiModelProperty(value = "房间号")
    private Integer dormitoryId;
    @ApiModelProperty(value = "入住人数")
    private Integer resideNumber;
    @ApiModelProperty(value = "空床位数")
    private Integer emptyBedNumber;
    @ApiModelProperty(value = "房间号")
    private String organizeId;
}
