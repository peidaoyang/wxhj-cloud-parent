/** 
 * @fileName: AttendanceDayRecServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月12日 上午11:02:23 
 */

package com.wxhj.cloud.business.service.impl;

import java.util.List;

import javax.annotation.Resource;

import com.github.dozermapper.core.Mapper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.business.mapper.AttendanceDayRecMapper;
import com.wxhj.cloud.business.service.AttendanceDayRecService;

import tk.mybatis.mapper.entity.Example;

/**
 * @className AttendanceDayRecServiceImpl.java
 * @author pjf
 * @date 2019年12月12日 上午11:02:23
 */
@Service
public class AttendanceDayRecServiceImpl implements AttendanceDayRecService {
	@Resource
	AttendanceDayRecMapper attendanceDayRecMapper;
	@Resource
	Mapper dozerBeanMapper;
	@Override
	@Transactional
	public void insertList(List<AttendanceDayRecDO> attendanceDayRecList) {
		attendanceDayRecList.forEach(q -> {
			//AttendanceDayRecDO attendanceDayRec = dozerBeanMapper.map(q, AttendanceDayRecDO.class);
			attendanceDayRecMapper.insert(q);
		});
	}

	@Override
	@Transactional
	public void insert(AttendanceDayRecDO attendanceDayRec) {
		attendanceDayRecMapper.insert(attendanceDayRec);
	}

//	@Override
//	@Transactional
//	public void updateList(List<AttendanceDayRecBO> attendanceDayRecList) {
//		attendanceDayRecList.forEach(q -> {
//			AttendanceDayRecDO attendanceDayRec = dozerBeanMapper.map(q, AttendanceDayRecDO.class);
//			attendanceDayRecMapper.updateByPrimaryKeySelective(attendanceDayRec);
//		});
//	}
	
	@Override
	public void update(AttendanceDayRecDO attendanceDayRec) {
		attendanceDayRecMapper.updateByPrimaryKeySelective(attendanceDayRec);
	}

	@Override
	@Transactional
	public void delete(String attendanceId) {
		Example example = new Example(AttendanceDayRecDO.class);
//		example.createCriteria().andEqualTo("attendanceId",attendanceId);
		example.createCriteria().andEqualTo("id",attendanceId);
		attendanceDayRecMapper.deleteByExample(example);
	}
	
//	@Override
//	public IPageResponseModel listAttendanceDayRec(IPageRequestModel pageRequestModel, String attendanceId) {
//		Example example = new Example(AttendanceDayRecDO.class);
//		example.createCriteria().andEqualTo("attendanceId",attendanceId);
//		PageInfo<AttendanceDayRecDO> pageAttendanceDayRec = PageUtil.selectPageList(pageRequestModel,
//				() -> attendanceDayRecMapper.selectByExample(example));
//		PageDefResponseModel pageDefResponseModel = new PageDefResponseModel();
//		pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageAttendanceDayRec,
//				pageDefResponseModel, AttendanceDayRecDO.class);
//		return pageDefResponseModel;
//	}

	@Override
	public List<AttendanceDayRecDO> listById(String id) {
		Example example = new Example(AttendanceDayRecDO.class);
		example.createCriteria().andEqualTo("id", id);
		return attendanceDayRecMapper.selectByExample(example);
	}

	@Override
	public List<AttendanceDayRecDO> listByIdList(List<String> idList) {
		Example example = new Example(AttendanceDayRecDO.class);
		example.createCriteria().andIn("id",idList);
		return attendanceDayRecMapper.selectByExample(example);
	}


//	@Override
//	public List<AttendanceDayRecDO> listAttendanceDayRecById(String Id) {
//		Example example = new Example(AttendanceDayRecDO.class);
//		example.createCriteria().andEqualTo("id",Id);
//		return attendanceDayRecMapper.selectByExample(example);
//	}

}
