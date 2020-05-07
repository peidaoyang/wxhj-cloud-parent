/** 
 * @fileName: WebTokenConfig.java  
 * @author: pjf
 * @date: 2019年10月11日 上午10:10:31 
 */

package com.wxhj.cloud.gateway.config;

import java.util.List;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import lombok.Data;

/**
 * @className WebTokenConfig.java
 * @author pjf
 * @date 2019年10月11日 上午10:10:31
 */

@Component
@ConfigurationProperties(prefix = "wxhj.tokenfilter.web")
@Data
public class WebTokenConfig implements BaseMatchConfig{
	private List<String> matchingUrls;

	private List<String> excludedPaths;
	private String md5Key;
}
