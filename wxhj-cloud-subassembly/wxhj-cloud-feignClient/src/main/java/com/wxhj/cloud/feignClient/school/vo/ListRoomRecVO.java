package com.wxhj.cloud.feignClient.school.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cya
 * @Date 2020/5/25
 * @Version V1.0
 **/
@Data
@ApiModel(value = "入住信息分页查询 返回对象")
public class ListRoomRecVO implements IOrganizeModel {
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
    @ApiModelProperty("组织名称")
    private String organizeName;
}
