package com.wxhj.cloud.device.service;

import com.wxhj.cloud.device.domain.view.ViewDeviceStateTotalDO;

public interface ViewDeviceStateTotalService {
    ViewDeviceStateTotalDO select(String organizeId);
}
