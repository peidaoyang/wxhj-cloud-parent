package com.wxhj.cloud.feignClient.vo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.Date;
import java.util.List;

/**
 * @author daxiong
 * @date 2020-04-08 17:42
 */
@Data
@ApiModel(value = "获取考勤规则记录VO")
public class GetAttendanceDaysVO {

    @ApiModelProperty(value = "日期")
    private Date dayInfo;
    @ApiModelProperty(value = "考勤规则类型", example = "0：正常班；1：休息；2：请假；3：出差")
    private Integer type;
    @ApiModelProperty(value = "考勤规则类型中文描述")
    private String typeName;

    List<AskForLeaveVO> askForLeaveList;
    List<OnBusinessVO> onBusinessList;
}
