/** 
 * @fileName: TreeControlVO.java  
 * @author: pjf
 * @date: 2019年11月13日 下午2:12:39 
 */

package com.wxhj.cloud.feignClient.vo;

import java.util.List;

import com.wxhj.cloud.core.interfaces.ITreeElement;
import com.wxhj.cloud.core.interfaces.ITreeList;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className TreeControlVO.java
 * @author pjf
 * @date 2019年11月13日 下午2:12:39   
*/
/**
 * @className TreeControlVO.java
 * @author pjf
 * @date 2019年11月13日 下午2:12:39
 */
@Data
@ToString
@NoArgsConstructor
@AllArgsConstructor
@ApiModel(description = "组织树形菜单控件")
public class TreeListControlVO implements ITreeElement, ITreeList<TreeListControlVO> {
	@ApiModelProperty(value = "菜单id")
	private String id;
	@ApiModelProperty(value = "菜单父级id")
	private String parentId;
	@ApiModelProperty(value = "菜单名称")
	private String fullName;
	@ApiModelProperty(value = "菜单层级")
	private Integer layers;
	@ApiModelProperty(value = "排序")
	private Integer sortCode;
	@ApiModelProperty(value = "子级列表")
	private List<TreeListControlVO> children;
}
