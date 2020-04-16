package com.wxhj.cloud.device.domain.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Table;
import java.util.Date;

@Data
@Table(name = "view_device_record")
public class ViewDeviceRecordDO {
    private String organizeId;
    private Integer recordType;
    private Integer receivedCount;
    @JsonFormat(pattern="yyyy-MM-dd")
    @DateTimeFormat(pattern="yyyy-MM-dd")
    private Date receivedDate;
}
