/**
 * @className DeviceResourceServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 下午12:59:07
 */
package com.wxhj.cloud.device.service.impl;

import com.wxhj.cloud.device.domain.DeviceResourceDO;
import com.wxhj.cloud.device.mapper.DeviceResourceMapper;
import com.wxhj.cloud.device.service.DeviceResourceService;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.ArrayList;

import java.util.List;
import java.util.UUID;

/**
 * @className DeviceResourceServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 下午12:59:07
 */
@Service
public class DeviceResourceServiceImpl implements DeviceResourceService {

    @Resource
    DeviceResourceMapper deviceResourceMapper;

    @Override
    public String insert(DeviceResourceDO deviceResource) {
        String id = UUID.randomUUID().toString();
        deviceResource.setId(id);
        deviceResource.setDatetime(LocalDateTime.now());
        deviceResource.setSentState(0);
        deviceResourceMapper.insert(deviceResource);
        return id;
    }

    @Override
    public List<String> insertList(List<DeviceResourceDO> deviceResourceList) {
        List<String> idList = new ArrayList<String>();
        deviceResourceList.forEach(q -> idList.add(this.insert(q)));
        return idList;
    }

    @Override
    public void update(DeviceResourceDO deviceResource) {
        Example example = new Example(DeviceResourceDO.class);
        example.createCriteria().andEqualTo("deviceId", deviceResource.getDeviceId())
                .andEqualTo("id", deviceResource.getId());
        deviceResourceMapper.updateByExampleSelective(deviceResource, example);
    }

    @Override
    public void delete(String id) {
        Example example = new Example(DeviceResourceDO.class);
        example.createCriteria().andEqualTo("id", id);
        deviceResourceMapper.deleteByExample(example);
    }


    @Override
    public List<DeviceResourceDO> selectByDeviceIdAndResourceType(List<String> deviceIdList, Integer resourceType) {
        Example example = new Example(DeviceResourceDO.class);
        example.createCriteria().andIn("deviceId", deviceIdList).andEqualTo("resourceType", resourceType);
        return deviceResourceMapper.selectByExample(example);
    }
    

}
