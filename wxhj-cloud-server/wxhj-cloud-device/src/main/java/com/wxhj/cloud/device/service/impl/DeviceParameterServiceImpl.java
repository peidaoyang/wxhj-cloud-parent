package com.wxhj.cloud.device.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceParameterDO;
import com.wxhj.cloud.device.mapper.DeviceParameterMapper;
import com.wxhj.cloud.device.service.DeviceParameterService;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className DeviceParameterService.java
 * @author jwl
 * @date 2019年12月2日 上午8:40:43
 */
@Service
public class DeviceParameterServiceImpl implements DeviceParameterService {

	@Resource
	DeviceParameterMapper deviceParameterMapper;

	@Override
	public void insert(DeviceParameterDO deviceParameter) {
//		if(deviceParameter.getInOutMark() == null){
//			deviceParameter.setInOutMark(0);
//		}
		deviceParameterMapper.insertSelective(deviceParameter);
	}

//	@Override
//	public void delete(String deviceId) {
//		deviceParameterMapper.deleteByPrimaryKey(deviceId);
//	}

//	@Override
//	public List<DeviceParameterDO> list() {
//		return deviceParameterMapper.selectAll();
//	}

	@Override
	public void update(DeviceParameterDO deviceParameter) {
		deviceParameterMapper.updateByPrimaryKeySelective(deviceParameter);
	}

	@Override
	public PageInfo<DeviceParameterDO> listPage(IPageRequestModel pageRequestModel, String valueName, String organizeId,
			String type) {
		Example example = new Example(DeviceParameterDO.class);
		Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId);
		if (Strings.isNotBlank(valueName)) {
			//由于前端会传sceneName字段，需要过滤一下
			if (!"sceneName".equals(type)) {
				criteria.andLike(type, "%" + valueName + "%");
			}
		}
		

		return PageUtil.selectPageList(pageRequestModel, () -> deviceParameterMapper.selectByExample(example));
	}

//	@Override
//	public List<DeviceParameterDO> listDeviceByOrganizeId(String organizeId) {
//		Example example = new Example(DeviceParameterDO.class);
//		example.createCriteria().andEqualTo("organizeId", organizeId);
//		return deviceParameterMapper.selectByExample(example);
//	}
//
//	@Override
//	public List<DeviceParameterDO> listDeviceBySceneId(String sceneId) {
//		Example example = new Example(DeviceParameterDO.class);
//		example.createCriteria().andEqualTo("sceneId", sceneId);
//		return deviceParameterMapper.selectByExample(example);
//	}

	@Override
	public DeviceParameterDO selectByDeviceId(String deviceId) {
		Example example = new Example(DeviceParameterDO.class);
		// example.setOrderByClause("parameter_version desc");
		example.createCriteria().andEqualTo("deviceId", deviceId);

		return deviceParameterMapper.selectOneByExample(example);

	}

	@Override
	public List<DeviceParameterDO> listByDeviceId(List<String> deviceId) {
		Example example = new Example(DeviceParameterDO.class);
		example.createCriteria().andIn("deviceId", deviceId);
		return deviceParameterMapper.selectByExample(example);
	}

	@Override
	public List<DeviceParameterDO> listByOrganizeId(String organizeId) {
		Example example = new Example(DeviceParameterDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		return deviceParameterMapper.selectByExample(example);
	}

}
