/** 
 * @fileName: MonitorAdminApplication.java  
 * @author: pjf
 * @date: 2020年1月6日 上午9:33:51 
 */

package com.wxhj.cloud.monitor.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

import de.codecentric.boot.admin.server.config.EnableAdminServer;

/**
 * @className MonitorAdminApplication.java
 * @author pjf
 * @date 2020年1月6日 上午9:33:51   
*/

@SpringBootApplication
@EnableDiscoveryClient
@EnableAdminServer
public class MonitorAdminApplication {
	public static void main(String[] args) {
        SpringApplication.run(MonitorAdminApplication.class, args);
    }
}
