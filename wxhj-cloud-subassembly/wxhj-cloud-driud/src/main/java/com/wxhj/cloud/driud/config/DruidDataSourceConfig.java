/** 
 * @fileName: DruidDataSourceConfig.java  
 * @author: pjf
 * @date: 2019年10月8日 下午2:05:51 
 */

package com.wxhj.cloud.driud.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;

import com.alibaba.druid.filter.Filter;
import com.alibaba.druid.filter.stat.StatFilter;
import com.alibaba.druid.pool.DruidDataSource;
import com.alibaba.druid.support.http.StatViewServlet;
import com.google.common.collect.Lists;

/**
 * @className DruidDataSourceConfig.java
 * @author pjf
 * @date 2019年10月8日 下午2:05:51
 */

@SpringBootConfiguration
public class DruidDataSourceConfig {
	@ConfigurationProperties(prefix = "spring.druid")
	@Bean
	public DruidDataSource dataSource() {
		DruidDataSource dataSource = new DruidDataSource();
		// 添加慢日志功能Lists.newArrayList添加guava工具集
		dataSource.setProxyFilters(Lists.newArrayList(statFilter()));
		return dataSource;
	}

	public Filter statFilter() {
		StatFilter filter = new StatFilter();
		// 设置慢sql时间
		filter.setSlowSqlMillis(3000);
		filter.setLogSlowSql(true);
		filter.setMergeSql(true);
		return filter;
	}

	/**
	 * 
	 * @author pjf
	 * @date 2019年11月6日 下午1:11:38
	 * @ 注册StatViewServlet
	 * @return
	 */
	@Bean
	public ServletRegistrationBean servletRegistrationBean() {
		return new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
	}
}
