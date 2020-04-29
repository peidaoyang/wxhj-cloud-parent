/**
 * @className AttendanceDataController.java
 * @author jwl
 * @date 2019年12月23日 下午4:14:51
 */
package com.wxhj.cloud.business.controller.attendance;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.view.ViewAttendanceAsyncSummaryDO;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryDO;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO;
import com.wxhj.cloud.business.service.AttendanceDataService;
import com.wxhj.cloud.business.service.ViewAttendanceAsyncSummaryService;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryMatchingFinalService;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryService;
import com.wxhj.cloud.business.vo.ViewAttendanceAsyncSummaryVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.ZipUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.AttendanceDataClient;
import com.wxhj.cloud.feignClient.business.request.DayAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListDayAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListDayDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthAttendanceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListMonthDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.AttendanceDataVO;
import com.wxhj.cloud.feignClient.business.vo.ListDayAttendanceDataVO;
import com.wxhj.cloud.feignClient.business.vo.ListMonthAttendanceDataVO;
import com.wxhj.cloud.feignClient.business.vo.ViewAttendanceSummaryMatchingFinalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @className AttendanceDataController.java
 * @author jwl
 * @date 2019年12月23日 下午4:14:51
 */
///预留修改内容
@Api("考勤报表")
@RestController
@RequestMapping("/attendanceData")
public class AttendanceDataController implements AttendanceDataClient {
//	@Resource
//	AttendanceDataService attendanceDataService;
//	@Resource
//	CurrentAttendanceGroupService currentAttendanceGroupService;
//	@Resource
//	CurrentAttendanceDayService currentAttendanceDayService;
	@Resource
	MessageSource messageSource;

	@Resource
	HttpServletRequest request;
	@Resource
	ViewAttendanceSummaryService viewAttendanceSummaryService;
	@Resource
	ViewAttendanceAsyncSummaryService viewAttendanceAsyncSummaryService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	FileStorageService fileStorageService;
	@Resource
	ViewAttendanceSummaryMatchingFinalService viewAttendanceSummaryMatchingFinalService;
	@Resource
	AttendanceDataService attendanceDataService;

//	@SuppressWarnings("unchecked")
//	@ApiOperation("明细报表")
//	@PostMapping("/listDayAttendanceData")
//	@Override
//	public WebApiReturnResultModel listDayAttendanceData(
//			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
//		PageInfo<ViewAttendanceSummaryDO> viewAttendanceSummaryList = viewAttendanceSummaryService.listPage(
//				listAttendanceData, listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(),
//				listAttendanceData.getNameValue(), listAttendanceData.getOrganizeId());
//
//		List<ListDayAttendanceDataVO> viewAttendanceSummaryResponseList = viewAttendanceSummaryList.getList().stream()
//				.map(q -> dozerBeanMapper.map(q, ListDayAttendanceDataVO.class)).collect(Collectors.toList());
//
//		try {
//			viewAttendanceSummaryResponseList = (List<ListDayAttendanceDataVO>) accessedRemotelyService
//					.accessedOrganizeList(viewAttendanceSummaryResponseList);
//		} catch (WuXiHuaJieFeignError e) {
//			// TODO Auto-generated catch block
//			return e.getWebApiReturnResultModel();
//		}
//
//		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
//				viewAttendanceSummaryList, viewAttendanceSummaryResponseList, new PageDefResponseModel());
//		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
//	}

	@ApiOperation("明细报表")
	@PostMapping("/listDayAttendanceData")
	@Override
	public WebApiReturnResultModel listDayAttendanceData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
		PageInfo<AttendanceDataDO> listPage = attendanceDataService.listPage(
				listAttendanceData, listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(),
				listAttendanceData.getOrganizeId(),listAttendanceData.getNameValue());

