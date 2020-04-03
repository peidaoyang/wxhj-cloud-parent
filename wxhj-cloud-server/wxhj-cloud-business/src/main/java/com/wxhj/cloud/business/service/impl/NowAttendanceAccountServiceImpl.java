/** 
 * @fileName: NowAttendanceAccountServiceImpl.java  
 * @author: pjf
 * @date: 2019年12月27日 上午10:24:54 
 */

package com.wxhj.cloud.business.service.impl;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.business.mapper.NowAttendanceAccountMapper;
import com.wxhj.cloud.business.service.NowAttendanceAccountService;

/**
 * @className NowAttendanceAccountServiceImpl.java
 * @author pjf
 * @date 2019年12月27日 上午10:24:54
 */

@Service
public class NowAttendanceAccountServiceImpl implements NowAttendanceAccountService {
	@Resource
	NowAttendanceAccountMapper nowAttendanceAccountMapper;

	// @Transactional
	@Override
	public void insertDef() {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(new Date());
		calendar.add(Calendar.DATE, 1);
		nowAttendanceAccountMapper.inital(simpleDateFormat.format(calendar.getTime()));
	}
}
