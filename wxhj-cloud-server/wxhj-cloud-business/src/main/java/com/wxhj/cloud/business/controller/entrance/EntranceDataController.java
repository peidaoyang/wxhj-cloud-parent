/**
 * @className EntranceDataController.java
 * @author jwl
 * @date 2020年1月19日 上午9:11:04
 */
package com.wxhj.cloud.business.controller.entrance;

import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.dozer.DozerBeanMapper;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewEntranceDataDO;
import com.wxhj.cloud.business.service.ViewEntranceDataService;
import com.wxhj.cloud.business.vo.EntranceDataVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.FileUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.EntranceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListDetailEntranceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataExcelRequestDTO;

import io.swagger.annotations.ApiOperation;

/**
 * @className EntranceDataController.java
 * @author jwl
 * @date 2020年1月19日 上午9:11:04
 */
@RestController
@RequestMapping("/entranceData")
public class EntranceDataController implements EntranceDataClient {
	@Resource
	ViewEntranceDataService viewEntranceDataServie;
	@Resource
	FileStorageService fileStorageService;
	@Resource
	AccessedRemotelyService accessedRemotelyService;
	@Resource
	DozerBeanMapper dozerBeanMapper;
	@Resource
	MessageSource messageSource;

	@SuppressWarnings("unchecked")
	@ApiOperation("查询门禁明细报表")
	@PostMapping("/listDetailEntranceData")
	@Override
	public WebApiReturnResultModel listDetailEntranceData(
			@Validated @RequestBody ListDetailEntranceDataRequestDTO listDetaileEntranceData) {

		PageInfo<ViewEntranceDataDO> listEntranceData = viewEntranceDataServie.listPage(listDetaileEntranceData,
				listDetaileEntranceData.getNameValue(), listDetaileEntranceData.getOrganizeId(),
				listDetaileEntranceData.getBeginTime(), listDetaileEntranceData.getEndTime());
		List<EntranceDataVO> entranceDataList = listEntranceData.getList().stream()
				.map(q -> dozerBeanMapper.map(q, EntranceDataVO.class)).collect(Collectors.toList());
		//
		try {
			entranceDataList = (List<EntranceDataVO>) accessedRemotelyService
					.accessedOrganizeSceneList(entranceDataList);
		} catch (WuXiHuaJieFeignError e) {
			// TODO Auto-generated catch block
			return e.getWebApiReturnResultModel();
		}

		PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
				.initPageResponseModel(listEntranceData, entranceDataList, new PageDefResponseModel());
		return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
	}

	@ApiOperation("导出门禁明细报表")
	@PostMapping("/listDetailEntranceDataExcel")
	@Override
	public WebApiReturnResultModel listDetailEntranceDataExcel(
			@Validated @RequestBody ListEntranceDataExcelRequestDTO listEntranceDataExcalRequest) {
		Locale locale = new Locale(listEntranceDataExcalRequest.getLanguage());

		List<ViewEntranceDataDO> listEntranceData = viewEntranceDataServie.list(
				listEntranceDataExcalRequest.getNameValue(), listEntranceDataExcalRequest.getOrganizeId(),
				listEntranceDataExcalRequest.getBeginTime(), listEntranceDataExcalRequest.getEndTime());
		List<EntranceDataVO> entranceDataList = listEntranceData.stream()
				.map(q -> dozerBeanMapper.map(q, EntranceDataVO.class)).collect(Collectors.toList());
		try {
			entranceDataList = (List<EntranceDataVO>) accessedRemotelyService
					.accessedOrganizeSceneList(entranceDataList);
		} catch (WuXiHuaJieFeignError e1) {
			// TODO Auto-generated catch block
			return e1.getWebApiReturnResultModel();
		}
		byte[] writeExcel;
		try {
			writeExcel = ExcelUtil.writeExcel(entranceDataList, EntranceDataVO.class, locale, messageSource);
		} catch (Exception e) {
			return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
		}
		String fileUuid = FileUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
		fileStorageService.saveFile(writeExcel, fileUuid);

		return WebApiReturnResultModel.ofSuccess(fileUuid);
	}
}
