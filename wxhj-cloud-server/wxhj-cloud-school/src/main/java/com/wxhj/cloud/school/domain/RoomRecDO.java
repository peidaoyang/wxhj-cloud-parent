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
@Table(name = "room_rec")
@Data
public class RoomRecDO {
    @ApiModelProperty(value = "主键")
    @Id
    private String id;
    @ApiModelProperty(value = "房间主键")
    private String roomId;
    @ApiModelProperty(value = "房间主键")
    private String dormitoryId;
    @ApiModelProperty(value = "床位号")
    private Integer number;
    @ApiModelProperty(value = "学号")
    private String otherCode;
    @ApiModelProperty(value = "用户名称")
    private String accountName;
    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "入住标志(0:未入账,1：已入住)")
    private Integer status;
    @ApiModelProperty(value = "组织编号")
    private String organizeId;


    public RoomRecDO(String roomId, String dormitoryId,Integer number,Integer status) {
        this.roomId = roomId;
        this.dormitoryId = dormitoryId;
        this.number = number;
        this.status = status;
    }
}
