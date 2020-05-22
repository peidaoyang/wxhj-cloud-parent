package com.wxhj.cloud.school.domain;

import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/18
 * @Version V1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "room")
@Data
public class RoomDO {
    @ApiModelProperty(value = "主键")
    @Id
    private String id;
    @ApiModelProperty(value = "房间号")
    private Integer number;
    @ApiModelProperty(value = "床位数")
    private Integer bedNumber;
    @ApiModelProperty(value = "楼层")
    private Integer floorNumber;
    @ApiModelProperty(value = "宿舍类型(0：男生宿舍,1：女生宿舍,2：教职工宿舍)")
    private Integer type;
    @ApiModelProperty(value = "楼栋编号")
    private String dormitoryId;
    @ApiModelProperty(value = "组织编号")
    private String organizeId;
}
