/**
 * @className DeviceVersionResponseDTO.java
 * @admin jwl
 * @date 2020年1月8日 上午9:15:18
 */
package com.wxhj.cloud.device.dto.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className DeviceVersionResponseDTO.java
 * @admin jwl
 * @date 2020年1月8日 上午9:15:18
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class DeviceVersionResponseDTO {
	private String versionId;
	private String fileName;
	private Integer resourceType;
}
