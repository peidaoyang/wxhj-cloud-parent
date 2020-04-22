package com.wxhj.cloud.account.domain;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "account_revoke")
public class AccountRevokeDO  {
    @Id
    private String consumeId;
    private String accountId;
    private Integer consumeMoney;
    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    private Date creatorTime;
    private String creatorUserId;
}
