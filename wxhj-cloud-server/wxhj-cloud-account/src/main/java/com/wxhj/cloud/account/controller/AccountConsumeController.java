package com.wxhj.cloud.account.controller;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.domain.AccountConsumeDO;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.AccountRevokeDO;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.account.domain.view.ViewAccountConsumeDO;
import com.wxhj.cloud.account.domain.view.ViewConsumeSummaryAccountDO;
import com.wxhj.cloud.account.service.*;
import com.wxhj.cloud.account.vo.AccountConsumeDetailedExcelVO;
import com.wxhj.cloud.account.vo.ViewConsumeSummaryAccountVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.ZipUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AccountConsumeClient;
import com.wxhj.cloud.feignClient.account.request.*;
import com.wxhj.cloud.feignClient.account.response.TodayConsumeResponseDTO;
import com.wxhj.cloud.feignClient.account.vo.AccountConsumeVO;
import com.wxhj.cloud.feignClient.account.vo.PersonConsumeVO;
import com.wxhj.cloud.feignClient.account.vo.ViewConsumeSummaryVO;
import com.wxhj.cloud.feignClient.business.vo.AppConsumeInfoVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
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

@RestController
@RequestMapping("/accountConsume")
@Slf4j
public class AccountConsumeController implements AccountConsumeClient {
    @Resource
    ViewAccountConsumeService viewAccountConsumeService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    MessageSource messageSource;
    @Resource
    FileStorageService fileStorageService;
    @Resource
    ViewConsumeSummaryAccountService viewConsumeSummaryAccountService;
    @Resource
    AccountConsumeService accountConsumeService;
    @Resource
    RechargeInfoService rechargeInfoService;
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    AccountRevokeService accountRevokeService;
    @Resource
    AccountCardInfoService accountCardInfoService;

