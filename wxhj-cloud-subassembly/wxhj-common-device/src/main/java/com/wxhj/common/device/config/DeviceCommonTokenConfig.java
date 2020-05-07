package com.wxhj.common.device.config;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/4/30 10:08 上午
 */
@Component
@Data
public class DeviceCommonTokenConfig {
    private List<String> matchingUrls = Arrays.asList("/deviceServer/**");

    private List<String> excludedPaths = Arrays.asList("/deviceServer/test");

    private String md5Key = "52606F34C985403D850D411BCB600DCR";
//    private String md5Key = "xhsgxxn";

}
