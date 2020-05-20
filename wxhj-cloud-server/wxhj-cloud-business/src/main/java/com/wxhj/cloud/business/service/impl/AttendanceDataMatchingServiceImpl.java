/** 
 * @fileName: AttendanceDataMatchingServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月23日 上午9:23:53 
 */

package com.wxhj.cloud.business.service.impl;


import java.time.LocalDate;
import java.util.List;

import javax.annotation.Resource;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.core.model.pagination.IPageRequestModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.wxhj.cloud.business.domain.AttendanceDataMatchingDO;
import com.wxhj.cloud.business.mapper.AttendanceDataMatchingMapper;
import com.wxhj.cloud.business.service.AttendanceDataMatchingService;
import tk.mybatis.mapper.entity.Example;


/**
 * @className AttendanceDataMatchingServiceImpl.java
 * @author pjf
 * @date 2019年12月23日 上午9:23:53   
*/
/**
 * @className AttendanceDataMatchingServiceImpl.java
 * @author pjf
 * @date 2019年12月23日 上午9:23:53
 */
@Service
public class AttendanceDataMatchingServiceImpl implements AttendanceDataMatchingService {
	@Resource
	AttendanceDataMatchingMapper attendanceDataMatchingMapper;

	@Override
	public PageInfo<AttendanceDataMatchingDO> listPage(IPageRequestModel pageRequestModel, LocalDate matchingDate, String accountId) {
		Example example = new Example(AttendanceDataMatchingDO.class);
		example.createCriteria().andEqualTo("accountId", accountId)
				.andEqualTo("matchingDate",matchingDate);
		PageInfo<AttendanceDataMatchingDO> pageList = PageUtil.selectPageList(pageRequestModel,() -> attendanceDataMatchingMapper.selectByExample(example));
		return pageList;
	}

	@Override
	public List<AttendanceDataMatchingDO> list(AttendanceDataMatchingDO attendanceDataMatching) {
		return attendanceDataMatchingMapper.select(attendanceDataMatching);

	}
	
	@Transactional
	@Override
	public void insert(AttendanceDataMatchingDO attendanceDataMatching) {
		attendanceDataMatchingMapper.insert(attendanceDataMatching);
	}

	@Override
	public void deleteByOrderNumber(String orderNumber) {
		attendanceDataMatchingMapper.deleteByPrimaryKey(orderNumber);
	}
}
