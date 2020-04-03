/** 
 * @fileName: CanalServerConfig.java  
 * @author: pjf
 * @date: 2019年12月3日 上午10:30:09 
 */

package com.wxhj.cloud.canal.config;

import java.util.List;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;

import lombok.Data;

/**
 * @className CanalServerConfig.java
 * @author pjf
 * @date 2019年12月3日 上午10:30:09   
*/
/**
 * @className CanalServerConfig.java
 * @author pjf
 * @date 2019年12月3日 上午10:30:09
 */
@SpringBootConfiguration
@ConfigurationProperties("wxhj.canal")
@Data
public class CanalServerConfig {
	private List<String> tableName;
}
