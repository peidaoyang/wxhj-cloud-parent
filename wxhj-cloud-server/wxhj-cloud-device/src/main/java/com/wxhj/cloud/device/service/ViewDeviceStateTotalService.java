package com.wxhj.cloud.device.service;

import com.wxhj.cloud.device.domain.view.ViewDeviceStateTotalDO;

import java.util.List;

public interface ViewDeviceStateTotalService {
    List<ViewDeviceStateTotalDO> select(String organizeId);
}
