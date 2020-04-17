package com.wxhj.cloud.device.service.impl;

import com.wxhj.cloud.device.domain.view.ViewDeviceRecordDO;
import com.wxhj.cloud.device.mapper.ViewDeviceRecordMapper;
import com.wxhj.cloud.device.service.ViewDeviceRecordService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ViewDeviceRecordServiceImpl implements ViewDeviceRecordService {
    @Resource
    ViewDeviceRecordMapper viewDeviceRecordMapper;

    @Override
    public List<ViewDeviceRecordDO> list(String organizeId) {
        Example example = new Example(ViewDeviceRecordDO.class);
        example.createCriteria().andEqualTo("organizeId",organizeId);
        List<ViewDeviceRecordDO> list = viewDeviceRecordMapper.selectByExample(example);
        return viewDeviceRecordMapper.selectByExample(example);
    }
}
