/** 
 * @fileName: TokenBaseMatchConfig.java  
 * @author: pjf
 * @date: 2019年10月25日 上午11:44:56 
 */

package com.wxhj.cloud.gateway.config;
/**
 * @className TokenBaseMatchConfig.java
 * @author pjf
 * @date 2019年10月25日 上午11:44:56   
*/

import java.util.List;

/**
 * 
 * @className BaseMatchConfig.java
 * @author pjf
 * @date 2019年11月6日 上午10:46:15
 */
public interface BaseMatchConfig {
	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 上午10:46:19 
	 * @return
	 */
	List<String> getMatchingUrls();
	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 上午10:46:22 
	 * @param matchingUrls
	 */
	void setMatchingUrls(List<String> matchingUrls);
	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 上午10:46:25 
	 * @return
	 */
	List<String> getExcludedPaths();
	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 上午10:46:28 
	 * @param excludedPaths
	 */
	void setExcludedPaths(List<String> excludedPaths);
}
