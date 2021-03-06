/**
 *
 */
package com.wxhj.cloud.feignClient.business.request;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

/**
 * @ClassName: ListMonthAttendanceByAccountIdRequestDTO.java
 * @author: cya
 * @Date: 2020年2月24日 下午4:34:09 
 */
@Data
@ApiModel(value = "app个人汇总报表请求对象")
public class ListMonthAttendanceByAccountIdRequestDTO implements IPageRequestModel {

    @ApiModelProperty(value = "单页行数", example = "50")
    @Min(1)
    private Integer rows;
    @ApiModelProperty(value = "当前页数", example = "1")
    @Min(1)
    private Integer page;
    @ApiModelProperty(value = "排序字段", example = "order_number")
    private String orderBy;
    @ApiModelProperty(value = "开始时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate beginTime;
    @ApiModelProperty(value = "结束时间", example = "2019-12-22")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate endTime;
    @ApiModelProperty(value = "账户编号", example = "0000000028")
    @NotBlank
    private String accountId;

}
