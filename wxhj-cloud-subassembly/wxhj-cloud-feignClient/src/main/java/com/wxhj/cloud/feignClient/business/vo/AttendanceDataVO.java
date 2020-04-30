package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.bo.IDeviceRecordModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("考勤明细 返回对象")
@ExcelDocumentAnnotation
public class AttendanceDataVO implements IDeviceRecordModel {
    @ExcelColumnAnnotation(columnName = "attendancedata.orderNumber")
    @ApiModelProperty(value = "设备的订单号")
    private String orderNumber;
    @ExcelColumnAnnotation(columnName = "attendancedata.matchingDate")
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @ApiModelProperty(value = "匹配的日期")
    private Date matchingDate;
    @ExcelColumnAnnotation(columnName = "attendancedata.matchingTime")
    @ApiModelProperty(value = "匹配的时间戳")
    private Integer matchingTime;
    @ExcelColumnAnnotation(columnName = "attendancedata.attendanceId")
    @ApiModelProperty(value = "匹配的规则id")
    private String attendanceId;
    @ExcelColumnAnnotation(columnName = "attendancedata.attendanceSequence")
    @ApiModelProperty(value = "匹配的流水号")
    private Integer attendanceSequence;
    @ExcelColumnAnnotation(columnName = "attendancedata.extentType")
    @ApiModelProperty(value = "匹配的到的类型")
    private Integer extentType;
    @ExcelColumnAnnotation(columnName = "attendancedata.recordDatetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @ApiModelProperty(value = "记录原始时间日期")
    private Date recordDatetime;
    @ExcelColumnAnnotation(columnName = "attendancedata.accountId")
    @ApiModelProperty(value = "用户id")
    private String accountId;
    @ExcelColumnAnnotation(columnName = "attendancedata.sceneId")
    @ApiModelProperty(value = "场景id")
    private String sceneId;
    @ExcelColumnAnnotation(columnName = "attendancedata.deviceId")
    @ApiModelProperty(value = "设备id")
    private String deviceId;
    @ExcelColumnAnnotation(columnName = "attendancedata.serialNumber")
    @ApiModelProperty(value = "设备流水号")
    private Long serialNumber;
    @ExcelColumnAnnotation(columnName = "attendancedata.organizeId")
    @ApiModelProperty(value = "组织编号")
    private String organizeId;
    @ExcelColumnAnnotation(columnName = "attendancedata.upTime")
    @ApiModelProperty(value = "上班时间")
    private String upTime;
    @ExcelColumnAnnotation(columnName = "attendancedata.downTime")
    @ApiModelProperty(value = "下班时间")
    private String downTime;

    @ExcelColumnAnnotation(columnName = "attendancedata.recordTimeStamp")
    @ApiModelProperty(value = "记录原始时间戳")
    private Long recordTimeStamp;
//    @ExcelColumnAnnotation(columnName = "attendancedata.upDownMark")
//    @ApiModelProperty(value = "0代表上班，1代表下班")
//    private Integer upDownMark;
    @ExcelColumnAnnotation(columnName = "attendancedata.organizeName")
    @ApiModelProperty(value = "组织名称")
    private String organizeName;
    @ApiModelProperty(value = "用户名")
    private String accountName;
    @ApiModelProperty(value = "场景名称")
    private String sceneName;

    public void setUpTime(String upTime) {
        this.upTime = DateUtil.minute2HourMinute(Integer.parseInt(upTime));
    }
    public void setDownTime(String downTime) {
        this.downTime = DateUtil.minute2HourMinute(Integer.parseInt(downTime));
    }
}
