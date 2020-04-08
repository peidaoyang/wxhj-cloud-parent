package com.wxhj.cloud.business.domain;

import lombok.Data;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 * 请假实体
 * @author daxiong
 * @date 2020-04-07 13:40
 */
@Table(name = "ask_for_leave")
@Data
public class AskForLeaveDO {
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
     * 请假类型
     * @see com.wxhj.cloud.core.enums.AskForLeaveTypeEnum
     */
    private Integer type;
    /**
     * 请假时长
     */
    private String duration;
    /**
     * 请假开始时间
     */
    private Date startTime;
    /**
     * 请假结束时间
     */
    private Date endTime;
    /**
     * 请假申请时间
     */
    private Date createTime;
    /**
     * 审核状态
     * @see com.wxhj.cloud.core.enums.ApproveStatusEnum
     */
    private Integer status;
    /**
     * 审核时间
     */
    private Date approveTime;
    /**
     * 请假原因
     */
    private String reason;
    /**
     * 备注
     */
    private String memo;
}
