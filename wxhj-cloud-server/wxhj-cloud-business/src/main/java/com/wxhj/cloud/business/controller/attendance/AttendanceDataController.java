/**
 * @className AttendanceDataController.java
 * @author jwl
 * @date 2019年12月23日 下午4:14:51
 */
package com.wxhj.cloud.business.controller.attendance;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.AttendanceDataDO;
import com.wxhj.cloud.business.domain.AttendanceDataMatchingDO;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryDO;
import com.wxhj.cloud.business.domain.view.ViewAttendanceSummaryMatchingFinalDO;
import com.wxhj.cloud.business.runnable.SummaryAttendanceRunnable;
import com.wxhj.cloud.business.service.AttendanceDataMatchingService;
import com.wxhj.cloud.business.service.AttendanceDataService;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryMatchingFinalService;
import com.wxhj.cloud.business.service.ViewAttendanceSummaryService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.SpringUtil;
import com.wxhj.cloud.core.utils.ZipUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.AttendanceDataClient;
import com.wxhj.cloud.feignClient.business.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.business.request.*;
import com.wxhj.cloud.feignClient.business.vo.AttendanceDataVO;
import com.wxhj.cloud.feignClient.business.vo.ListEntranceDataByAccountVO;
import com.wxhj.cloud.feignClient.business.vo.MatchAttendanceDataByAccountVO;
import com.wxhj.cloud.feignClient.business.vo.ViewAccountAttendanceMatchingFinalVO;
import com.wxhj.cloud.feignClient.business.vo.ViewAttendanceSummaryMatchingFinalVO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author jwl
 * @className AttendanceDataController.java
 * @date 2019年12月23日 下午4:14:51
 */
///预留修改内容
@Api("考勤报表")
@RestController
@RequestMapping("/attendanceData")
public class AttendanceDataController implements AttendanceDataClient {

    @Resource
    MessageSource messageSource;

    @Resource
    ViewAttendanceSummaryService viewAttendanceSummaryService;
    //	@Resource
//	ViewAttendanceAsyncSummaryService viewAttendanceAsyncSummaryService;
	@Resource
	Mapper dozerBeanMapper;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	FileStorageService fileStorageService;
	@Resource
	ViewAttendanceSummaryMatchingFinalService viewAttendanceSummaryMatchingFinalService;
	@Resource
	AttendanceDataService attendanceDataService;
	@Resource
	AttendanceDataMatchingService attendanceDataMatchingService;


