package com.wxhj.cloud.school.domain.view;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

@Data
@ApiModel("楼栋信息统计")
@Table(name = "view_dormitory_total")
public class ViewDormitoryTotalDO {
    @ApiModelProperty("楼栋主键")
    private String id;
    @ApiModelProperty("楼栋编号")
    private Integer number;
    @ApiModelProperty("楼层数")
    private Integer floorNumber;
    @ApiModelProperty("组织编号")
    private String organizeId;
    @ApiModelProperty("房间数量")
    private Integer roomSize;
    @ApiModelProperty("床位数量")
    private Integer bedTotal;
    @ApiModelProperty("入住人数")
    private Integer resideNumber;
    @ApiModelProperty("空床位数量")
    private Integer emptyBedNumber;
}
