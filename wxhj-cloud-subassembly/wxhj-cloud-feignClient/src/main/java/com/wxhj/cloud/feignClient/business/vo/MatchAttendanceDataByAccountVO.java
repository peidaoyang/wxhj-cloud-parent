package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.utils.DateFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.time.LocalDateTime;


@NoArgsConstructor
@AllArgsConstructor
@ApiModel(value = "根据账户id获取打卡记录 返回对象")
@Data
public class MatchAttendanceDataByAccountVO implements IOrganizeSceneModel {
//    @DateTimeFormat(pattern = "yyyy-MM-dd")

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @ApiModelProperty(value = "记录产生时间")
    private LocalDateTime recordDatetime;

    @ApiModelProperty(value = "账户id")
    private String accountId;
    @ApiModelProperty(value = "场景id")
    private String sceneId;
    @ApiModelProperty(value = "场景名称(不能排序)")
    private String sceneName;
    @ApiModelProperty(value = "组织id")
    private String organizeId;
    @ApiModelProperty(value = "组织名称(不能排序)")
    private String organizeName;

    @ApiModelProperty(value = "上班时间(不能排序)")
    private String upTime;
    @ApiModelProperty(value = "下班时间(不能排序)")
    private String downTime;

    @ApiModelProperty(value = "上班标志：0代表上班，1代表下班")
    private Integer upDownMark;

    @ApiModelProperty(value = "排版时间 (不能排序)")
    private String matchingTime;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @ApiModelProperty(value = "排班日期")
    private LocalDate matchingDate;

    public void setUpTime(String upTime) {
        this.upTime = DateFormat.minute2HourMinute(Integer.parseInt(upTime));
    }

    public void setDownTime(String downTime) {
        this.downTime = DateFormat.minute2HourMinute(Integer.parseInt(downTime));
    }

    public void setMatchingTime(String matchingTime) {
        this.matchingTime = DateFormat.minute2HourMinute(Integer.parseInt(matchingTime));
    }
}
