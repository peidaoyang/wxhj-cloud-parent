/**
 * @className DeviceResourceServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 下午12:59:07
 */
package com.wxhj.cloud.device.service.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.device.domain.DeviceResourceDO;
import com.wxhj.cloud.device.mapper.DeviceResourceMapper;
import com.wxhj.cloud.device.service.DeviceResourceService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className DeviceResourceServiceImpl.java
 * @admin jwl
 * @date 2020年1月7日 下午12:59:07
 */
@Service
public class DeviceResourceServiceImpl implements DeviceResourceService{

	@Resource
	DeviceResourceMapper deviceResourceMapper;
	
	@Override
	public String insert(DeviceResourceDO deviceResourceDO) {
		String id = UUID.randomUUID().toString();
		deviceResourceDO.setId(id);
		deviceResourceDO.setDatetime(new Date());
		deviceResourceDO.setSentState(0);
		deviceResourceMapper.insert(deviceResourceDO);
		return id;
	}
	
	@Override
	public List<String> insert(List<DeviceResourceDO> deviceResource) {
		List<String> idList = new ArrayList<String>();
		deviceResource.forEach(q -> idList.add(this.insert(q)));
		return idList;
	}

	@Override
	public void update(DeviceResourceDO deviceResourceDO) {
		Example example = new Example(DeviceResourceDO.class);
		example.createCriteria().andEqualTo("posId",deviceResourceDO.getPosId())
			.andEqualTo("versionId", deviceResourceDO.getVersionId());
		deviceResourceMapper.updateByExampleSelective(deviceResourceDO, example);
	}

	@Override
	public void delete(String id) {
		Example example = new Example(DeviceResourceDO.class);
		example.createCriteria().andEqualTo("id", id);
		deviceResourceMapper.deleteByExample(example);
	}


	@Override
	public List<DeviceResourceDO> selectByPosIdAndResourceType(List<String> posId, Integer resourceType) {
		Example example = new Example(DeviceResourceDO.class);
		example.createCriteria().andIn("posId", posId).andEqualTo("resourceType", resourceType);
		return deviceResourceMapper.selectByExample(example);
	}

	/*
	 * @Override public List<DeviceResourceDO> listByPosId(String posId) { Example
	 * example = new Example(DeviceResourceDO.class);
	 * example.createCriteria().andEqualTo("posId", posId); return
	 * deviceResourceMapper.selectByExample(example); }
	 */

}
