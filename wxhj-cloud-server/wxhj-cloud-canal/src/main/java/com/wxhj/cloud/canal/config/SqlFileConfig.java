package com.wxhj.cloud.canal.config;

import lombok.Data;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author daxiong
 * @date 2020-04-01 10:29
 */
@SpringBootApplication
@ConfigurationProperties(prefix = "wxhj.sql")
@Data
public class SqlFileConfig {
    private String path;
    private String fileName;
}
