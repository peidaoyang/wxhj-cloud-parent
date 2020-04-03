/**
 * 
 */
package com.wxhj.cloud.platform.domain.view;

import java.util.Date;

import javax.persistence.Id;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @ClassName: ViewRoleOrganizeDO.java
 * @author: cya
 * @Date: 2019年11月25日 上午11:05:46 
 */
@Data
@ToString
@Table(name = "view_role_organize")
public class ViewRoleOrganizeDO {
	@Id
	@ApiModelProperty(value="角色id")
	private String id;
	@ApiModelProperty(value="组织id")
	private String organizeId;
	@ApiModelProperty(value="角色编码")
	private String encode;
	@ApiModelProperty(value="角色名称")
	private String fullName;
	@ApiModelProperty(value="角色类型")
	private String type;
	@ApiModelProperty(value="排血编码")
	private Integer sortCode;
	@ApiModelProperty(value="描述")
	private String description;
	@ApiModelProperty(value="组织名称")
	private String organizeName;
	@JsonFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="创建时间")
	private Date creatorTime;
	@ApiModelProperty(value="创建人员id")
	private String creatorUserId;
	@ApiModelProperty(value="是否容许删除，1表示不许删除，0表示可以删除")
	private Integer isDeleteMark;
}
