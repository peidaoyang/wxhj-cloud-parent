package com.wxhj.cloud.account.controller;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.dozer.DozerBeanMapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.account.domain.view.ViewRechargeAccountDO;
import com.wxhj.cloud.account.domain.view.ViewRechargeSummaryDO;
import com.wxhj.cloud.account.dto.response.AppRechargeInfoResponseDTO;
import com.wxhj.cloud.account.service.RechargeInfoService;
import com.wxhj.cloud.account.service.ViewRechargeAccountService;
import com.wxhj.cloud.account.service.ViewRechargeSummaryService;
import com.wxhj.cloud.account.vo.RechargeExcelVO;
import com.wxhj.cloud.account.vo.ViewRechargeSummaryVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.FileUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.RechargeClient;
import com.wxhj.cloud.feignClient.account.request.AppRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeExcelRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.ListRechargeInfoVO;

import io.swagger.annotations.ApiOperation;

/**
 * @ClassName: RechargeController.java
 * @author: cya
 * @Date: 2020年2月1日 下午1:05:34
 */
@RestController
@RequestMapping("/rechargeInfo")
public class RechargeInfoController implements RechargeClient {
	@Resource
	RechargeInfoService rechargeInfoService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	MessageSource messageSource;
	@Resource
	FileStorageService fileStorageService;
	@Resource
	HttpServletRequest request;
	@Resource
	ViewRechargeSummaryService viewRechargeSummaryService;
	@Resource
	ViewRechargeAccountService viewRechargeAccountService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;

	@PostMapping("/listRechargeInfo")
	@ApiOperation("分页查询充值信息")
	@Override
	public WebApiReturnResultModel listRechargeInfo(
			@RequestBody @Validated ListRechargeInfoRequestDTO listRechargeInfo) {
		PageInfo<RechargeInfoDO> rechargeList = rechargeInfoService.listRechargeInfo(listRechargeInfo,
				listRechargeInfo.getNameValue(), listRechargeInfo.getType(), listRechargeInfo.getPayType(),
				listRechargeInfo.getStartTime(), listRechargeInfo.getEndTime(), listRechargeInfo.getOrganizeId());

		List<ListRechargeInfoVO> voList = rechargeList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, ListRechargeInfoVO.class)).collect(Collectors.toList());
		try {
			voList = (List<ListRechargeInfoVO>) accessedRemotelyService.accessedOrganizeUserList(voList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}
		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(rechargeList,
				voList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@PostMapping("/rechargeExcel")
	@ApiOperation("充值信息报表导出")
	@Override
	public WebApiReturnResultModel rechargeExcel(@RequestBody @Validated RechargeExcelRequestDTO rechargeExcel) {
		List<ViewRechargeAccountDO> rechargeInfoList = viewRechargeAccountService.select(rechargeExcel.getNameValue(),
				rechargeExcel.getType(), rechargeExcel.getPayType(), rechargeExcel.getStartTime(),
				rechargeExcel.getEndTime(), rechargeExcel.getOrganizeId());

		if (rechargeInfoList.size() == 0) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.NO_FILE);
		}
		List<RechargeExcelVO> rechargeExcelList = rechargeInfoList.stream()
				.map(q -> dozerBeanMapper.map(q, RechargeExcelVO.class)).collect(Collectors.toList());

		try {
			rechargeExcelList = (List<RechargeExcelVO>) accessedRemotelyService
					.accessedOrganizeUserList(rechargeExcelList);
		} catch (WuXiHuaJieFeignError e1) {
			// TODO Auto-generated catch block
			return e1.getWebApiReturnResultModel();
		}

		Locale locale = new Locale(rechargeExcel.getLanguage());
		byte[] writeExcel;
		try {
			writeExcel = ExcelUtil.writeExcel(rechargeExcelList, RechargeExcelVO.class, locale, messageSource);
		} catch (Exception e) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		String fileUuid = FileUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
		fileStorageService.saveFile(writeExcel, fileUuid);

		return WebApiReturnResultModel.ofSuccess(fileUuid);
	}

	@PostMapping("/appRechargeInfo")
	@ApiOperation("app充值信息查询")
	@Override
	public WebApiReturnResultModel appRechargeInfo(@RequestBody @Validated AppRechargeInfoRequestDTO appRechargeInfo) {
		PageInfo<RechargeInfoDO> rechargeList = rechargeInfoService.listRechargeInfo(appRechargeInfo,
				appRechargeInfo.getStartTime(), appRechargeInfo.getEndTime(), appRechargeInfo.getAccountId());

		List<AppRechargeInfoResponseDTO> appRechargeList = rechargeList.getList().stream()
				.map(q -> dozerBeanMapper.map(q, AppRechargeInfoResponseDTO.class)).collect(Collectors.toList());

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(rechargeList,
				appRechargeList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

//	@PostMapping("/rechargeSummary")
//	@ApiOperation("充值汇总报表")
//	@Override
//	public WebApiReturnResultModel rechargeSummary(@RequestBody @Validated RechargeSummaryRequestDTO rechargeSummary) {
//		PageInfo<ViewRechargeSummaryDO> list = viewRechargeSummaryService.listByDatePage(rechargeSummary,
//				rechargeSummary.getBeginTime(), rechargeSummary.getEndTime());
//
//		List<ViewRechargeSummaryVO> voList = list.getList().stream()
//				.map(q -> dozerBeanMapper.map(q, ViewRechargeSummaryVO.class)).collect(Collectors.toList());
//		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(list, voList,
//				new PageDefResponseModel());
//		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
//	}
}