package com.wxhj.cloud.business.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


/**
 * 出差实体
 * @author daxiong
 * @date 2020-04-07 13:40
 */
@Table(name = "on_business")
@Data
public class OnBusinessDO {
    @Id
    private String id;
    /**
     * 账户id
     */
    private String accountId;
    /**
     * 账户名
     */
    private String accountName;
    /**
     * 组织id
     */
    private String organizeId;
    /**
     * 出差时长
     */
    private String duration;
    /**
     * 出差开始时间
     */
    private LocalDateTime startTime;
    /**
     * 出差结束时间
     */
    private LocalDateTime endTime;
    /**
     * 出差申请时间
     */
    private LocalDateTime createTime;
    /**
     * 审核状态
     * @see com.wxhj.cloud.core.enums.ApproveStatusEnum
     */
    private Integer status;
    /**
     * 审核时间
     */
    private LocalDateTime approveTime;
    /**
     * 出差原因
     */
    private String reason;
    /**
     * 备注
     */
    private String memo;
}
