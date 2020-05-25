/**
 * @fileName: DayAttendanceDataExcelRequestDTO.java
 * @author: pjf
 * @date: 2020年2月9日 下午12:04:02
 */

package com.wxhj.cloud.feignClient.business.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.dto.CommonListExportRequestDTO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @className DayAttendanceDataExcelRequestDTO.java
 * @author pjf
 * @date 2020年2月9日 下午12:04:02   
 */

/**
 * @className DayAttendanceDataExcelRequestDTO.java
 * @author pjf
 * @date 2020年2月9日 下午12:04:02 
 */
@Data
@ApiModel(value = "考勤明细导出请求")
public class DayAttendanceDataExcelRequestDTO extends CommonListExportRequestDTO {
    @ApiModelProperty(value = "开始时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginTime;
    @ApiModelProperty(value = "结束时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
}
