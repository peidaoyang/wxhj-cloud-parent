/** 
 * @fileName: DeviceStateServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月11日 下午2:15:58 
 */

package com.wxhj.cloud.device.service.impl;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.device.domain.DeviceStateDO;
import com.wxhj.cloud.device.mapper.DeviceStateMapper;
import com.wxhj.cloud.device.service.DeviceStateService;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import tk.mybatis.mapper.entity.Example;

import javax.annotation.Resource;
import java.time.LocalDateTime;


/**
 * @className DeviceStateServiceImpl.java
 * @author pjf
 * @date 2020年2月11日 下午2:15:58
 */

@Service
public class DeviceStateServiceImpl implements DeviceStateService {
	@Resource
	DeviceStateMapper deviceStateMapper;

	@Override
	public void insert(DeviceStateDO deviceState) {
		deviceStateMapper.insert(deviceState);
	}

	@Override
	public void update(DeviceStateDO deviceState) {
		deviceStateMapper.updateByPrimaryKey(deviceState);

	}

	@Override
	public DeviceStateDO selectOneById(String deviceId) {
//		Example example = new Example(DeviceStateDO.class);
//		example.createCriteria().andEqualTo("deviceId", deviceId);

		return deviceStateMapper.selectByPrimaryKey(deviceId);
	}

	@Override
	public PageInfo<DeviceStateDO> listPage(IPageRequestModel pageRequestModel, String organizeId, Integer lineType) {
		//获取半小时前时间
		LocalDateTime appointDate =LocalDateTime.now().plusMinutes(-30);

			//	DateUtil.growDateMinute(new Date(),-30);

		Example example = new Example(DeviceStateDO.class);
		Example.Criteria criteria = example.createCriteria();
		criteria.andEqualTo("organizeId", organizeId);
		if(lineType == 1){
			criteria.andGreaterThanOrEqualTo("lastTime",appointDate);
		}else if(lineType == 2){
			criteria.andLessThan("lastTime",appointDate);
		}
		return PageUtil.selectPageList(pageRequestModel, () -> deviceStateMapper.selectByExample(example));
	}



	@Override
	public int countGreaterThanLastTime(LocalDateTime time,String organizeId) {
		Example example = new Example(DeviceStateDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andGreaterThan("lastTime",time);
		return deviceStateMapper.selectCountByExample(example);
	}


	@Override
	public int countDevice(String organizeId) {
		Example example = new Example(DeviceStateDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		return deviceStateMapper.selectCountByExample(example);
	}

	@Override
	public void replace(DeviceStateDO deviceState) {
		if(deviceState.getDeviceState() == null){
			deviceState.setDeviceState(0);
		}
		deviceStateMapper.replace(deviceState);
	}
}
