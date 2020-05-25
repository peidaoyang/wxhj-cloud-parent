/**
 *
 */
package com.wxhj.cloud.feignClient.account.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @ClassName: ListConsumeSummaryExcel.java
 * @author: cya
 * @Date: 2020年3月16日 下午1:50:48 
 */
@ApiModel(value = "消费汇总报表导出 请求对象")
@Data
public class ListConsumeSummaryExcel {
    @ApiModelProperty(value = "条件参数", example = "")
    private String nameValue;

    @ApiModelProperty(value = "开始时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginTime;
    @ApiModelProperty(value = "结束时间", example = "2020-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;

    @ApiModelProperty(value = "组织编号", example = "f8b89131-de13-4dc2-b5bb-b117e12c23bc")
    @NotBlank
    private String organizeId;
    @ApiModelProperty(value = "导出的语音标准", example = "zh_CN")
    @NotBlank
    private String language;
}
