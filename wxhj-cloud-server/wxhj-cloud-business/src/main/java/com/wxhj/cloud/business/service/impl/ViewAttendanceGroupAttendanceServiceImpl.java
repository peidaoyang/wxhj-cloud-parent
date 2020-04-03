/**
 * 
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.business.domain.view.ViewAttendanceGroupAttendanceDO;
import com.wxhj.cloud.business.mapper.view.ViewAttendanceGroupAttendanceMapper;
import com.wxhj.cloud.business.service.ViewAttendanceGroupAttendanceService;

import tk.mybatis.mapper.entity.Example;

/**
 * @ClassName: ViewAttendanceGroupAttendanceServiceImpl.java
 * @author: cya
 * @Date: 2020年3月14日 下午2:19:23 
 */
@Service
public class ViewAttendanceGroupAttendanceServiceImpl implements ViewAttendanceGroupAttendanceService{
	@Resource
	ViewAttendanceGroupAttendanceMapper viewAttendanceGroupAttendanceMapper;


	@Override
	public List<ViewAttendanceGroupAttendanceDO> listById(String id) {
		Example example = new Example(ViewAttendanceGroupAttendanceDO.class);
		example.createCriteria().andEqualTo("attendanceGroupId", id);
		return viewAttendanceGroupAttendanceMapper.selectByExample(example);
	}
}
