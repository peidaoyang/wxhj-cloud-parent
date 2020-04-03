package com.wxhj.cloud.device.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.config.LocalIdConfig;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.statics.DeviceStaticClass;
import com.wxhj.cloud.device.domain.DeviceInfoDO;
import com.wxhj.cloud.device.mapper.DeviceInfoMapper;
import com.wxhj.cloud.device.service.DeviceInfoService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className DeviceInfoServiceImpl.java
 * @author jwl
 * @date 2019年11月28日 下午4:33:24
 */
@Service
public class DeviceInfoServiceImpl implements DeviceInfoService {

	@Resource
	DeviceInfoMapper deviceInfoMapper;
	@Resource
	LocalIdConfig localIdConfig;

	@Override
	@Transactional
//<<<<<<< .mine
	public String insertCascade(DeviceInfoDO deviceInfoDO) {
//		String id = DeviceStaticClass.PREFIX_DEVICE.concat(selectDeviceSequence().toString());
//||||||| .r53
//	public String insert(DeviceInfoDO deviceInfoDO) {
//		String id = DeviceStaticClass.PREFIX_DEVICE.concat(selectDeviceSequence().toString());
//=======
//	public String insertCascade(DeviceInfoDO deviceInfoDO) {
////		String id = DeviceStaticClass.PREFIX_DEVICE.concat(selectDeviceSequence().toString());
//>>>>>>> .r67
		deviceInfoDO.setId(UUID.randomUUID().toString());
		deviceInfoMapper.insert(deviceInfoDO);
		return deviceInfoDO.getId();
	}

//	private Long selectDeviceSequence() {
//		String terminal = localIdConfig.getLocalId();
//		deviceInfoMapper.replaceSequence(DeviceStaticClass.DEVICE_SEQUENCE, terminal);
//		return deviceInfoMapper.selectSequence(DeviceStaticClass.DEVICE_SEQUENCE, terminal);
//	}
//
//	@Transactional
//	@Override
//	public void insertList(List<DeviceInfoDO> deviceInfoList) {
//		deviceInfoList.forEach(q -> this.insert(q));
//	}

	@Override
	public void deleteById(String id) {
		deviceInfoMapper.deleteByPrimaryKey(id);
	}

	@Override
	public Integer selectCount() {
		Example example = new Example(DeviceInfoDO.class);
		example.createCriteria().andEqualTo("1=1");
		return deviceInfoMapper.selectCountByExample(example);
	}

	@Override
	public List<DeviceInfoDO> list() {
		return deviceInfoMapper.selectAll();
	}

	@Override
	public List<DeviceInfoDO> listByDeviceType(Integer type) {
		Example example = new Example(DeviceInfoDO.class);
		example.createCriteria().andEqualTo("deviceType", type);
		return deviceInfoMapper.selectByExample(example);
	}

	@Override
	public PageInfo<DeviceInfoDO> listDevicePage(IPageRequestModel iPageRequestModel, String deviceModel) {
		Example example = new Example(DeviceInfoDO.class);
		example.createCriteria().andLike("deviceModel", "%" + deviceModel + "%");
		PageInfo<DeviceInfoDO> list = PageUtil.selectPageList(iPageRequestModel,
				() -> deviceInfoMapper.selectByExample(example));
		return list;
	}

	@Override
	public void update(DeviceInfoDO deviceInfoDO) {
		deviceInfoMapper.updateByPrimaryKey(deviceInfoDO);
	}

	@Override
	public DeviceInfoDO selectByDeviceId(String deviceId) {
		Example example = new Example(DeviceInfoDO.class);
		example.createCriteria().andCondition("deviceId", deviceId);
		List<DeviceInfoDO> deviceInfoList = deviceInfoMapper.selectByExample(example);
		if(deviceInfoList.size()>0)
		{
			return deviceInfoList.get(0);
		}
		return null;
	}
}
