/** 
 * @fileName: SceneInfoDO.java  
 * @author: pjf
 * @date: 2019年11月13日 上午10:04:55 
 */

package com.wxhj.cloud.platform.domain;

import javax.persistence.Id;
import javax.persistence.Table;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @className SceneInfoDO.java
 * @author pjf
 * @date 2019年11月13日 上午10:04:55
 */

@Table(name = "scene_info")
@Data
@ToString
@NoArgsConstructor
public class SceneInfoDO {
	@Id
	@ApiModelProperty(value="场景id")
	private String id;
	@ApiModelProperty(value="场景名称")
	private String sceneName;
	@ApiModelProperty(value="组织id")
	private String organizeId;
}
