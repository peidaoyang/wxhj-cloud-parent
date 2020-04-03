/**
 * @className DeviceGlobalParameterServiceImpl.java
 * @admin jwl
 * @date 2019年12月10日 上午11:57:21
 */
package com.wxhj.cloud.device.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.bo.DeviceGlobalParameterScreenBO;
import com.wxhj.cloud.device.domain.DeviceGlobalParameterDO;
import com.wxhj.cloud.device.mapper.DeviceGlobalParameterMapper;
import com.wxhj.cloud.device.service.DeviceGlobalParameterService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className DeviceGlobalParameterServiceImpl.java
 * @admin jwl
 * @date 2019年12月10日 上午11:57:21
 */
@Service
public class DeviceGlobalParameterServiceImpl implements DeviceGlobalParameterService {

	@Resource
	DeviceGlobalParameterMapper deviceGlobalParameterMapper;

	@Override
	public String insert(DeviceGlobalParameterDO deviceGlobalParameter) {
		String id = UUID.randomUUID().toString();
		deviceGlobalParameter.setId(id);
		deviceGlobalParameterMapper.insert(deviceGlobalParameter);
		return id;
	}

	@Override
	public void updateCascade(DeviceGlobalParameterDO deviceGlobalParameter) {
		deviceGlobalParameterMapper.updateByPrimaryKey(deviceGlobalParameter);
	}

	@Override
	public void deleteCascade(String id) {
		deviceGlobalParameterMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<DeviceGlobalParameterDO> listDeviceGlobalParameter(IPageRequestModel pageRequestModel,
			String nameValue, String organizeId) {
		Example example = new Example(DeviceGlobalParameterDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andLike("fullName", "%" + nameValue + "%");
		PageInfo<DeviceGlobalParameterDO> list = PageUtil.selectPageList(pageRequestModel,
				() -> deviceGlobalParameterMapper.selectByExample(example));
		return list;
	}

	@Override
	public List<DeviceGlobalParameterDO> selectByDeviceState(
			DeviceGlobalParameterScreenBO deviceGlobalParameterScreen) {
		Example example = new Example(DeviceGlobalParameterDO.class);
		deviceGlobalParameterScreen.getCriteriaList().forEach(q -> {
			example.and(q);
		});
		List<DeviceGlobalParameterDO> deviceGlobalParameterList = deviceGlobalParameterMapper.selectByExample(example);
		return deviceGlobalParameterList;
	}

	@Override
	public DeviceGlobalParameterDO selectById(String id) {

		return deviceGlobalParameterMapper.selectByPrimaryKey(id);
	}

}
