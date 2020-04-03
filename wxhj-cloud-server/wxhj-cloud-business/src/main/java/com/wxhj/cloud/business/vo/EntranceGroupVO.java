//package com.wxhj.cloud.business.vo;
//
//import java.util.Date;
//
//import org.springframework.format.annotation.DateTimeFormat;
//
//import com.fasterxml.jackson.annotation.JsonFormat;
//import com.wxhj.cloud.feignClient.bo.IOrganizeModel;
//
//import io.swagger.annotations.ApiModelProperty;
//import lombok.Data;
//
//@Data
//public class EntranceGroupVO implements IOrganizeModel {
//	@ApiModelProperty(value="通行组编号")
//	private String id;
//	@ApiModelProperty(value="通行组名称")
//	private String fullName;
//	@ApiModelProperty(value="通行组类型 0 为周 1 为月")
//	private Integer groupType;
//	
//	@ApiModelProperty(value="组织编号（不能排序）")
//	private String organizeId;
//	@ApiModelProperty(value="组织名称（不能排序）")
//	private String organizeName;
//	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
//	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
//	@ApiModelProperty(value="应用时间")
//	private Date applyDate;
//	@ApiModelProperty(value="参数编号")
//	private String parameterId;
//}
