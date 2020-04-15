package com.wxhj.cloud.device.service.impl;

import com.wxhj.cloud.device.domain.view.ViewDeviceStateTotalDO;
import com.wxhj.cloud.device.mapper.ViewDeviceStateTotalMapper;
import com.wxhj.cloud.device.service.ViewDeviceStateTotalService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

@Service
public class ViewDeviceStateTotalServiceImpl implements ViewDeviceStateTotalService {
    @Resource
    ViewDeviceStateTotalMapper viewDeviceStateTotalMapper;


    @Override
    public ViewDeviceStateTotalDO select(String organizeId) {
        return viewDeviceStateTotalMapper.selectByPrimaryKey(organizeId);
    }
}
