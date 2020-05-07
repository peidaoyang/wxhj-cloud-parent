/** 
 * @fileName: WebTokenConfig.java  
 * @author: pjf
 * @date: 2019年10月11日 上午10:10:31 
 */

package com.wxhj.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @className WebTokenConfig.java
 * @author pjf
 * @date 2019年10月11日 上午10:10:31
 */

@Component
@ConfigurationProperties(prefix = "wxhj.tokenfilter.app")
@Data
public class AppTokenConfig implements BaseMatchConfig{
	private List<String> matchingUrls;

	private List<String> excludedPaths;
	private String md5Key;
}
