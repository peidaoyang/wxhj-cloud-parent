/**
 * @className AttendanceGroupRecServiceImpl.java
 * @admin jwl
 * @date 2019年12月13日 下午1:08:57
 */
package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceGroupRecDO;
import com.wxhj.cloud.business.mapper.AttendanceGroupRecMapper;
import com.wxhj.cloud.business.service.AttendanceGroupRecService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;

/**
 * @className AttendanceGroupRecServiceImpl.java
 * @admin jwl
 * @date 2019年12月13日 下午1:08:57
 */
@Service
public class AttendanceGroupRecServiceImpl implements AttendanceGroupRecService {

	@Resource
	AttendanceGroupRecMapper attendanceGroupRecMapper;
	@Resource
	DozerBeanMapper dozerBeanMapper;

	@Override
	@Transactional
	public void insertList(List<AttendanceGroupRecDO> attendanceGroupList) {
		attendanceGroupList.forEach(q -> {

			attendanceGroupRecMapper.insert(q);
		});
	}

	@Override
	public void insert(AttendanceGroupRecDO attendanceGroupRec) {
		attendanceGroupRecMapper.insert(attendanceGroupRec);
	}

	@Override
	@Transactional
	public void updateList(List<AttendanceGroupRecDO> attendanceGroupList) {
		attendanceGroupList.forEach(q -> {

			attendanceGroupRecMapper.updateByPrimaryKeySelective(q);
		});
	}

	@Override
	public void update(AttendanceGroupRecDO attendanceGroupRec) {
		attendanceGroupRecMapper.updateByPrimaryKeySelective(attendanceGroupRec);
	}

	@Override
	public void delete(String id) {
		Example example = new Example(AttendanceGroupRecDO.class);
		example.createCriteria().andEqualTo("attendanceGroupId", id);
		attendanceGroupRecMapper.deleteByExample(example);
	}

	@Override
	public IPageResponseModel listAttendanceGroupRec(IPageRequestModel pageRequestModel) {
		Example example = new Example(AttendanceGroupRecDO.class);
		PageInfo<AttendanceGroupRecDO> pageAttendanceRec = PageUtil.selectPageList(pageRequestModel,
				() -> attendanceGroupRecMapper.selectByExample(example));
		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageAttendanceRec,
				pageDefResponseModel, AttendanceGroupRecDO.class);
		return pageDefResponseModel;
	}

	@Override
	public List<AttendanceGroupRecDO> listById(String id) {
		Example example = new Example(AttendanceGroupRecDO.class);
		example.createCriteria().andEqualTo("attendanceGroupId", id);
		return attendanceGroupRecMapper.selectByExample(example);
	}

}