		List<AttendanceDataVO> responseList = listPage.getList().stream().map(q -> dozerBeanMapper.map(q, AttendanceDataVO.class)).collect(Collectors.toList());
		try {
            responseList = (List<AttendanceDataVO>) accessedRemotelyService.accessDeviceRecordList(responseList);
			responseList = responseList.stream().filter(q -> q.getAccountName().contains(listAttendanceData.getNameValue())).collect(Collectors.toList());
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
                listPage, responseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("导出考勤明细报表")
	@PostMapping("/exportDayAttendanceDataExcel")
	@Override
	public WebApiReturnResultModel exportDayAttendanceDataExcel(
			@Validated @RequestBody DayAttendanceDataExcelRequestDTO dayAttendanceDataExcel) {
		Locale locale = new Locale(dayAttendanceDataExcel.getLanguage());
		List<AttendanceDataDO> list = attendanceDataService.list(
				dayAttendanceDataExcel.getBeginTime(), dayAttendanceDataExcel.getEndTime(),dayAttendanceDataExcel.getOrganizeId());

		List<AttendanceDataVO> voList = list.stream().map(q -> dozerBeanMapper.map(q, AttendanceDataVO.class)).collect(Collectors.toList());

		byte[] writeExcel;
		try {
			voList = (List<AttendanceDataVO>) accessedRemotelyService.accessDeviceRecordList(voList);
			voList = voList.stream().filter(q -> q.getAccountName().contains(dayAttendanceDataExcel.getNameValue())).collect(Collectors.toList());

			writeExcel = ExcelUtil.writeExcel(voList, AttendanceDataVO.class, locale,messageSource);
		} catch (Exception e) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
		fileStorageService.saveFile(writeExcel, fileUuid);

		return WebApiReturnResultModel.ofSuccess(fileUuid);

	}


//	@ApiOperation("导出考勤明细报表")
//	@PostMapping("/exportDayAttendanceDataExcel")
//	@Override
//	public WebApiReturnResultModel exportDayAttendanceDataExcel(
//			@Validated @RequestBody DayAttendanceDataExcelRequestDTO dayAttendanceDataExcel) {
//		Locale locale = new Locale(dayAttendanceDataExcel.getLanguage());
//		List<ViewAttendanceSummaryDO> viewAttendanceSummaryList = viewAttendanceSummaryService.list(
//				dayAttendanceDataExcel.getBeginTime(), dayAttendanceDataExcel.getEndTime(),
//				dayAttendanceDataExcel.getNameValue(), dayAttendanceDataExcel.getOrganizeId());
//		byte[] writeExcel;
//		try {
//			writeExcel = ExcelUtil.writeExcel(viewAttendanceSummaryList, ViewAttendanceSummaryDO.class, locale,
//					messageSource);
//		} catch (Exception e) {
//			return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
//		}
//		String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
//		fileStorageService.saveFile(writeExcel, fileUuid);
//
//		return WebApiReturnResultModel.ofSuccess(fileUuid);
//
//	}
//
//
	@ApiOperation("考勤记录汇总报表")
	@PostMapping("/listDayAttendanceMatchingData")
	@Override
	public WebApiReturnResultModel listDayAttendanceMatchingData(
			@Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
		PageInfo<ViewAttendanceSummaryMatchingFinalDO> viewAttendanceSummaryMatchingList = viewAttendanceSummaryMatchingFinalService.listByOrganizePage(
				listAttendanceData, listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getOrganizeId());

		List<ViewAttendanceSummaryMatchingFinalVO> viewAttendanceSummaryResponseList = viewAttendanceSummaryMatchingList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ViewAttendanceSummaryMatchingFinalVO.class)).collect(Collectors.toList());
		try {
			viewAttendanceSummaryResponseList = (List<ViewAttendanceSummaryMatchingFinalVO>)
					accessedRemotelyService
					.accessedOrganizeList(viewAttendanceSummaryResponseList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
				viewAttendanceSummaryMatchingList, viewAttendanceSummaryResponseList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}


	@SuppressWarnings("unchecked")
	@ApiOperation("汇总报表")
	@PostMapping("/listMonthAttendanceData")
	@Override
	public WebApiReturnResultModel listMonthAttendanceData(
			@Validated @RequestBody ListMonthAttendanceDataRequestDTO listAttendanceData) {
		List<ViewAttendanceAsyncSummaryDO> pageInfoList = viewAttendanceAsyncSummaryService.listByDatetimeAndOrganize(
				listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getOrganizeId());
		PageInfo<ViewAttendanceAsyncSummaryDO> pageInfo = PageUtil.selectPageList(listAttendanceData,
				() -> pageInfoList);
		List<ListMonthAttendanceDataVO> pageInfoVOList = pageInfoList.stream()
				.map(q -> dozerBeanMapper.map(q, ListMonthAttendanceDataVO.class)).collect(Collectors.toList());
		try {
			pageInfoVOList = (List<ListMonthAttendanceDataVO>) accessedRemotelyService
					.accessedOrganizeList(pageInfoVOList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageInfo,
				pageInfoVOList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("导出汇总报表")
	@PostMapping("/listMonthAttendanceDataExcel")
	@Override
	public WebApiReturnResultModel listMonthAttendanceDataExcel(
			@Validated @RequestBody ListMonthAttendanceDataExcelRequestDTO listAttendanceExcelData) {
		List<ViewAttendanceAsyncSummaryDO> doList = viewAttendanceAsyncSummaryService.listByDatetimeAndOrganize(
				listAttendanceExcelData.getBeginTime(), listAttendanceExcelData.getEndTime(),
				listAttendanceExcelData.getOrganizeId());
		List<ViewAttendanceAsyncSummaryVO> voList = doList.stream()
				.map(q -> dozerBeanMapper.map(q, ViewAttendanceAsyncSummaryVO.class)).collect(Collectors.toList());

		Locale locale = new Locale(listAttendanceExcelData.getLanguage());

		byte[] writeExcel;
		try {
			writeExcel = ExcelUtil.writeExcel(voList, ViewAttendanceAsyncSummaryVO.class, locale, messageSource);
		} catch (Exception e) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
		fileStorageService.saveFile(writeExcel, fileUuid);

		return WebApiReturnResultModel.ofSuccess(fileUuid);
	}

	@ApiOperation("根据Appid获取汇总报表")
	@PostMapping("/listMonthAttendanceByAccountId")
	@Override
	public WebApiReturnResultModel listMonthAttendanceByAccountId(
			@Validated @RequestBody ListMonthAttendanceByAccountIdRequestDTO listAttendanceData) {
		List<ViewAttendanceAsyncSummaryDO> pageInfoList = viewAttendanceAsyncSummaryService.listByDatetimeAndAccount(
				listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getAccountId());
		PageInfo<ViewAttendanceAsyncSummaryDO> pageInfo = PageUtil.selectPageList(listAttendanceData,
				() -> pageInfoList);
		List<ListMonthAttendanceDataVO> pageInfoVOList = pageInfoList.stream()
				.map(q -> dozerBeanMapper.map(q, ListMonthAttendanceDataVO.class)).collect(Collectors.toList());
		try {
			pageInfoVOList = (List<ListMonthAttendanceDataVO>) accessedRemotelyService
					.accessedOrganizeList(pageInfoVOList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageInfo,
				pageInfoVOList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation(value = "获取登录用户的汇总报表")
	@PostMapping("/listMonthDataByAccount")
	@Override
	public WebApiReturnResultModel listMonthDataByAccount(
			@Validated @RequestBody ListMonthDataByAccountRequestDTO listDataByAccount) {

		return WebApiReturnResultModel.ofSuccess();
	}

	@ApiOperation(value = "获取登录用户的明细报表")
	@PostMapping("/listDayDataByAccount")
	@Override
	public WebApiReturnResultModel listDayDataByAccount(
			@Validated @RequestBody ListDayDataByAccountRequestDTO listDataByAccount) {
		PageInfo<ViewAttendanceSummaryDO> viewAttendanceSummaryList = viewAttendanceSummaryService.listByAccountPage(
				listDataByAccount, listDataByAccount.getBeginTime(), listDataByAccount.getEndTime(),
				listDataByAccount.getAccountId());
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(viewAttendanceSummaryList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}
}