package com.wxhj.cloud.device.service;


import com.wxhj.cloud.device.domain.view.ViewDeviceRecordDO;

import java.util.List;

public interface ViewDeviceRecordService {
    /**
     * 此处默认返回7天内数据
     * @param organizeId
     * @return
     */
    List<ViewDeviceRecordDO> list(String organizeId);
}
