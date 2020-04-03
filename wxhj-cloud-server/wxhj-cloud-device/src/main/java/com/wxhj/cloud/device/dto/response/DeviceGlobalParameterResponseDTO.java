/**
 * @className DeviceGlobalParameterResponseDTO.java
 * @admin jwl
 * @date 2019年12月10日 下午2:41:00
 */
package com.wxhj.cloud.device.dto.response;

import java.util.List;


import lombok.Data;
import lombok.ToString;

/**
 * @className DeviceGlobalParameterResponseDTO.java
 * @admin jwl
 * @date 2019年12月10日 下午2:41:00
 */
@Data
@ToString
public class DeviceGlobalParameterResponseDTO {
	private String id;
	private String organizeId;
	private Integer deviceType;
	private List<String> deviceIdList;
	private String parameterFileUrl;
	private String time_stamp;
	private String parameterType;
	private String sceneId;
	private String fullName;
}
