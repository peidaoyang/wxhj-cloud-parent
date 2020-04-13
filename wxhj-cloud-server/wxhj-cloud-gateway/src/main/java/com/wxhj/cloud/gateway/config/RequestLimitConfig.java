package com.wxhj.cloud.gateway.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
@ConfigurationProperties(prefix = "wxhj.tokenfilter.request-limit")
@Data
public class RequestLimitConfig implements BaseMatchConfig {
    private List<String> matchingUrls;
    private List<String> excludedPaths;
}
