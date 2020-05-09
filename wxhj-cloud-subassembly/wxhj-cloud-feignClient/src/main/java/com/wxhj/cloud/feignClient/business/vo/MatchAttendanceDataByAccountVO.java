package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@ApiModel(value = "根据账户id获取打卡记录 返回对象")
@Data
public class MatchAttendanceDataByAccountVO implements IOrganizeSceneModel {
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @ApiModelProperty(value = "排班日期")
    private Date matchingDate;
    @ApiModelProperty(value = "排版日期+时间 (不能排序)")
    private String matchingTime;

    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @ApiModelProperty(value = "记录产生时间")
    private Date recordDatetime;

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

    public void setMatchingTime(String matchingTime) {
        this.matchingTime = DateUtil.getStringDate(this.matchingDate,"yyyy-MM-dd")+" "+DateUtil.minute2HourMinute(Integer.parseInt(matchingTime));
    }

    public void setUpTime(String upTime) {
        this.upTime = DateUtil.minute2HourMinute(Integer.parseInt(upTime));
    }

    public void setDownTime(String downTime) {
        this.downTime = DateUtil.minute2HourMinute(Integer.parseInt(downTime));
    }
}
