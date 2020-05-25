package com.wxhj.cloud.account.domain;

import lombok.Builder;
import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * (OrganizeCardPriority)实体类
 *
 * @author makejava
 * @since 2020-05-21 11:54:49
 */
@Data
@Builder
@Table(name = "organize_card_priority")
public class OrganizeCardPriorityDO implements Serializable {
    private static final long serialVersionUID = -73436485658294191L;
    /**
    * 组织id
    */
    @Id
    private String organizeId;
    /**
    * 卡类型
    */
    @Id
    private Integer cardType;
    /**
    * 优先级，值越小，优先级越高
    */
    private Integer priority;

}