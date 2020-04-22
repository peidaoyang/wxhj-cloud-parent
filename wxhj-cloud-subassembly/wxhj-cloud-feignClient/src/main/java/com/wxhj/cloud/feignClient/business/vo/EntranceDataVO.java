package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import io.swagger.annotations.ApiModel;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("门禁明细返回对象")
@ExcelDocumentAnnotation
public class EntranceDataVO implements IOrganizeSceneModel {
    @ExcelColumnAnnotation(columnName = "entranceData.orderNumber")
    private String orderNumber;
    @ExcelColumnAnnotation(columnName = "entranceData.accessDate")
    @JsonFormat(pattern = "yyyy-MM-dd",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    //HH:mm:ss
    private Date accessDate;
    @ExcelColumnAnnotation(columnName = "entranceData.accessTime")
    private Integer accessTime;
    @ExcelColumnAnnotation(columnName = "entranceData.entranceName")
    private String entranceName;
    @ExcelColumnAnnotation(columnName = "entranceData.entranceSequence")
    private Integer entranceSequence;
    @ExcelColumnAnnotation(columnName = "entranceData.recordDatetime")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date recordDatetime;
    @ExcelColumnAnnotation(columnName = "entranceData.accountName")
    private String accountName;
    @ExcelColumnAnnotation(columnName = "entranceData.sceneId")
    private String sceneId;
    @ExcelColumnAnnotation(columnName = "entranceData.sceneName")
    private String sceneName;
    @ExcelColumnAnnotation(columnName = "entranceData.deviceId")
    private String deviceId;
    @ExcelColumnAnnotation(columnName = "entranceData.deviceName")
    private String deviceName;
    @ExcelColumnAnnotation(columnName = "entranceData.serialNumber")
    private Long serialNumber;
    @ExcelColumnAnnotation(columnName = "entranceData.organizeId")
    private String organizeId;
    @ExcelColumnAnnotation(columnName = "entranceData.organizeName")
    private String organizeName;
    @ExcelColumnAnnotation(columnName = "entranceData.beginTime")
    private Integer beginTime;
    @ExcelColumnAnnotation(columnName = "entranceData.endTime")
    private Integer endTime;
    @ExcelColumnAnnotation(columnName = "entranceData.recordTimeStamp")
    private Long recordTimeStamp;
    @ExcelColumnAnnotation(columnName = "entranceData.temperature")
    private Double temperature;
}
