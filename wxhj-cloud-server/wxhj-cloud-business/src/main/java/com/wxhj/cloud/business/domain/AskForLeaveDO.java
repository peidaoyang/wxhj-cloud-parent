package com.wxhj.cloud.business.domain;

import lombok.Data;
import org.apache.ibatis.type.JdbcType;
import tk.mybatis.mapper.annotation.ColumnType;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;


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
  //  @ColumnType(jdbcType= JdbcType.date)
    private LocalDateTime startTime;
    /**
     * 请假结束时间
     */
    private LocalDateTime endTime;
    /**
     * 请假申请时间
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
     * 请假原因
     */
    private String reason;
    /**
     * 备注
     */
    private String memo;
}
