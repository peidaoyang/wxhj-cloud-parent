package com.wxhj.cloud.device.service.impl;

import com.wxhj.cloud.device.domain.view.ViewDeviceStateTotalDO;
import com.wxhj.cloud.device.mapper.ViewDeviceStateTotalMapper;
import com.wxhj.cloud.device.service.ViewDeviceStateTotalService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.util.List;

@Service
public class ViewDeviceStateTotalServiceImpl implements ViewDeviceStateTotalService {
    @Resource
    ViewDeviceStateTotalMapper viewDeviceStateTotalMapper;


    @Override
    public List<ViewDeviceStateTotalDO> select(String organizeId) {
        Example example = new Example(ViewDeviceStateTotalDO.class);
        example.createCriteria().andEqualTo("organizeId",organizeId);
        return viewDeviceStateTotalMapper.selectByExample(example);
    }
}
