/** 
 * @fileName: DeviceParameterBO.java  
 * @author: pjf
 * @date: 2020年2月5日 下午12:52:22 
 */

package com.wxhj.cloud.feignClient.device.bo;

import lombok.Data;

/**
 * @className DeviceParameterBO.java
 * @author pjf
 * @date 2020年2月5日 下午12:52:22   
*/

@Data
public class DeviceParameterBO {
	private String deviceId;
	private String organizeId;
	private String sceneId;
	private String parameterUrl;
	private Long parameterVersion;
	private String deviceName;
}
