package com.wxhj.cloud.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * (ConsumeRule)实体类
 *
 * @author makejava
 * @since 2020-05-28 08:42:27
 */
@Data
@Builder
public class ConsumeRuleDO implements Serializable {
    private static final long serialVersionUID = -44892372711341359L;
    /**
    * 主键id
    */
    @Id
    private String id;
    /**
    * 消费规则名称
    */
    private String name;
    /**
    * 时间段描述
    */
    private String timeDesc;
    /**
    * 第一时间段开始分钟数
    */
    private Integer beginTime1;
    /**
    * 第一时间段结束分钟数
    */
    private Integer endTime1;
    /**
    * 第二时间段开始分钟数
    */
    private Integer beginTime2;
    /**
    * 第二时间段结束分钟数
    */
    private Integer endTime2;
    /**
    * 第三时间段开始分钟数
    */
    private Integer beginTime3;
    /**
    * 第三时间段结束分钟数
    */
    private Integer endTime3;
    /**
    * 规则类型。0：普通消费，1：定次消费
    */
    private Object ruleType;
    /**
    * 次数下限
    */
    private Object countFloor;
    /**
    * 次数上限
    */
    private Object countCeiling;
    /**
    * 基准消费金额，以分为单位
    */
    private Integer baseAmount;
    /**
    * 折扣类型。0：减免金额，1：折扣率
    */
    private Object discountType;
    /**
    * 折扣值
    */
    private Integer discountValue;
    /**
    * 根组织id
    */
    private String organizeId;
    /**
    * 备注
    */
    private String memo;
    /**
    * 创建时间
    */
    private LocalDateTime createTime;

}