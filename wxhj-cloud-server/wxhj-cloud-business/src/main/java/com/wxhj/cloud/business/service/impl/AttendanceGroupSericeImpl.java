/**
 * @className AttendanceGroupSericeImpl.java
 * @admin jwl
 * @date 2019年12月13日 上午11:50:39
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceGroupDO;
import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.business.mapper.AttendanceGroupMapper;
import com.wxhj.cloud.business.service.AttendanceGroupService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className AttendanceGroupSericeImpl.java
 * @admin jwl
 * @date 2019年12月13日 上午11:50:39
 */
@Service
public class AttendanceGroupSericeImpl implements AttendanceGroupService {

	@Resource
	AttendanceGroupMapper attendanceGroupMapper;
	@Resource
	Mapper dozerBeanMapper;

	@Override
	@Transactional
	public String insertCascade(AttendanceGroupDO attendanceGroup, List<AttendanceGroupRecDO> attendanceGroupRecList) {
		// String id = UUID.randomUUID().toString();
		// attendanceGroup.setId(id);
		attendanceGroupMapper.insert(attendanceGroup);
		return attendanceGroup.getId();
	}

	@Override
	@Transactional
	public void updateCascade(AttendanceGroupDO attendanceGroup, List<AttendanceGroupRecDO> attendanceGroupRecList) {
		attendanceGroupMapper.updateByPrimaryKeySelective(attendanceGroup);
	}

	@Override
	public void deleteCascade(String id) {
		attendanceGroupMapper.deleteByPrimaryKey(id);
	}

	@Override
	public PageInfo<AttendanceGroupDO> listAttendanceGroup(IPageRequestModel pageRequestModel, String fullName,
			String organizeId) {
		Example example = new Example(AttendanceGroupDO.class);
		example.createCriteria().andLike("fullName", "%" + fullName + "%").andEqualTo("organizeId", organizeId);
		PageInfo<AttendanceGroupDO> pageAttendanceGroup = PageUtil.selectPageList(pageRequestModel,
				() -> attendanceGroupMapper.selectByExample(example));
		return pageAttendanceGroup;
	}

	@Override
	public AttendanceGroupDO selectAttendanceGroupById(String id) {
		return attendanceGroupMapper.selectByPrimaryKey(id);
	}

	@Override
	public List<AttendanceGroupDO> listByAttendanceGroupName(String fullName) {
		Example example = new Example(AttendanceGroupDO.class);
		example.createCriteria().andLike("fullName", "%" + fullName + "%");
		return attendanceGroupMapper.selectByExample(example);
	}

	@Override
	public List<AttendanceGroupDO> listAll(String organizeId) {
		Example example = new Example(AttendanceGroupDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId);
		return attendanceGroupMapper.selectByExample(example);
	}

	@Override
	public List<AttendanceGroupDO> listInAtendanceGroupId(List<String> atendanceGroupId) {
		Example example = new Example(AttendanceGroupDO.class);
		example.createCriteria().andIn("id", atendanceGroupId);
		return attendanceGroupMapper.selectByExample(example);
	}

	@Override
	public void update(AttendanceGroupDO attendanceGroup) {
		attendanceGroupMapper.updateByPrimaryKeySelective(attendanceGroup);

	}

}
