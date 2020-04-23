/** 
 * @fileName: FaceDataDownloadRequest.java  
 * @author: pjf
 * @date: 2019年12月5日 上午9:38:23 
 */

package com.wxhj.common.device.dto.request;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className FaceDataDownloadRequest.java
 * @author pjf
 * @date 2019年12月5日 上午9:38:23   
*/
/**
 * @className FaceDataDownloadRequest.java
 * @author pjf
 * @date 2019年12月5日 上午9:38:23
 */
@Data
@ToString
@ApiModel(description = "人脸信息请求对象")
public class FaceDataDownloadRequestDTO {
	@ApiModelProperty(dataType="Long",value = "开始页数", example = "0")
	@Min(0L)
	private Long startIndex;
	@ApiModelProperty(dataType="Long",value = "结束页数", example = "1")
	@Min(0L)
	private Long endIndex;
	@ApiModelProperty(value = "设备编号", example = "82012345")
	@NotNull
	private String deviceId;
	@ApiModelProperty(value = "场景编号", example = "484fb259-21de-4c22-b01a-d9f3f124a975")
	@NotNull
	private String sceneId;

}
