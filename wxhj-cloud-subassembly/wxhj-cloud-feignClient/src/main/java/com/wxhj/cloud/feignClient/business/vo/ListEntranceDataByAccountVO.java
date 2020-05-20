package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.utils.DateFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;


@Data
@ApiModel(value = "根据账户id查询门禁明细报表 返回对象")
public class ListEntranceDataByAccountVO implements IOrganizeSceneModel {
    @ApiModelProperty(value = "出入的日期")
    private LocalDate accessDate;
    @ApiModelProperty(value = "出入的时间 (不能排序)")
    private String accessTime;


    @ApiModelProperty(value = "账户Id")
    private String accountId;
    private String sceneId;
    @ApiModelProperty(value = "场景名称(不能排序)")
    private String sceneName;
    @ApiModelProperty(value = "体温")
    private Double temperature;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "规则开始时间")
    private LocalDate startDate;
    @JsonFormat(pattern = "yyyy-MM-dd")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "规则结束时间")
    private LocalDate endDate;
    @ApiModelProperty(value = "设备名称")
    private String deviceName;
    @ApiModelProperty(value = "通行规则名称")
    private String entranceName;
    @ApiModelProperty(value = "组织ID")
    private String organizeId;
    @ApiModelProperty(value = "所属组织(不能排序)")
    private String organizeName;

    public void setAccessTime(String accessTime) {
        this.accessTime = DateFormat.getStringDate(
                this.accessDate, "yyyy-MM-dd")
                + " " +
                DateFormat.minute2HourMinute(Integer.parseInt(accessTime));
    }
}
