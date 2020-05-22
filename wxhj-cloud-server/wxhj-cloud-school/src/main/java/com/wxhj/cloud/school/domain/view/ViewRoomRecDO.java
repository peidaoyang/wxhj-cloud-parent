package com.wxhj.cloud.school.domain.view;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@Table(name = "view_room_rec")
public class ViewRoomRecDO {
    @Id
    @ApiModelProperty("床位信息主键")
    private String id;
    @ApiModelProperty("宿舍信息主键")
    private String roomId;
    @ApiModelProperty("床位号")
    private Integer number;
    @ApiModelProperty("学号")
    private String otherCode;
    @ApiModelProperty("账户名称")
    private String accountName;
    @ApiModelProperty("账户号")
    private String accountId;
    @ApiModelProperty("宿舍类型")
    private Integer roomType;
    @ApiModelProperty("房间号")
    private Integer roomNumber;
    @ApiModelProperty("楼栋号")
    private Integer dormitoryNumber;
    @ApiModelProperty("楼栋编号")
    private String dormitoryId;
    @ApiModelProperty("组织编号")
    private String organizeId;
}
