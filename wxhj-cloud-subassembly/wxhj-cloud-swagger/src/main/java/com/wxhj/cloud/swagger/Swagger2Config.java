/** 
 * @fileName: Swagger2config.java  
 * @author: pjf
 * @date: 2019年10月11日 下午1:37:56 
 */

package com.wxhj.cloud.swagger;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @className Swagger2config.java
 * @author pjf
 * @date 2019年10月11日 下午1:37:56
 */
@Configuration
@EnableSwagger2
public class Swagger2Config {
	// swagger2的配置文件，这里可以配置swagger2的一些基本的内容，比如扫描的包等等
	@Bean
	public Docket createRestApi() {
		return new Docket(DocumentationType.SWAGGER_2).apiInfo(apiInfo()).select()
				// 为当前包路径
				.apis(RequestHandlerSelectors.basePackage("com.wxhj.cloud")).paths(PathSelectors.any()).build();
	}

	// 构建 api文档的详细信息函数,注意这里的注解引用的是哪个
	private ApiInfo apiInfo() {
		return new ApiInfoBuilder()
				// 页面标题
				.title("Spring Boot 测试使用   API")
				// 创建人
				.contact(new Contact("pjf", "http://www.baidu.com", "592780385@qq.com"))
				// 版本号
				.version("1.0")
				// 描述
				.description("程序调试专用，祝愿一次通过").build();
	}
}
