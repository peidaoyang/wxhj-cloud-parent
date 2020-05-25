/**
 * @className AttendanceDataServiceImpl.java
 * @author jwl
 * @date 2019年12月23日 下午3:54:56
 */
package com.wxhj.cloud.business.service.impl;


import java.time.LocalDateTime;
import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.mapper.AttendanceDataMapper;
import com.wxhj.cloud.business.service.AttendanceDataService;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.IPageResponseModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;

import tk.mybatis.mapper.entity.Example;
import tk.mybatis.mapper.entity.Example.Criteria;

/**
 * @className AttendanceDataServiceImpl.java
 * @author jwl
 * @date 2019年12月23日 下午3:54:56
 */
@Service
public class AttendanceDataServiceImpl implements AttendanceDataService{

	@Resource
	AttendanceDataMapper attendanceDataMapper;
	
	@Transactional
	@Override
	public void insertCascade(AttendanceDataDO attendanceData) {
		attendanceDataMapper.insert(attendanceData);

	}

	@Override
	public AttendanceDataDO select(String id) {
		return attendanceDataMapper.selectByPrimaryKey(id);
	}


	@Override
	public PageInfo<AttendanceDataDO> listPage(IPageRequestModel pageRequestModel, LocalDateTime beginTime, LocalDateTime endTime, String organizeId,String nameValue) {
		Example example = new Example(AttendanceDataDO.class);
		example.createCriteria().andEqualTo("organizeId", organizeId).andBetween("recordDatetime", beginTime, endTime);
		PageInfo<AttendanceDataDO> pageAttendanceData = PageUtil.selectPageList(pageRequestModel,
				() -> attendanceDataMapper.selectByExample(example));
		return pageAttendanceData;
	}

	@Override
	public List<AttendanceDataDO> list(LocalDateTime beginTime, LocalDateTime endTime, String organizeId) {
		Example example = new Example(AttendanceDataDO.class);

		example.createCriteria()
				.andEqualTo("organizeId", organizeId)
				.andBetween("recordDatetime", beginTime, endTime);
		return attendanceDataMapper.selectByExample(example);
	}


}

