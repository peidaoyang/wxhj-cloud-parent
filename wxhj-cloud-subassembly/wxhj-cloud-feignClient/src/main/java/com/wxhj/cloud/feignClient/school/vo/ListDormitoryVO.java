package com.wxhj.cloud.feignClient.school.vo;

import com.wxhj.cloud.feignClient.bo.IAuthoritySynchroModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @Author cya
 * @Date 2020/5/19
 * @Version V1.0
 **/
@Data
@ApiModel(value = "楼栋分页查询 返回对象")
public class ListDormitoryVO implements IOrganizeModel, IAuthoritySynchroModel {
    private String id;
    @ApiModelProperty("组织id")
    private String organizeId;
    @ApiModelProperty("组织名称(不能排序)")
    private String organizeName;

    @ApiModelProperty("楼栋编号")
    private Integer number;
    @ApiModelProperty("楼层数")
    private Integer floorNumber;
    @ApiModelProperty("房间数量")
    private Integer roomSize;
    @ApiModelProperty("床位数量")
    private Integer bedTotal;
    @ApiModelProperty("入住人数")
    private Integer resideNumber;
    @ApiModelProperty("空床位数量")
    private Integer emptyBedNumber;
    @ApiModelProperty("自动同步")
    private Integer autoSynchro;
}
