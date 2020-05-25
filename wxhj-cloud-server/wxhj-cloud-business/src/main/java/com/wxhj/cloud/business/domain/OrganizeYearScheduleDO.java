package com.wxhj.cloud.business.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * (organize_year_schedule)实体类
 *
 * @author makejava
 * @since 2020-05-12 17:13:37
 */
@Data
@Table(name = "organize_year_schedule")
public class OrganizeYearScheduleDO implements Serializable {
    private static final long serialVersionUID = 419528151273669488L;
    /**
    * 主键
    */
    @Id
    private String id;
    /**
    * 年作息名称
    */
    private String fullName;
    /**
    * 组织id
    */
    private String organizeId;
    /**
    * 状态
    */
    private Integer status;
    /**
    * 是否应用法定节假日，1：应用了；2：没应用
    */
    private Integer useLegalVocation;
    /**
    * 备注
    */
    private String memo;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 开始时间
    */
    private Date startTime;
    /**
    * 结束时间
    */
    private Date endTime;

}