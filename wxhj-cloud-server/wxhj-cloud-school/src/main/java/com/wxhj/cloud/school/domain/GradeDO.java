package com.wxhj.cloud.school.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.persistence.Table;
import java.time.LocalDate;

/**
 * @Author cya
 * @Date 2020/5/25
 * @Version V1.0
 **/
@Data
@ApiModel("年级管理")
@Table(name = "grade")
public class GradeDO {
    @ApiModelProperty(value = "主键")
    private String id;
    @ApiModelProperty(value = "年级名称")
    private String name;
    @ApiModelProperty(value = "入学时间")
    private LocalDate enrollmentYear;
    @ApiModelProperty(value = "年级负责人")
    private String responsiblePerson;
    @ApiModelProperty(value = "组织id")
    private String organizeId;
}
