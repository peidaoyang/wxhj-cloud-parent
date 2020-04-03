package com.wxhj.cloud.gateway.config;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

import org.apache.catalina.connector.Connector;
import org.apache.coyote.http11.Http11NioProtocol;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.web.embedded.tomcat.TomcatServletWebServerFactory;
import org.springframework.boot.web.servlet.server.ServletWebServerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.core.io.ClassPathResource;

import com.google.common.io.ByteStreams;

import lombok.extern.slf4j.Slf4j;

@SpringBootConfiguration
@Slf4j
public class HttpsConfig {
	@Value("${https.port}")
	private Integer port;

	@Value("${https.ssl.key-store}")
	private String keyStore;

	@Value("${https.ssl.key-store-password}")
	private String keyStorePassword;

	@Value("${https.ssl.key-password}")
	private String keyPassword;

	@Bean
	public ServletWebServerFactory servletContainer() {
		TomcatServletWebServerFactory tomcat = new TomcatServletWebServerFactory();
		tomcat.addAdditionalTomcatConnectors(createSslConnector()); // 添加http

		return tomcat;
	}

	// 配置https
	private Connector createSslConnector() {
		Connector connector = new Connector("org.apache.coyote.http11.Http11NioProtocol");
		Http11NioProtocol protocol = (Http11NioProtocol) connector.getProtocolHandler();
		try {
			ClassPathResource resource = new ClassPathResource(keyStore);
			//
			String tempPath = System.getProperty("java.io.tmpdir") + System.currentTimeMillis() + ".keystore";
			File fileTemp = new File(tempPath);
			//fileTemp.createNewFile();
			ByteStreams.copy(resource.getInputStream(), new FileOutputStream(fileTemp));
			// new FileOutputStream(fileTemp)
			// Files.copy(resource. );
			// IOUtils.copy(resource.getInputStream(),new FileOutputStream(f))
			// resource.getInputStream();
			// File keystore = new ClassPathResource(keyStore).getFile();
			/* File truststore = new ClassPathResource("sample.jks").getFile(); */
			connector.setScheme("https");
			connector.setSecure(true);
			connector.setPort(port);
			protocol.setSSLEnabled(true);
			protocol.setKeystoreFile(fileTemp.getAbsolutePath());
			protocol.setKeystorePass(keyStorePassword);
			protocol.setKeyPass(keyPassword);
			return connector;
		} catch (IOException ex) {
			log.error(ex.getMessage().toString());
		}
		return connector;
	}

}
