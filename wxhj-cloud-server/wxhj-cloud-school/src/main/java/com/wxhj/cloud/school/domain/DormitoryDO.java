package com.wxhj.cloud.school.domain;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Author cya
 * @Date 2020/5/15
 * @Version V1.0
 **/
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "dormitory")
@Data
public class DormitoryDO {
    @Id
    private String id;
    @ApiModelProperty("组织id")
    private String organizeId;
    @ApiModelProperty("楼栋号")
    private Integer number;
    @ApiModelProperty("楼层数")
    private Integer floorNumber;
    @ApiModelProperty("备注")
    private String note;
//    @ApiModelProperty("房间数")
//    private Integer room;
//    @ApiModelProperty("床位数量")
//    private Integer bedNumber;
//    private Integer resideNumber;
//    private Integer emptyBedNumber;
}
