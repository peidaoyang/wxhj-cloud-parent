package com.wxhj.cloud.account.controller;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.wxhj.cloud.account.domain.AccountCardInfoDO;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.domain.RechargeInfoDO;
import com.wxhj.cloud.account.domain.RefundDO;
import com.wxhj.cloud.account.domain.view.ViewRechargeAccountDO;
import com.wxhj.cloud.account.service.AccountCardInfoService;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.RechargeInfoService;
import com.wxhj.cloud.account.service.RefundService;
import com.wxhj.cloud.account.service.ViewRechargeAccountService;
import com.wxhj.cloud.account.vo.RechargeExcelVO;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.component.service.FileStorageService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.ExcelUtil;
import com.wxhj.cloud.core.utils.ZipUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.RechargeClient;
import com.wxhj.cloud.feignClient.account.request.AppRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListRechargeInfoRequestDTO;
import com.wxhj.cloud.feignClient.account.request.PersonRechargeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RechargeExcelRequestDTO;
import com.wxhj.cloud.feignClient.account.request.RefundRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.AppRechargeInfoVO;
import com.wxhj.cloud.feignClient.account.vo.ListRechargeInfoVO;
import com.wxhj.cloud.feignClient.account.vo.PersonRechargeVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.context.MessageSource;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Locale;
import java.util.stream.Collectors;

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
    Mapper dozerBeanMapper;
    @Resource
    MessageSource messageSource;
    @Resource
    FileStorageService fileStorageService;
    @Resource
    ViewRechargeAccountService viewRechargeAccountService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;
    @Resource
    AccountInfoService accountInfoService;
    @Resource
    AccountCardInfoService accountCardInfoService;
    @Resource
    RefundService refundService;

    @PostMapping("/listRechargeInfo")
    @ApiOperation("分页查询充值信息")
    @Override
    public WebApiReturnResultModel listRechargeInfo(
            @RequestBody @Validated ListRechargeInfoRequestDTO listRechargeInfo) {
        PageInfo<RechargeInfoDO> rechargeList = rechargeInfoService.listRechargeInfo(listRechargeInfo,
                listRechargeInfo.getNameValue(), listRechargeInfo.getType(), listRechargeInfo.getPayType(),
                listRechargeInfo.getStartTime().atStartOfDay(),
                listRechargeInfo.getEndTime().atStartOfDay(), listRechargeInfo.getOrganizeId());

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
                rechargeExcel.getType(), rechargeExcel.getPayType(),
                rechargeExcel.getStartTime().atStartOfDay(),
                rechargeExcel.getEndTime().atStartOfDay(),
                rechargeExcel.getOrganizeId());

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
        String fileUuid = ZipUtil.generateFile(ExcelUtil.OFFICE_EXCEL_XLSX);
        fileStorageService.saveFile(writeExcel, fileUuid);

        return WebApiReturnResultModel.ofSuccess(fileUuid);
    }

    @PostMapping("/appRechargeInfo")
    @ApiOperation("app充值信息查询")
    @Override
    public WebApiReturnResultModel appRechargeInfo(@RequestBody @Validated AppRechargeInfoRequestDTO appRechargeInfo) {
        PageInfo<RechargeInfoDO> rechargeList = rechargeInfoService.listRechargeInfo(appRechargeInfo,
                appRechargeInfo.getStartTime(), appRechargeInfo.getEndTime(), appRechargeInfo.getAccountId(), appRechargeInfo.getCardType());

        List<AppRechargeInfoVO> appRechargeList = rechargeList.getList().stream()
                .map(q -> dozerBeanMapper.map(q, AppRechargeInfoVO.class)).collect(Collectors.toList());

        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(rechargeList,
                appRechargeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("个人充值信息查询")
    @PostMapping("/personRecharge")
    @Override
    public WebApiReturnResultModel personRecharge(@RequestBody @Validated PersonRechargeRequestDTO personRecharge) {
        personRecharge.format();
        PageInfo<RechargeInfoDO> rechargeList =
                rechargeInfoService.listRechargeInfo(personRecharge,
                        personRecharge.getStartTime().atStartOfDay(),
                        personRecharge.getEndTime().atStartOfDay(), personRecharge.getAccountId(), personRecharge.getCardType());

        List<PersonRechargeVO> appRechargeList = rechargeList.getList().stream()
                .map(q -> dozerBeanMapper.map(q, PersonRechargeVO.class)).collect(Collectors.toList());

        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(rechargeList,
                appRechargeList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @PostMapping("/refund")
    @ApiOperation("充值退款")
    @Transactional
    @Override
    public WebApiReturnResultModel refund(@RequestBody @Validated RefundRequestDTO refundRequest) {
        RefundDO refund = dozerBeanMapper.map(refundRequest, RefundDO.class);
        AccountInfoDO accountInfo = accountInfoService.selectByAccountId(refund.getAccountId());
        if (accountInfo.getIsFrozen() == 1) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_FROZEN);
        } else if (accountInfo.getAccountBalance() - refund.getAmount() < 0) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.BALANCE_NOT_ENOUGH);
        }
        // 插入退款
        refundService.insert(refund);
        // 账户总信息修改
        accountInfoService.revoke(accountInfo.getAccountBalance(), -refund.getAmount(), accountInfo.getAccountId());
        // 账户卡信息修改
        AccountCardInfoDO accountCardInfo = accountCardInfoService.selectByAccountIdAndCardType(refund.getAccountId(), refundRequest.getCardType());
        if (accountCardInfo == null) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_NO_CARD);
        }
        accountCardInfo.setBalance(accountCardInfo.getBalance() - refund.getAmount());
        accountCardInfoService.update(accountCardInfo);
        // 充值信息修改退款状态
        rechargeInfoService.revoke(refundRequest.getRefundId(), 1);
        return WebApiReturnResultModel.ofSuccess();
    }

}
