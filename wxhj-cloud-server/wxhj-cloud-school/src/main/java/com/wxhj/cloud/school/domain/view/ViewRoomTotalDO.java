package com.wxhj.cloud.school.domain.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/20
 * @Version V1.0
 **/
@Table(name = "view_room_total")
@Data
public class ViewRoomTotalDO {
    @Id
    @ApiModelProperty(value = "房间主键")
    private String id;
    @ApiModelProperty(value = "房间号")
    private Integer number;
    @ApiModelProperty(value = "已入住床位数")
    private Integer resideNumber;
    @ApiModelProperty(value = "空床位数")
    private Integer emptyBedNumber;
    @ApiModelProperty(value = "宿舍床位数")
    private Integer bedNumber;
    @ApiModelProperty(value = "楼层数")
    private Integer floorNumber;
    @ApiModelProperty(value = "楼栋号")
    private Integer dormitoryNumber;
    @ApiModelProperty(value = "楼栋编号")
    private String dormitoryId;
    @ApiModelProperty(value = "宿舍类型,0:男生宿舍,1:女生宿舍")
    private Integer type;
    @ApiModelProperty(value = "组织编号")
    private String organizeId;
}
