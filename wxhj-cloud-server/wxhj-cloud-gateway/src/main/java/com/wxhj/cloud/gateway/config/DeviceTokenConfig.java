/** 
 * @fileName: DeviceTokenConfig.java  
 * @author: pjf
 * @date: 2019年10月30日 下午5:02:18 
 */

package com.wxhj.cloud.gateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @className DeviceTokenConfig.java
 * @author pjf
 * @date 2019年10月30日 下午5:02:18
 */

@Component
@ConfigurationProperties(prefix = "wxhj.tokenfilter.device")
@Data
public class DeviceTokenConfig implements BaseMatchConfig {
	private List<String> matchingUrls;

	private List<String> excludedPaths;
	private String md5Key;
}
