package com.wxhj.cloud.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.time.LocalDateTime;


@Data
@Table(name = "refund")
public class RefundDO {
    private String refundId;
    private String accountId;
    private Integer amount;
    private Integer type;
    private String otherNo;
    private String otherAccountId;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private LocalDateTime creatorTime;
    private String creatorUserId;
}
