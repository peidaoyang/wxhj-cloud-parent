/** 
 * @fileName: GatewayStaticClass.java  
 * @author: pjf
 * @date: 2019年10月25日 下午4:30:13 
 */

package com.wxhj.cloud.gateway.config;

import java.util.List;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

/**
 * @className GatewayStaticClass.java
 * @author pjf
 * @date 2019年10月25日 下午4:30:13
 */

public class GatewayStaticClass {

	private static PathMatcher pathMatcher = new AntPathMatcher();

	public static boolean matchUrl(BaseMatchConfig baseMatchConfig, String servletPath) {
		boolean flag = false;
		List<String> matchingUrls = baseMatchConfig.getMatchingUrls();
		for (String matchingUrl : matchingUrls) {
			flag = flag || pathMatcher.match(matchingUrl, servletPath);
		}

		if (!flag) {
			return false;
		}
		if (baseMatchConfig.getExcludedPaths() != null && baseMatchConfig.getExcludedPaths().size() > 0) {
			for (String excludedPath : baseMatchConfig.getExcludedPaths()) {
				if (pathMatcher.match(excludedPath, servletPath)) {
					return false;
				}
			}
		}
		return true;
	}
}
