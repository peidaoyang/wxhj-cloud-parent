/** 
 * @fileName: AttendanceDayServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:02:54 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.business.mapper.AttendanceDayMapper;
import com.wxhj.cloud.business.service.AttendanceDayService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className AttendanceDayServiceImpl.java
 * @author pjf
 * @date 2019年12月12日 上午11:02:54
 */

@Service
public class AttendanceDayServiceImpl implements AttendanceDayService {
	@Resource
	AttendanceDayMapper attendanceDayMapper;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Override
	@Transactional
	public String insertCascade(AttendanceDayBO attendanceDay) {
		AttendanceDayDO attendanceDayTemp = dozerBeanMapper.map(attendanceDay, AttendanceDayDO.class);
		String id = UUID.randomUUID().toString();
		attendanceDayTemp.setId(id);
		attendanceDayMapper.insert(attendanceDayTemp);
		return id;
	}

	@Override
	@Transactional
	public void updateCascade(AttendanceDayBO attendanceDay) {
		AttendanceDayDO attendanceDayDO = dozerBeanMapper.map(attendanceDay, AttendanceDayDO.class);
		attendanceDayMapper.updateByPrimaryKeySelective(attendanceDayDO);
	}

	@Override
	public void delete(String id) {
		attendanceDayMapper.deleteByPrimaryKey(id);
	}
	
	@Override
	public void delete(List<String> id) {
		id.forEach(q -> {
			attendanceDayMapper.deleteByPrimaryKey(q);
		});
	}

	@Override
	public PageInfo<AttendanceDayDO> listByFullName(IPageRequestModel pageRequestModel, String fullName, String orgainzeId) {
		Example example = new Example(AttendanceDayDO.class);
		example.createCriteria().andLike("fullName","%"+fullName+"%").andEqualTo("organizeId",orgainzeId);
		PageInfo<AttendanceDayDO> pageAttendance = PageUtil.selectPageList(pageRequestModel,
				() -> attendanceDayMapper.selectByExample(example));
		return pageAttendance;
	}
	
	@Override
	public AttendanceDayDO selectById(String id) {
		return attendanceDayMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AttendanceDayDO> listByOrganizeId(String organizeId) {
		Example example = new Example(AttendanceDayDO.class);
		example.createCriteria().andEqualTo("organizeId",organizeId);
		return attendanceDayMapper.selectByExample(example);
	}

	
}
