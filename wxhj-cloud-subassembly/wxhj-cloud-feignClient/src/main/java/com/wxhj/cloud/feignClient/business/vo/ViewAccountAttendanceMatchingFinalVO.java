package com.wxhj.cloud.feignClient.business.vo;

import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author daxiong
 * @date 2020/4/21 2:09 下午
 */
@Data
@ApiModel(value = "考勤人员汇总的记录VO")
public class ViewAccountAttendanceMatchingFinalVO implements IOrganizeModel {

    @ApiModelProperty(value = "权限组id(不显示)")
    private String authorityGroupId;
    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "权限组类型(不显示)")
    private Integer attendanceType;
    @ApiModelProperty(value = "姓名")
    private String accountName;
    @ApiModelProperty(value = "考勤组名称")
    private String authorityGroupName;
    @ApiModelProperty(value = "组织id(不显示)")
    private String organizeId;

    @ApiModelProperty(value = "组织名称")
    private String organizeName;
    @ApiModelProperty(value = "子组织的id")
    private String childOrganizeId;

    @ApiModelProperty(value = "请假总时长")
    private Integer leaveTotal;
    private String leaveTotalStr;
    @ApiModelProperty(value = "出差总时长")
    private Integer travelTotal;
    private String travelTotalStr;
    @ApiModelProperty(value = "工作总时长")
    private Integer workTotal;
    private String workTotalStr;
    @ApiModelProperty(value = "迟到总时长")
    private Integer lateTotal;
    @ApiModelProperty(value = "早退总时长")
    private Integer earlyTotal;
    @ApiModelProperty(value = "缺勤天数")
    private Integer absentDays;
    @ApiModelProperty(value = "缺卡天数")
    private Integer missCardDays;
    @ApiModelProperty(value = "迟到早退天数")
    private Integer lateEarlyDays;
    @ApiModelProperty(value = "早退天数")
    private Integer earlyDays;
    @ApiModelProperty(value = "迟到天数")
    private Integer lateDays;
    @ApiModelProperty(value = "休息天数")
    private Integer restDays;
    @ApiModelProperty(value = "工作总天数")
    private Integer workDays;

}
