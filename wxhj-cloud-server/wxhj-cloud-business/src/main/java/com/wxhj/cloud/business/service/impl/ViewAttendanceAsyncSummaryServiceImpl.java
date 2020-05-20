///** 
// * @fileName: ViewAttendanceAsyncSummaryServiceImpl.java  
// * @author: pjf
// * @date: 2020年2月20日 下午4:12:48 
// */
//
//package com.wxhj.cloud.business.service.impl;
//
//
//
//import java.util.List;
//
//import javax.annotation.Resource;
//
//import org.springframework.stereotype.Service;
//
//import com.wxhj.cloud.business.domain.view.ViewAttendanceAsyncSummaryDO;
//import com.wxhj.cloud.business.mapper.view.ViewAttendanceAsyncSummaryMapper;
//import com.wxhj.cloud.business.service.ViewAttendanceAsyncSummaryService;
//
///**
// * @className ViewAttendanceAsyncSummaryServiceImpl.java
// * @author pjf
// * @date 2020年2月20日 下午4:12:48   
//*/
//@Service
//public class ViewAttendanceAsyncSummaryServiceImpl implements ViewAttendanceAsyncSummaryService {
//	@Resource
//	ViewAttendanceAsyncSummaryServicemaryMapper viewAttendanceAsyncSummaryMapper;
//
//	@Override
//	public List<ViewAttendanceAsyncSummaryDO> listByDatetimeAndOrganize(Date startDate, Date endDate,
//			String organizeId) {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//		return viewAttendanceAsyncSummaryMapper.listByDatetimeAndOrganize(simpleDateFormat.format(startDate),
//				simpleDateFormat.format(endDate), organizeId);
//	}
//
//	@Override
//	public List<ViewAttendanceAsyncSummaryDO> listByDatetimeAndAccount(Date startDate, Date endDate, String accountId) {
//		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
//		return viewAttendanceAsyncSummaryMapper.listByDatetimeAndAccount(simpleDateFormat.format(startDate),
//				simpleDateFormat.format(endDate), accountId);
//	}
//
//}
