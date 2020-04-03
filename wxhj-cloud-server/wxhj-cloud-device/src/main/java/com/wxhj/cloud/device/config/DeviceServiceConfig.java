/** 
 * @fileName: DeviceServiceConfig.java  
 * @author: pjf
 * @date: 2019年12月17日 下午1:53:37 
 */

package com.wxhj.cloud.device.config;

import java.util.Map;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @className DeviceServiceConfig.java
 * @author pjf
 * @date 2019年12月17日 下午1:53:37   
*/
/**
 * @className DeviceServiceConfig.java
 * @author pjf
 * @date 2019年12月17日 下午1:53:37
 */
@SpringBootConfiguration
@ConfigurationProperties("wxhj.device")
@Data
public class DeviceServiceConfig {
	private Map<Integer, String> recordTopicMap;
}