    @ApiOperation("明细报表")
    @PostMapping("/listDayAttendanceData")
    @Override
    public WebApiReturnResultModel listDayAttendanceData(
            @Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
        PageInfo<AttendanceDataDO> listPage = attendanceDataService.listPage(
                listAttendanceData, listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(),
                listAttendanceData.getOrganizeId(), listAttendanceData.getNameValue());

        List<AttendanceDataVO> responseList = listPage.getList().stream().map(q -> dozerBeanMapper.map(q, AttendanceDataVO.class)).collect(Collectors.toList());
        try {
            responseList = (List<AttendanceDataVO>) accessedRemotelyService.accessDeviceRecordList(responseList);
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
                dayAttendanceDataExcel.getBeginTime(), dayAttendanceDataExcel.getEndTime(), dayAttendanceDataExcel.getOrganizeId());

        List<AttendanceDataVO> voList = list.stream().map(q -> dozerBeanMapper.map(q, AttendanceDataVO.class)).collect(Collectors.toList());

        byte[] writeExcel;
        try {
            voList = (List<AttendanceDataVO>) accessedRemotelyService.accessDeviceRecordList(voList);
            voList = voList.stream().filter(q -> q.getAccountName().contains(dayAttendanceDataExcel.getNameValue())).collect(Collectors.toList());

            writeExcel = ExcelUtil.writeExcel(voList, AttendanceDataVO.class, locale, messageSource);
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
    @ApiOperation(value = "考勤记录汇总报表", response = ViewAttendanceSummaryMatchingFinalVO.class)
    @PostMapping("/listDayAttendanceMatchingData")
    @Override
    public WebApiReturnResultModel listDayAttendanceMatchingData(
            @Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
        PageInfo<ViewAttendanceSummaryMatchingFinalDO> viewAttendanceSummaryMatchingList = viewAttendanceSummaryMatchingFinalService.listByOrganizePage(
                listAttendanceData, listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getOrganizeId(), listAttendanceData.getNameValue());

        List<ViewAttendanceSummaryMatchingFinalVO> viewAttendanceSummaryResponseList = viewAttendanceSummaryMatchingList.getList().stream()
                .map(q -> dozerBeanMapper.map(q, ViewAttendanceSummaryMatchingFinalVO.class)).collect(Collectors.toList());

        // 将分钟数转换成时间string显示
        viewAttendanceSummaryResponseList.forEach(q -> q.formatView());
        try {
            viewAttendanceSummaryResponseList = (List<ViewAttendanceSummaryMatchingFinalVO>)
                    accessedRemotelyService.accessedOrganizeList(viewAttendanceSummaryResponseList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }

        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(
                viewAttendanceSummaryMatchingList, viewAttendanceSummaryResponseList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation(value = "考勤记录人员汇总报表", response = ViewAccountAttendanceMatchingFinalVO.class)
    @PostMapping("/listAccountAttendanceMatchingData")
    @Override
    public WebApiReturnResultModel listAccountAttendanceMatchingData(
            @Validated @RequestBody ListDayAttendanceDataRequestDTO listAttendanceData) {
        List<ViewAttendanceSummaryMatchingFinalDO> viewAttendanceSummaryMatchingList = viewAttendanceSummaryMatchingFinalService.listByOrganizePageNoPage(
                listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getOrganizeId(), listAttendanceData.getNameValue());
        // 汇总人员考勤信息
        List<ViewAccountAttendanceMatchingFinalVO> viewAccountAttendanceVOs = viewAttendanceSummaryMatchingFinalService.gatherAccountAttendanceInfo(viewAttendanceSummaryMatchingList);
        try {
            viewAccountAttendanceVOs = (List<ViewAccountAttendanceMatchingFinalVO>)
                    accessedRemotelyService.accessedOrganizeList(viewAccountAttendanceVOs);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }

        Integer page = listAttendanceData.getPage();
        Integer rows = listAttendanceData.getRows();
        PageDefResponseModel pageDefResponseModel = PageUtil.formatListPageResponse(page, rows, viewAccountAttendanceVOs);
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @Resource
    SpringUtil springUtil;

    @ApiOperation(value = "刷新考勤记录汇总")
    @PostMapping("/refresh")
    public WebApiReturnResultModel refresh(
            @Validated @RequestBody GetAttendanceDaysDTO getAttendanceDays) {
        Date beginTime = getAttendanceDays.getBeginTime();
        if (beginTime == null) {
            beginTime = new Date();
        }
        beginTime = DateUtil.growDateIgnoreHMS(beginTime, 0);
        SummaryAttendanceRunnable summaryAttendanceRunnable = springUtil.getBean(SummaryAttendanceRunnable.class);
        summaryAttendanceRunnable.run(beginTime);
        return WebApiReturnResultModel.ofSuccess();
    }


    @SuppressWarnings("unchecked")
//	@ApiOperation("汇总报表")
//	@PostMapping("/listMonthAttendanceData")
//	@Override
//	public WebApiReturnResultModel listMonthAttendanceData(
//			@Validated @RequestBody ListMonthAttendanceDataRequestDTO listAttendanceData) {
//		List<ViewAttendanceAsyncSummaryDO> pageInfoList = viewAttendanceAsyncSummaryService.listByDatetimeAndOrganize(
//				listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getOrganizeId());
//		PageInfo<ViewAttendanceAsyncSummaryDO> pageInfo = PageUtil.selectPageList(listAttendanceData,
//				() -> pageInfoList);
//		List<ListMonthAttendanceDataVO> pageInfoVOList = pageInfoList.stream()
//				.map(q -> dozerBeanMapper.map(q, ListMonthAttendanceDataVO.class)).collect(Collectors.toList());
//		try {
//			pageInfoVOList = (List<ListMonthAttendanceDataVO>) accessedRemotelyService
//					.accessedOrganizeList(pageInfoVOList);
//		} catch (WuXiHuaJieFeignError e) {
//			// TODO Auto-generated catch block
//			return e.getWebApiReturnResultModel();
//		}
//
//		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageInfo,
//				pageInfoVOList, new PageDefResponseModel());
//		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
//	}

    @ApiOperation("导出汇总报表")
    @PostMapping("/listMonthAttendanceDataExcel")
    @Override
    public WebApiReturnResultModel listMonthAttendanceDataExcel(
            @Validated @RequestBody ListMonthAttendanceDataExcelRequestDTO listAttendanceExcelData) {
//		List<ViewAttendanceAsyncSummaryDO> doList = viewAttendanceAsyncSummaryService.listByDatetimeAndOrganize(
//				listAttendanceExcelData.getBeginTime(), listAttendanceExcelData.getEndTime(),
//				listAttendanceExcelData.getOrganizeId());
//		List<ViewAttendanceAsyncSummaryVO> voList = doList.stream()
//				.map(q -> dozerBeanMapper.map(q, ViewAttendanceAsyncSummaryVO.class)).collect(Collectors.toList());
//
//		Locale locale = new Locale(listAttendanceExcelData.getLanguage());
//
//		byte[] writeExcel;
//		try {
//			writeExcel = ExcelUtil.writeExcel(voList, ViewAttendanceAsyncSummaryVO.class, locale, messageSource);
//		} catch (Exception e) {
//			return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
//		}
//		String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
//		fileStorageService.saveFile(writeExcel, fileUuid);
//
//		return WebApiReturnResultModel.ofSuccess(fileUuid);
        return null;
    }

    @ApiOperation("根据Appid获取汇总报表")
    @PostMapping("/listMonthAttendanceByAccountId")
    @Override
    public WebApiReturnResultModel listMonthAttendanceByAccountId(
            @Validated @RequestBody ListMonthAttendanceByAccountIdRequestDTO listAttendanceData) {
//		List<ViewAttendanceAsyncSummaryDO> pageInfoList = viewAttendanceAsyncSummaryService.listByDatetimeAndAccount(
//				listAttendanceData.getBeginTime(), listAttendanceData.getEndTime(), listAttendanceData.getAccountId());
//		PageInfo<ViewAttendanceAsyncSummaryDO> pageInfo = PageUtil.selectPageList(listAttendanceData,
//				() -> pageInfoList);
//		List<ListMonthAttendanceDataVO> pageInfoVOList = pageInfoList.stream()
//				.map(q -> dozerBeanMapper.map(q, ListMonthAttendanceDataVO.class)).collect(Collectors.toList());
//		try {
//			pageInfoVOList = (List<ListMonthAttendanceDataVO>) accessedRemotelyService
//					.accessedOrganizeList(pageInfoVOList);
//		} catch (WuXiHuaJieFeignError e) {
//			// TODO Auto-generated catch block
//			return e.getWebApiReturnResultModel();
//		}
//
//		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(pageInfo,
//				pageInfoVOList, new PageDefResponseModel());
//		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
        return null;
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

	@ApiOperation(value = "根据账户id获取打卡记录", response = MatchAttendanceDataByAccountVO.class)
	@PostMapping("/matchAttendanceDataByAccount")
	@Override
	public WebApiReturnResultModel matchAttendanceDataByAccount(@RequestBody @Validated MatchAttendanceDataByAccountRequestDTO matchAttendanceDataByAccount){
		PageInfo<AttendanceDataMatchingDO> matchingDOPageInfo = attendanceDataMatchingService.listPage(matchAttendanceDataByAccount,matchAttendanceDataByAccount.getAttendanceTime(),matchAttendanceDataByAccount.getAccountId());
		List<MatchAttendanceDataByAccountVO>  voList = matchingDOPageInfo.getList().stream().map(q-> dozerBeanMapper.map(q, MatchAttendanceDataByAccountVO.class)).collect(Collectors.toList());
		try {
			voList = (List<MatchAttendanceDataByAccountVO>) accessedRemotelyService.accessedOrganizeSceneList(voList);
		} catch (WuXiHuaJieFeignError e) {
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(matchingDOPageInfo, voList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

}