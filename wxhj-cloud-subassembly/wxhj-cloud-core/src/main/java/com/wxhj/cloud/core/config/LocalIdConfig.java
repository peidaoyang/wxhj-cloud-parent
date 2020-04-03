/** 
 * @fileName: ConfigDemo.java  
 * @author: pjf
 * @date: 2019年10月29日 上午9:24:28 
 */

package com.wxhj.cloud.core.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;

import com.wxhj.cloud.core.utils.HardwareUtil;

import lombok.Data;

/**
 * @className ConfigDemo.java
 * @author pjf
 * @date 2019年10月29日 上午9:24:28   
*/
/**
 * @className ConfigDemo.java
 * @author pjf
 * @date 2019年10月29日 上午9:24:28
 */
@Data
@SpringBootConfiguration
public class LocalIdConfig {
	@Value("${server.port:8080}")
	private int port;

	private String mac = "";

	public LocalIdConfig() {
		mac = HardwareUtil.getMac().replace("-", "");
	}

	public String getLocalId() {
		return mac + ":" + port;
	}

}
