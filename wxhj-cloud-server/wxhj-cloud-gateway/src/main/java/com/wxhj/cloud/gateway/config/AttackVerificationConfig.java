/** 
 * @fileName: AttackVerificationConfig.java  
 * @author: pjf
 * @date: 2019年10月25日 下午1:39:25 
 */

package com.wxhj.cloud.gateway.config;

import java.util.List;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import lombok.Data;

/**
 * @className AttackVerificationConfig.java
 * @author pjf
 * @date 2019年10月25日 下午1:39:25   
*/
/**
 * @className AttackVerificationConfig.java
 * @author pjf
 * @date 2019年10月25日 下午1:39:25
 */
@Component
@ConfigurationProperties(prefix = "wxhj.tokenfilter.attack-verification")
@Data
public class AttackVerificationConfig implements BaseMatchConfig {

	private Integer timeoutMillisecond=1000;
	
	private List<String> matchingUrls;

	private List<String> excludedPaths;

}
