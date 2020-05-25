package com.wxhj.cloud.feignClient.business.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author daxiong
 * @date 2020/5/13 8:45 上午
 */
@ApiModel(value = "组织年度日期管理VO")
@Data
public class OrganizeYearScheduleVO implements IOrganizeModel {
    @ApiModelProperty("主键")
    private String id;
    @ApiModelProperty("年作息名称")
    private String fullName;
    @ApiModelProperty("组织id")
    private String organizeId;
    @ApiModelProperty("组织名称")
    private String organizeName;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("是否应用法定节假日，1：应用了；2：没应用")
    private Integer useLegalVocation;
    @ApiModelProperty("备注")
    private String memo;
    @ApiModelProperty("创建时间")
    private LocalDate createTime;
}
