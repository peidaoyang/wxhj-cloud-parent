package com.wxhj.cloud.device.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceAuthorizeDO;
import com.wxhj.cloud.device.mapper.DeviceAuthorizeMapper;
import com.wxhj.cloud.device.service.DeviceAuthorizeService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className DeviceAuthorizeServiceImpl.java
 * @author jwl
 * @date 2019年11月28日 下午4:33:57
 */
@Service
public class DeviceAuthorizeServiceImpl implements DeviceAuthorizeService {

	@Resource
	DeviceAuthorizeMapper deviceAuthorizeMapper;

	@Override
	public String insert(DeviceAuthorizeDO deviceAuthorize) {
		deviceAuthorize.setId(UUID.randomUUID().toString());
		deviceAuthorizeMapper.insert(deviceAuthorize);
		return deviceAuthorize.getId();
	}

	@Transactional
	@Override
	public void insert(List<DeviceAuthorizeDO> deviceAuthorizeList) {
		deviceAuthorizeList.forEach(q -> this.insert(q));
	}

	@Override
	public void deleteById(String id) {
		deviceAuthorizeMapper.deleteByPrimaryKey(id);
	}

	@Override
	public List<DeviceAuthorizeDO> list() {
		return deviceAuthorizeMapper.selectAll();
	}

	@Override
	public DeviceAuthorizeDO selectByDeviceIdAndType(String deviceId, Integer authorizeType) {
		Example example = new Example(DeviceAuthorizeDO.class);
		example.orderBy("creatorTime");
		example.createCriteria().andEqualTo("deviceId", deviceId).andEqualTo("authorizeType", authorizeType);
		DeviceAuthorizeDO device = deviceAuthorizeMapper.selectOneByExample(example);
		return device;
	}

	@Override
	public void update(DeviceAuthorizeDO deviceAuthorize) {

		deviceAuthorizeMapper.updateByPrimaryKeySelective(deviceAuthorize);
	}

	@Override
	public DeviceAuthorizeDO selectByDeviceIdIsNullAndType(Integer authorizeType) {
		Example example = new Example(DeviceAuthorizeDO.class);
		example.createCriteria().andEqualTo("authorizeType", authorizeType).andIsNull("deviceId");
		List<DeviceAuthorizeDO> selectByExample = deviceAuthorizeMapper.selectByExample(example);
		return selectByExample.size() > 0 ? selectByExample.get(0) : null;
	}

	@Override
	public PageInfo<DeviceAuthorizeDO> listDeviceAuthorize(IPageRequestModel pageRequestModel) {
		PageInfo<DeviceAuthorizeDO> pageDeviceAuthorize = PageUtil.selectPageList(pageRequestModel,
				() -> deviceAuthorizeMapper.selectAll());
		return pageDeviceAuthorize;
	}

}
