/** 
 * @fileName: DeviceHearbeatResponseDTO.java  
 * @author: pjf
 * @date: 2019年12月5日 上午8:59:49 
 */

package com.wxhj.cloud.device.dto.response;

import java.util.List;

import com.wxhj.cloud.device.bo.DeviceGlobalParameterBO;
import com.wxhj.cloud.device.bo.ViewDeviceResourceBO;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

/**
 * @className DeviceHearbeatResponseDTO.java
 * @author pjf
 * @date 2019年12月5日 上午8:59:49   
*/
/**
 * @className DeviceHearbeatResponseDTO.java
 * @author pjf
 * @date 2019年12月5日 上午8:59:49
 */
@Data
@ToString
@ApiModel("签到请求返回对象")
public class DeviceHearbeatResponseDTO {
	@ApiModelProperty(value="设备Id")
	private String deviceId;
	@ApiModelProperty(value="设备名称")
	private String deviceName;
	@ApiModelProperty(value="参数版本")
	private Long parameterVersion;
	@ApiModelProperty(value="人脸信息最小索引")
	private Long faceMinIndex;
	@ApiModelProperty(value="人脸信息最大索引")
	private Long faceMaxIndex;
	@ApiModelProperty(value="设备全局参数返回列表(用于更新全局参数文件)")
	private List<DeviceGlobalParameterBO> deviceGlobalParameterList;
	// DeviceResourceDO
	@ApiModelProperty(value="设备资源参数返回列表(用于更新设备资源文件)")
	private List<ViewDeviceResourceBO> deviceResourceList;

}
