package com.wxhj.cloud.device.controller;

import com.wxhj.cloud.redis.annotation.LogAnnotationController;
import com.wxhj.common.device.api.DefaultDeviceCommonController;
import org.springframework.web.bind.annotation.RestController;

/**
 * 日志记录专用
 * @author daxiong
 * @date 2020/4/28 10:38 上午
 */
@LogAnnotationController
@RestController
public class DeviceCommonController extends DefaultDeviceCommonController {
}
