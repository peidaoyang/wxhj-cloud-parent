package com.wxhj.cloud.account.domain;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.interfaces.IModelInitialization;
import com.wxhj.cloud.core.utils.DateFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDateTime;

@Data
@Table(name = "account_consume")
public class AccountConsumeDO implements IModelInitialization {
    @Id
    private String consumeId;
    private String accountId;
    private String deviceId;
    private String orderNumber;
    private Integer consumeMoney;
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime consumeDate;
    private String organizeId;
    private String sceneId;
    private Integer isRevoke;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime recordDatetime;
    //设备流水号
    private Long serialNumber;

    private Integer consumeMonth;

    @Override
    public void initialization() {
        isRevoke = 0;
        consumeMonth = Integer.parseInt(DateFormat.getStringDate(this.consumeDate, "yyyyMM"));
    }
}
