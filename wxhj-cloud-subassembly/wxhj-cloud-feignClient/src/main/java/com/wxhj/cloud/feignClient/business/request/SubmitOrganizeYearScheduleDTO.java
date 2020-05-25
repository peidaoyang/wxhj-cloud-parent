package com.wxhj.cloud.feignClient.business.request;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.business.vo.OrganizeYearScheduleRecVO;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/14 8:54 上午
 */
@ApiModel(value = "组织年度日期管理DTO")
@Data
public class SubmitOrganizeYearScheduleDTO {
    @ApiModelProperty("id")
    private String id;
    @ApiModelProperty("年作息名称")
    @NotBlank
    private String fullName;
    @ApiModelProperty("组织id")
    @NotBlank
    private String organizeId;
    @ApiModelProperty("状态")
    private Integer status;
    @ApiModelProperty("是否应用法定节假日，1：应用了；2：没应用")
    private Integer useLegalVocation;
    @ApiModelProperty("备注")
    private String memo;
    @ApiModelProperty(value = "开始时间", example = "2020-01-01")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date startTime;
    @ApiModelProperty(value = "结束时间", example = "2020-12-31")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull
    private Date endTime;

    @ApiModelProperty("规则列表")
    private List<OrganizeYearScheduleRecVO> list;
}
