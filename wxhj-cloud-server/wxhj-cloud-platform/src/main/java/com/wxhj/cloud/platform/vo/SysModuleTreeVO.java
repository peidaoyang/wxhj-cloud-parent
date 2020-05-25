/** 
 * @fileName: SysModuleTreeVO.java  
 * @author: pjf
 * @date: 2019年11月13日 下午5:24:39 
 */

package com.wxhj.cloud.platform.vo;


import java.time.LocalDateTime;
import java.util.List;


import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.wxhj.cloud.core.interfaces.ITreeList;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className SysModuleTreeVO.java
 * @author pjf
 * @date 2019年11月13日 下午5:24:39   
*/
/**
 * @className SysModuleTreeVO.java
 * @author pjf
 * @date 2019年11月13日 下午5:24:39
 */
@Data
@ToString
public class SysModuleTreeVO implements ITreeList<SysModuleTreeVO> {
	@ApiModelProperty(value="菜单id")
	private String id;
	@ApiModelProperty(value="父级菜单id")
	private String parentId;
	@ApiModelProperty(value="层级")
	private Integer layers;
	@ApiModelProperty(value="菜单编码")
	private String encode;
	@ApiModelProperty(value="菜单名称")
	private String fullName;
	@ApiModelProperty(value="图片（不能排序）")
	private String icon;
	@ApiModelProperty(value="链接地址")
	private String urlAddress;
	@ApiModelProperty(value="目标")
	private String target;
	
	@ApiModelProperty(value="菜单id")
	private Integer isMenu;
	
	@ApiModelProperty(value="排序")
	private Integer sortCode;
	@ApiModelProperty(value="菜单类型")
	private Integer moduleType;
	
//	@ApiModelProperty(value="是否已经删除，0未删除，1已删除")
//	private Integer isDeleteMark=0;
//	@ApiModelProperty(value="是否可以删除")
//	private Integer isEnabledMark;
	@ApiModelProperty(value="菜单描述")
	private String description;
	
	@ApiModelProperty(value="创建时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime creatorTime;
	@ApiModelProperty(value="创建人员编号")
	private String creatorUserId;
	
	@ApiModelProperty(value="修改时间")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private LocalDateTime lastModifyTime;
	@ApiModelProperty(value="修改人员编号")
	private String lastModifyUserId;
	
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@ApiModelProperty(value="删除时间")
	private LocalDateTime deleteTime;
	@ApiModelProperty(value="菜单id")
	private String deleteUserId;
	
	
	@ApiModelProperty(value="自菜单列表（不能排序）")
	private List<SysModuleTreeVO> children;
}
