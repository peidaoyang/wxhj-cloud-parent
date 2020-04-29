package com.wxhj.cloud.feignClient.business.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.bo.IDeviceRecordModel;
import com.wxhj.cloud.feignClient.bo.IOrganizeSceneModel;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@ApiModel("门禁明细返回对象")
@ExcelDocumentAnnotation
public class EntranceDataVO implements IOrganizeSceneModel {
    @ExcelColumnAnnotation(columnName = "entranceData.accountId")
    @ApiModelProperty(value = "用户ID")
    private String accountId;
    @ExcelColumnAnnotation(columnName = "entranceData.accountName")
    @ApiModelProperty(value = "用户姓名")
    private String accountName;
    @ApiModelProperty(value = "组织ID")
    private String organizeId;
    @ExcelColumnAnnotation(columnName = "entranceData.organizeName")
    @ApiModelProperty(value = "所属组织")
    private String organizeName;
    @ApiModelProperty(value = "场景ID")
    private String sceneId;
    @ExcelColumnAnnotation(columnName = "entranceData.sceneName")
    @ApiModelProperty(value = "应用场景")
    private String sceneName;
    @ExcelColumnAnnotation(columnName = "entranceData.entranceName")
    @ApiModelProperty("通行规则")
    private String entranceName;
    @ExcelColumnAnnotation(columnName = "entranceData.temperature")
    @ApiModelProperty("体温值")
    private Double temperature;
    @JsonFormat(pattern = "yyyy-MM-dd", timezone = "GMT+8")
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private Date accessDate;
    @ExcelColumnAnnotation(columnName = "entranceData.accessTime")
    @ApiModelProperty("通行时间")
    private String accessTime;

    public void setAccessTime(String accessTime) {
        this.accessTime =DateUtil.getStringDate(this.accessDate,"yyyy-MM-dd")+" "+DateUtil.minute2HourMinute(Integer.parseInt(accessTime));
    }


}
