package com.wxhj.cloud.business.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Builder;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;

/**
 * (OrganizeYearScheduleRecDO)实体类
 *
 * @author makejava
 * @since 2020-05-08 14:01:43
 */
@Table(name = "organize_year_schedule_rec")
@Data
@Builder
public class OrganizeYearScheduleRecDO implements Serializable {
    private static final long serialVersionUID = -51821655168202402L;
    /**
    * 关联的组织年计划管理id
    */
    @Id
    private String organizeYearScheduleId;

    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @Id
    private LocalDate day;
    /**
    * 状态
    */
    private Integer status;
    /**
    * 名称
    */
    private String name;
    /**
    * 备注
    */
    private String memo;

}