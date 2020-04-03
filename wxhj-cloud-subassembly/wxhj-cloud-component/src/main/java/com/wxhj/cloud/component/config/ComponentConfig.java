/** 
 * @fileName: FeignClientConfig.java  
 * @author: pjf
 * @date: 2020年3月7日 下午4:09:22 
 */

package com.wxhj.cloud.component.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;

import com.baidu.aip.face.AipFace;
import com.wxhj.cloud.baidu.auth.DefaultBceCredentials;
import com.wxhj.cloud.baidu.services.bos.BosClient;
import com.wxhj.cloud.baidu.services.bos.BosClientConfiguration;
import com.wxhj.cloud.core.statics.BaiduApiStaticClass;

/**
 * @className FeignClientConfig.java
 * @author pjf
 * @date 2020年3月7日 下午4:09:22   
*/
/**
 * @className FeignClientConfig.java
 * @author pjf
 * @date 2020年3月7日 下午4:09:22
 */
@SpringBootConfiguration
public class ComponentConfig {
	@Bean()
	public AipFace BaiduAipFace() {
		AipFace client = new AipFace(BaiduApiStaticClass.APP_ID, BaiduApiStaticClass.API_KEY,
				BaiduApiStaticClass.SECRET_KEY);
		// 可选：设置网络连接参数
		client.setConnectionTimeoutInMillis(2000);
		client.setSocketTimeoutInMillis(60000);
		return client;
	}

	@Bean()
	public BosClient BaiduBosClient() {
		BosClientConfiguration config = new BosClientConfiguration();
		config.setCredentials(new DefaultBceCredentials(BaiduApiStaticClass.FILE_ACCESS_KEY_ID,
				BaiduApiStaticClass.FILE_SECRET_ACCESS_KEY));
		config.setEndpoint(BaiduApiStaticClass.FILE_ENDPOINT);
		BosClient client = new BosClient(config);
		return client;
	}
}
