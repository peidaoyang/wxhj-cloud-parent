/**
 * @className EntranceDataController.java
 * @author jwl
 * @date 2020年1月19日 上午9:11:04
 */
package com.wxhj.cloud.business.controller.entrance;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.business.domain.view.ViewEntranceDataDO;
import com.wxhj.cloud.business.service.EntranceDataService;
import com.wxhj.cloud.business.service.ViewEntranceDataService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.DateFormat;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.ZipUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.EntranceDataClient;
import com.wxhj.cloud.feignClient.business.request.ListDetailEntranceDataRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataByAccountRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListEntranceDataExcelRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.EntranceDataVO;
import com.wxhj.cloud.feignClient.business.vo.ListEntranceDataByAccountVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.redis.annotation.LogAnnotationController;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.MessageSource;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

/**
 * @author jwl
 * @className EntranceDataController.java
 * @date 2020年1月19日 上午9:11:04
 */
@RestController
@RequestMapping("/entranceData")
@LogAnnotationController
public class EntranceDataController implements EntranceDataClient {
    @Resource
    ViewEntranceDataService viewEntranceDataServie;
    @Resource
    EntranceDataService entranceDataService;
    @Resource
    FileStorageService fileStorageService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    MessageSource messageSource;

    @SuppressWarnings("unchecked")
    @ApiOperation("查询门禁明细报表")
    @PostMapping("/listDetailEntranceData")
    @Override
    public WebApiReturnResultModel listDetailEntranceData(
            @Validated @RequestBody ListDetailEntranceDataRequestDTO listDetaileEntranceData) {

        PageInfo<ViewEntranceDataDO> listEntranceData = viewEntranceDataServie.listPage(listDetaileEntranceData,
                listDetaileEntranceData.getOrganizeId(),
                listDetaileEntranceData.getBeginTime().atStartOfDay(),
                listDetaileEntranceData.getEndTime().atStartOfDay(),
                listDetaileEntranceData.getNameValue());
        List<EntranceDataVO> entranceDataList = listEntranceData.getList().stream()
                .map(q -> dozerBeanMapper.map(q, EntranceDataVO.class)).collect(Collectors.toList());

        entranceDataList.forEach(q -> {
            q.setAccessDate(DateFormat.stringToDate(DateFormat.getStringDate(q.getAccessDate(),"yyyy-MM-dd")
                    +" "+DateFormat.minute2HourMinute(q.getAccessTime()),"yyyy-MM-dd HH:mm:ss"));
        });
        try {
            entranceDataList = (List<EntranceDataVO>) accessedRemotelyService.accessedOrganizeSceneList(entranceDataList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
                .initPageResponseModel(listEntranceData, entranceDataList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation(value = "根据账户id查询门禁明细报表", response = ListEntranceDataByAccountVO.class)
    @PostMapping("/listEntranceDataByAccount")
    @Override
    public WebApiReturnResultModel listEntranceDataByAccount(@RequestBody @Validated ListEntranceDataByAccountRequestDTO listEntranceDataByAccount) {
        PageInfo<ViewEntranceDataDO> listEntranceData =
                viewEntranceDataServie.listPageByAccount(
                        listEntranceDataByAccount,
                        listEntranceDataByAccount.getBeginTime().atStartOfDay(),
                        listEntranceDataByAccount.getEndTime().atStartOfDay(),
                        listEntranceDataByAccount.getAccountId());
        List<ListEntranceDataByAccountVO> listEntranceVO = listEntranceData.getList().stream().map(q -> dozerBeanMapper.map(q, ListEntranceDataByAccountVO.class)).collect(Collectors.toList());
        try {
            listEntranceVO = (List<ListEntranceDataByAccountVO>) accessedRemotelyService.accessedOrganizeSceneList(listEntranceVO);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
                .initPageResponseModel(listEntranceData, listEntranceVO, new PageDefResponseModel());
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

        byte[] writeExcel;
        try {
            entranceDataList = (List<EntranceDataVO>) accessedRemotelyService.accessedOrganizeSceneList(entranceDataList);
            writeExcel = ExcelUtil.writeExcel(entranceDataList, EntranceDataVO.class, locale, messageSource);
        } catch (Exception e) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
        fileStorageService.saveFile(writeExcel, fileUuid);

        return WebApiReturnResultModel.ofSuccess(fileUuid);
    }

    @ApiOperation("获取当日门禁记录数量")
    @PostMapping("/totayEntrance")
    @Override
    public WebApiReturnResultModel totayEntrance(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {

        return WebApiReturnResultModel.ofSuccess(
                entranceDataService.listCount(commonIdRequest.getId(),
                        LocalDate.now().atStartOfDay()));
    }


}
