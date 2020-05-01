/** 
 * @fileName: GatewayStaticClass.java  
 * @author: pjf
 * @date: 2019年10月25日 下午4:30:13 
 */

package com.wxhj.common.device.config;

import org.springframework.util.AntPathMatcher;
import org.springframework.util.PathMatcher;

import java.util.List;

/**
 * @className DeviceGatewayStaticClass.java
 * @author pjf
 * @date 2019年10月25日 下午4:30:13
 */

public class DeviceGatewayStaticClass {

	private static PathMatcher pathMatcher = new AntPathMatcher();

	public static boolean matchUrl(DeviceCommonTokenConfig baseMatchConfig, String servletPath) {
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