    @ApiOperation("查询消费数据明细")
    @PostMapping("/listConsumeDetail")
    @Override
    public WebApiReturnResultModel listConsumeDetail(
            @Validated @RequestBody ListConsumeDetailRequestDTO listConsumeDetail) {
        PageInfo<ViewAccountConsumeDO> listPage = viewAccountConsumeService.listPage(listConsumeDetail,
                listConsumeDetail.getOrganizeId(), listConsumeDetail.getNameValue(),
                listConsumeDetail.getBeginTime().atStartOfDay(),
                listConsumeDetail.getEndTime().atStartOfDay(), listConsumeDetail.getCardType());
//				CommUtil.dayDate(listConsumeDetail.getBeginTime()),
//				CommUtil.dayDate(listConsumeDetail.getEndTime()));

        List<AccountConsumeVO> accountConsumeList = listPage.getList().stream()
                .map(q -> dozerBeanMapper.map(q, AccountConsumeVO.class)).collect(Collectors.toList());
        try {
            accountConsumeList = (List<AccountConsumeVO>) accessedRemotelyService
                    .accessedOrganizeSceneList(accountConsumeList);
            accountConsumeList = (List<AccountConsumeVO>) accessedRemotelyService.accessCardNameList(accountConsumeList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage,
                accountConsumeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("导出消费明细报表")
    @PostMapping("/listConsumeDetailExcel")
    @Override
    public WebApiReturnResultModel listConsumeDetailExcel(
            @Validated @RequestBody ListConsumeDetailExcelRequestDTO listConsumeDetailExcelRequest) {
        Locale locale = new Locale(listConsumeDetailExcelRequest.getLanguage());

        List<ViewAccountConsumeDO> viewAccountConsumeList = viewAccountConsumeService.list(
                listConsumeDetailExcelRequest.getOrganizeId(), listConsumeDetailExcelRequest.getNameValue(),
                listConsumeDetailExcelRequest.getBeginTime().atStartOfDay(),
                listConsumeDetailExcelRequest.getEndTime().atStartOfDay());
//                CommUtil.dayDate(listConsumeDetailExcelRequest.getBeginTime()),
//                CommUtil.dayDate(listConsumeDetailExcelRequest.getEndTime()));

        List<AccountConsumeVO> accountConsumeList = viewAccountConsumeList.stream()
                .map(q -> dozerBeanMapper.map(q, AccountConsumeVO.class)).collect(Collectors.toList());
        try {
            accountConsumeList = (List<AccountConsumeVO>) accessedRemotelyService
                    .accessedOrganizeSceneList(accountConsumeList);
            accountConsumeList = (List<AccountConsumeVO>) accessedRemotelyService.accessCardNameList(accountConsumeList);
        } catch (WuXiHuaJieFeignError e1) {
            // TODO Auto-generated catch block
            return e1.getWebApiReturnResultModel();
        }
        List<AccountConsumeDetailedExcelVO> accountConsumeDetailedExcelList = accountConsumeList.stream()
                .map(q -> dozerBeanMapper.map(q, AccountConsumeDetailedExcelVO.class)).collect(Collectors.toList());
        byte[] writeExcel;
        try {
            writeExcel = ExcelUtil.writeExcel(accountConsumeDetailedExcelList, AccountConsumeDetailedExcelVO.class,
                    locale, messageSource);
        } catch (Exception e) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
        fileStorageService.saveFile(writeExcel, fileUuid);

        return WebApiReturnResultModel.ofSuccess(fileUuid);
    }

    @ApiOperation("消费信息汇总")
    @PostMapping("/listConsumeSummary")
    @Override
    public WebApiReturnResultModel listConsumeSummary(
            @Validated @RequestBody ListConsumeSummaryRequestDTO listConsumeSummary) {
        PageInfo<ViewConsumeSummaryAccountDO> listPage = viewConsumeSummaryAccountService.listPage(listConsumeSummary,
                listConsumeSummary.getOrganizeId(), listConsumeSummary.getNameValue(),
                listConsumeSummary.getBeginTime(), listConsumeSummary.getEndTime());
        List<ViewConsumeSummaryVO> accountConsumeList = listPage.getList().stream()
                .map(q -> dozerBeanMapper.map(q, ViewConsumeSummaryVO.class)).collect(Collectors.toList());
        try {
            accountConsumeList = (List<ViewConsumeSummaryVO>) accessedRemotelyService
                    .accessedOrganizeList(accountConsumeList);
        } catch (WuXiHuaJieFeignError e) {

            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage,
                accountConsumeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("消费信息导出")
    @PostMapping("/listConsumeSummaryExcel")
    public WebApiReturnResultModel listConsumeSummaryExcel(
            @Validated @RequestBody ListConsumeSummaryExcel listConsumeSummaryExcel) {
        Locale locale = new Locale(listConsumeSummaryExcel.getLanguage());

        List<ViewConsumeSummaryAccountDO> viewAccountConsumeSumList = viewConsumeSummaryAccountService.list(
                listConsumeSummaryExcel.getOrganizeId(), listConsumeSummaryExcel.getNameValue(),
                listConsumeSummaryExcel.getBeginTime(),
                listConsumeSummaryExcel.getEndTime());
//                CommUtil.dayDate(listConsumeSummaryExcel.getBeginTime()),
//                CommUtil.dayDate(listConsumeSummaryExcel.getEndTime()));

        List<ViewConsumeSummaryVO> accountConsumeSummaryList = viewAccountConsumeSumList.stream()
                .map(q -> dozerBeanMapper.map(q, ViewConsumeSummaryVO.class)).collect(Collectors.toList());
        try {
            accountConsumeSummaryList = (List<ViewConsumeSummaryVO>) accessedRemotelyService
                    .accessedOrganizeList(accountConsumeSummaryList);
        } catch (WuXiHuaJieFeignError e1) {
            // TODO Auto-generated catch block
            return e1.getWebApiReturnResultModel();
        }

        byte[] writeExcel;
        try {
            writeExcel = ExcelUtil.writeExcel(accountConsumeSummaryList, ViewConsumeSummaryVO.class, locale,
                    messageSource);
        } catch (Exception e) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.INTERNAL_SERVER_ERROR, e.getMessage());
        }
        String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
        fileStorageService.saveFile(writeExcel, fileUuid);

        return WebApiReturnResultModel.ofSuccess(fileUuid);
    }

    @ApiOperation("app消费汇总记录查询")
    @PostMapping("/appConsumeInfoSummary")
    @Override
    public WebApiReturnResultModel appConsumeInfoSummary(
            @RequestBody @Validated AppConsumeInfoSummaryRequestDTO appConsumeSummary) {
        PageInfo<ViewConsumeSummaryAccountDO> listPage = viewConsumeSummaryAccountService.listPage(appConsumeSummary,
                appConsumeSummary.getOrganizeId(), appConsumeSummary.getAccountId(),
                appConsumeSummary.getStartTime().toLocalDate(),
                appConsumeSummary.getEndTime().toLocalDate());
        List<ViewConsumeSummaryAccountVO> accountConsumeList = listPage.getList().stream()
                .map(q -> dozerBeanMapper.map(q, ViewConsumeSummaryAccountVO.class)).collect(Collectors.toList());
        try {
            accountConsumeList = (List<ViewConsumeSummaryAccountVO>) accessedRemotelyService
                    .accessedOrganizeList(accountConsumeList);
        } catch (WuXiHuaJieFeignError e) {
            // TODO Auto-generated catch block
            return e.getWebApiReturnResultModel();
        }

        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage,
                accountConsumeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation(value = "app消费记录查询", response = AppConsumeInfoVO.class)
    @PostMapping("/appConsumeInfo")
    @Override
    public WebApiReturnResultModel appConsumeInfo(@RequestBody @Validated AppConsumeInfoRequestDTO appConsumeInfo) {
        PageInfo<ViewAccountConsumeDO> listPage = viewAccountConsumeService.listByTimeAndAccountPage(appConsumeInfo,
                appConsumeInfo.getAccountId(),
                appConsumeInfo.getStartTime().atStartOfDay(),
                appConsumeInfo.getEndTime().atStartOfDay(), appConsumeInfo.getCardType());
        List<AppConsumeInfoVO> accountConsumeList = listPage.getList().stream().map(q -> dozerBeanMapper.map(q, AppConsumeInfoVO.class)).collect(Collectors.toList());
        try {
            accountConsumeList = (List<AppConsumeInfoVO>) accessedRemotelyService.accessedOrganizeList(accountConsumeList);
        } catch (WuXiHuaJieFeignError e) {
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage,
                accountConsumeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("个人消费记录查询")
    @PostMapping("/personConsume")
    @Override
    public WebApiReturnResultModel personConsume(@RequestBody @Validated PersonConsumeRequestDTO personConsumeRequest) {
        PageInfo<ViewAccountConsumeDO> listPage =
                viewAccountConsumeService.listByTimeAndAccountPage(
                        personConsumeRequest, personConsumeRequest.getAccountId(),
                        personConsumeRequest.getStartTime().atStartOfDay(),
                        personConsumeRequest.getEndTime().atStartOfDay(), personConsumeRequest.getCardType());

        List<PersonConsumeVO> personConsumeList = listPage.getList().stream().map(q -> dozerBeanMapper.map(q, PersonConsumeVO.class)).collect(Collectors.toList());
        try {
            personConsumeList = (List<PersonConsumeVO>) accessedRemotelyService.accessCardNameList(personConsumeList);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            log.error("类型转换失败");
            wuXiHuaJieFeignError.printStackTrace();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(listPage, personConsumeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }


    @ApiOperation("当日消费/充值 记录查询")
    @PostMapping("/todayConsume")
    @Override
    public WebApiReturnResultModel todayConsume(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        LocalDateTime date = LocalDate.now().atStartOfDay();
        List<AccountConsumeDO> consumelist = accountConsumeService.list(commonIdRequest.getId(), date);
        int todayConsumeCount = consumelist.size();
        Double todayConsumeMoney = 0.00;
        if (consumelist.size() > 0) {
            todayConsumeMoney = (consumelist.stream().map(q -> q.getConsumeMoney())
                    .reduce(Integer::sum).get()) / 100.00;
        }

        List<RechargeInfoDO> rechargeList = rechargeInfoService.list(commonIdRequest.getId(), date);
        int todayRechargeCount = rechargeList.size();
        Double todayRechargeMoney = 0.00;
        if (rechargeList.size() > 0) {
            todayRechargeMoney = (rechargeList.stream().map(q -> q.getAmount())
                    .reduce(Integer::sum).get()) / 100.00;
        }

        return WebApiReturnResultModel.ofSuccess(
                new TodayConsumeResponseDTO(todayConsumeMoney,
                        todayConsumeCount, todayRechargeMoney, todayRechargeCount));
    }

    @ApiOperation("消费撤销")
    @PostMapping("/accountRevoke")
    @Override
    @Transactional
    public WebApiReturnResultModel accountRevoke(@RequestBody @Validated AccountRevokeRequestDTO accountRevokeRequest) {
        AccountRevokeDO accountRevokeDO = dozerBeanMapper.map(accountRevokeRequest, AccountRevokeDO.class);
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(accountRevokeDO.getAccountId());
        if (accountInfo.getIsFrozen() == 1) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_FROZEN);
        }
        accountRevokeService.insert(accountRevokeDO);
        accountInfoService.revoke(
                accountInfo.getAccountBalance(),
                accountRevokeRequest.getConsumeMoney(),
                accountInfo.getAccountId());
        accountConsumeService.revoke(accountRevokeDO.getConsumeId(), 1);

        AccountCardInfoDO accountCardInfo = accountCardInfoService.selectByAccountIdAndCardType(accountRevokeRequest.getAccountId(),
                accountRevokeRequest.getCardType());
        if (accountCardInfo == null) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_NO_CARD);
        }
        accountCardInfoService.revoke(accountCardInfo, accountRevokeRequest.getConsumeMoney());
        return WebApiReturnResultModel.ofSuccess();
    }

}
