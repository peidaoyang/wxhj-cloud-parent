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
 * (NationLegalVocation)实体类
 *
 * @author makejava
 * @since 2020-05-08 14:01:43
 */
@Table(name = "nation_legal_vocation")
@Data
@Builder
public class NationLegalVocationDO implements Serializable {
    private static final long serialVersionUID = -51821655168202402L;
    /**
    * 日期，主键
    */
    @Id
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate day;
    /**
    * 日期对应的年
    */
    private Integer year;
    /**
    * 状态
    */
    private Integer status;
    /**
    * 节假日名称
    */
    private String name;
    /**
    * 创建时间
    */
    private Date createTime;
    /**
    * 备注
    */
    private String memo;

}