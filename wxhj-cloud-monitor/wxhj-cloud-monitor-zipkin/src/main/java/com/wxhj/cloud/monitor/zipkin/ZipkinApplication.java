/** 
 * @fileName: ZipkinApplication.java  
 * @author: pjf
 * @date: 2020年1月6日 上午11:28:14 
 */

package com.wxhj.cloud.monitor.zipkin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import zipkin.server.internal.EnableZipkinServer;

/**
 * @className ZipkinApplication.java
 * @author pjf
 * @date 2020年1月6日 上午11:28:14
 */

@SpringBootApplication
@EnableZipkinServer
public class ZipkinApplication {
	public static void main(String[] args) {
		SpringApplication.run(ZipkinApplication.class, args);
	}
}
