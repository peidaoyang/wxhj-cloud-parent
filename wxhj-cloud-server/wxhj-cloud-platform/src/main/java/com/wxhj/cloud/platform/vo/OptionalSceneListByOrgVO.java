/**
 * 
 */
package com.wxhj.cloud.platform.vo;

import java.util.List;

import com.wxhj.cloud.platform.domain.SceneInfoDO;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @ClassName: OptionalSceneListByOrgVO.java
 * @author: cya
 * @Date: 2020年3月17日 下午2:15:34 
 */
@Data
public class OptionalSceneListByOrgVO {
	@ApiModelProperty(value="编号")
	private String id;
	@ApiModelProperty(value="父级编号")
	private String parentId;
	@ApiModelProperty(value="层级")
	private Integer layers;
	@ApiModelProperty(value="编码")
	private String encode;
	@ApiModelProperty(value="名称")
	private String fullName;
	@ApiModelProperty(value="场景列表")
	private List<SceneInfoDO> sceneList;
}
