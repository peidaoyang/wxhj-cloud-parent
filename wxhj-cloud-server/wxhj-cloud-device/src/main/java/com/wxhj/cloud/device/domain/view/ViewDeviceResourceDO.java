/**
 * @className ViewDeviceResourceDO.java
 * @admin jwl
 * @date 2020年1月7日 下午2:22:11
 */
package com.wxhj.cloud.device.domain.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.Id;
import javax.persistence.Table;
import java.time.LocalDate;
import java.time.LocalDateTime;


/**
 * @className ViewDeviceResourceDO.java
 * @admin jwl
 * @date 2020年1月7日 下午2:22:11
 */
@Data
@Table(name = "view_device_resource")
public class ViewDeviceResourceDO {
    @Id
    private String id;
    //pos机编号
    private String deviceId;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @JsonFormat(pattern = "yyyy-MM-dd")
    //下发时间
    private LocalDate datetime;
    //资源类型
    private Integer resourceType;
    //下发类型
    private Integer sentState;
    //参数版本
    private String versionNumber;
    //下发文件名
    private String fileName;
    //更新说明
    private String updateDescribe;
    //设备别名
    private String deviceName;
    private String organizeId;
    //设备类型
    private Integer deviceType;

    private Integer fileSize;

    private String md5;

}
