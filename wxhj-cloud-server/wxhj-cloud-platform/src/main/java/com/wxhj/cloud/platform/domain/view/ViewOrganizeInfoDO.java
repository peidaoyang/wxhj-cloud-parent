/**
 * 
 */
package com.wxhj.cloud.platform.domain.view;



import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.time.LocalDateTime;

/**
 * @ClassName: ViewOrganizeInfoDO.java
 * @author: cya
 * @Date: 2020年3月12日 下午5:45:33 
 */
@Data
@Table(name="view_organize_info")
public class ViewOrganizeInfoDO {
	@Id
	@ApiModelProperty(value="主键")
	private String id;
	@ApiModelProperty(value="组织名称")
	private String fullName;
	@ApiModelProperty(value="父级组织名称")
	private String parentFullName;
	@ApiModelProperty(value="父级组织id")
	private String parentId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="创建时间")
	private LocalDateTime creatorTime;
	@ApiModelProperty(value="创建时间")
	private String description;
	@ApiModelProperty(value="删除标志,1表示已经删除，0未删除")
	private Integer isDeleteMark;
}
