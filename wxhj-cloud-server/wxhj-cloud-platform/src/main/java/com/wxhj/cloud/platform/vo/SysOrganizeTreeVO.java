/** 
 * @fileName: SysOrganizeTreeVO.java  
 * @author: pjf
 * @date: 2019年11月13日 下午3:46:41 
 */

package com.wxhj.cloud.platform.vo;

import java.util.Date;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.interfaces.ITreeList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SysOrganizeTreeVO.java
 * @author pjf
 * @date 2019年11月13日 下午3:46:41   
*/
/**
 * @className SysOrganizeTreeVO.java
 * @author pjf
 * @date 2019年11月13日 下午3:46:41
 */
@Data
@ToString
@ApiModel( description = "组织树级显示列表对象")
public class SysOrganizeTreeVO implements ITreeList<SysOrganizeTreeVO> {
	@ApiModelProperty(value = "组织id", example = "guid")
	private String id;
	@ApiModelProperty(value = "父级id", example = "guid")
	private String parentId;
	@ApiModelProperty(value = "层级", example = "guid")
	private Integer layers = 1;
	@ApiModelProperty(value = "组织编码", example = "000011")
	private String encode;
	@ApiModelProperty(value = "组织名称", example = "xxx组织")
	private String fullName;
	@ApiModelProperty(value = "简称")
	private String shortName;
	@ApiModelProperty(value = "分类id")
	private String categoryId;
	@ApiModelProperty(value = "负责人id")
	private String managerId;
	@ApiModelProperty(value = "电话")
	private String telephone;
	@ApiModelProperty(value = "手机")
	private String mobilePhone;
	@ApiModelProperty(value = "微信")
	private String wechat;
	@ApiModelProperty(value = "传真")
	private String fax;
	@ApiModelProperty(value = "邮箱")
	private String email;
	@ApiModelProperty(value = "归属区域")
	private String areaid;
	@ApiModelProperty(value = "地址")
	private String address;
	@ApiModelProperty(value = "允许编辑")
	private Integer isAllowEdit;
	@ApiModelProperty(value = "允许删除")
	private Integer isAllowDelete;
	@ApiModelProperty(value = "排序字段", example="1")
	private Integer sortCode;
	@ApiModelProperty(value = "描述", example="用于xxx的作用")
	private String description;
	
	@ApiModelProperty(value = "创建时间", example="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date creatorTime;
	@ApiModelProperty(value = "创建人", example="李四")
	private String creatorUserId;
	
	@ApiModelProperty(value = "最后修改时间", example="yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastModifyTime;
	@ApiModelProperty(value = "最后修改人", example="张三")
	private String lastModifyUserId;
	
	@ApiModelProperty(value = "组织下的子级组织")
	private List<SysOrganizeTreeVO> children;
	@ApiModelProperty(value = "是否有效标志")
	private Integer isEnabledMark;
}
