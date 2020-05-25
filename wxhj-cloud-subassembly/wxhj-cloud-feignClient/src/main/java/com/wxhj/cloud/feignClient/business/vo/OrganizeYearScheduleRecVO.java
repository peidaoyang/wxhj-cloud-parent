package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;
import java.util.Date;

/**
 * @author daxiong
 * @date 2020/5/13 8:45 上午
 */
@ApiModel(value = "组织年度日期管理规则VO")
@Data
public class OrganizeYearScheduleRecVO {
    @ApiModelProperty("关联的组织年计划管理id")
    private String organizeYearScheduleId;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty("日期")
    private LocalDate day;
    @ApiModelProperty("状态：0:正常工作日；1：正常休息日；2：国家法定调休日；" +
            "3：国家法定节假日；4：自定义工作日1；5自定义工作日2；" +
            "6：自定义工作日3；7：自定义工作日4")
    private Integer status;
    @ApiModelProperty("名称")
    private String name;
    @ApiModelProperty("备注")
    private String memo;
}
