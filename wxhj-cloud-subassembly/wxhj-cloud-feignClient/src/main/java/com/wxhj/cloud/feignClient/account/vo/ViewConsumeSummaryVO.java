/**
 * 
 */
package com.wxhj.cloud.feignClient.account.vo;


import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.file.ExcelColumnAnnotation;
import com.wxhj.cloud.core.file.ExcelDocumentAnnotation;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

/**
 * @ClassName: ViewConsumeSummaryVO.java
 * @author: cya
 * @Date: 2020年3月16日 下午2:34:45 
 */
@Data
@ApiModel(value = "消费汇总报表返回对象")
@ExcelDocumentAnnotation
public class ViewConsumeSummaryVO implements IOrganizeModel{
	@ApiModelProperty(value = "用户ID")
	@ExcelColumnAnnotation(columnName = "accountConsumeSummary.accountId")
	private String accountId;
	@JsonFormat(pattern = "yyyy-MM-dd")
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@ExcelColumnAnnotation(columnName = "accountConsumeSummary.consumeDate")
	@ApiModelProperty(value = "汇总日期")
	private LocalDate consumeDate;
	@ExcelColumnAnnotation(columnName = "accountConsumeSummary.consumeMoney")
	@ApiModelProperty(value = "总消费金额")
	private Double consumeMoney;
	@ApiModelProperty(value = "组织编号")
	@ExcelColumnAnnotation(columnName = "accountConsumeSummary.organizeId")
	private String organizeId;
	@ApiModelProperty(value = "组织名称")
	@ExcelColumnAnnotation(columnName = "accountConsumeSummary.organizeName")
	private String organizeName;
	
	@ApiModelProperty(value = "用户名称")
	@ExcelColumnAnnotation(columnName = "accountConsumeSummary.name")
	private String name;
	public void setConsumeMoney(Double consumeMoney) {
		this.consumeMoney = consumeMoney/100.00;
	}
}
