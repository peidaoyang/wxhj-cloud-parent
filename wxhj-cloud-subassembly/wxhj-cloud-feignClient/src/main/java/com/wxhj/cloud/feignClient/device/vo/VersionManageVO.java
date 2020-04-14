package com.wxhj.cloud.feignClient.device.vo;

import java.util.Date;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.feignClient.bo.IOrganizeModel;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@Data
public class VersionManageVO implements IOrganizeModel {
	@ApiModelProperty(value="主键")
	private String id;
	@ApiModelProperty(value="版本编号")
	private String versionNumber;
	@ApiModelProperty(value="设备类型")
	private Integer deviceType;
	@ApiModelProperty(value="文件名")
	private String fileName;
	@ApiModelProperty(value="发布状态")
	private Integer releaseState;
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	@ApiModelProperty(value="创建人员编号")
	private String creatorUserId;
	
	@ApiModelProperty(value="修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	@ApiModelProperty(value="修改人id")
	private String lastModifyUserId;
	@ApiModelProperty(value="修改描述")
	private String updateDescribe;
	@ApiModelProperty(value="资源类型")
	private Integer resourceType;
	@ApiModelProperty(value="组织编号（不可排序）")
	private String organizeId;
	@ApiModelProperty(value="组织名称（不可排序）")
	private String organizeName;

	@ApiModelProperty(value = "上传文件大小(kb为单位)")
	private Integer fileSize;
}