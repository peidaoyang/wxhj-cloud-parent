/**
 * @className DeviceResourceService.java
 * @admin jwl
 * @date 2020年1月7日 下午12:56:53
 */
package com.wxhj.cloud.device.service;

import java.util.List;

import com.wxhj.cloud.device.domain.DeviceResourceDO;

/**
 * @className DeviceResourceService.java
 * @admin jwl
 * @date 2020年1月7日 下午12:56:53
 */
public interface DeviceResourceService {
	String insert(DeviceResourceDO deviceResourceDO);
	
	List<String> insertList(List<DeviceResourceDO> deviceResource);
	
	void update(DeviceResourceDO deviceResourceDO);
	
	void delete(String id);
	
	List<DeviceResourceDO> selectByPosIdAndResourceType(List<String> posId, Integer resourceType);
	
//	List<DeviceResourceDO> listByPosId(String posId);

}
