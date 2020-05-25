package com.wxhj.cloud.account.controller;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.account.domain.AccountInfoDO;
import com.wxhj.cloud.account.service.AccountInfoService;
import com.wxhj.cloud.account.service.AccountTypeService;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.feignClient.account.AccountTypeClient;
import com.wxhj.cloud.feignClient.account.request.AccountByOtherCodeAndTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.request.ListByOrgTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.AccountByIdAndTypeVO;
import com.wxhj.cloud.feignClient.account.vo.ListByOrgTypeVO;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author cya
 * @Date 2020/5/13
 * @Version V1.0
 **/
@RestController
@RequestMapping("/accountType")
public class AccountTypeController implements AccountTypeClient {
    @Resource
    AccountTypeService accountTypeService;
    @Resource
    AccountInfoService accountInfoService;

    @Resource
    Mapper dozerBeanMapper;

    @ApiOperation("根据组织类型获取人员类型列表")
    @PostMapping("/listByOrgType")
    @Override
    public WebApiReturnResultModel listByOrgType(@RequestBody @Validated ListByOrgTypeRequestDTO listByOrgType){
        List<ListByOrgTypeVO> voList = accountTypeService.listByOrgType(listByOrgType.getOrgType()).stream()
                .map(q -> dozerBeanMapper.map(q, ListByOrgTypeVO.class)).collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(voList);
    }

    @ApiOperation("根据账户类型和账户id查询账户信息")
    @PostMapping("/accountByOtherCodeAndType")
    public WebApiReturnResultModel accountByOtherCodeAndType(@RequestBody @Validated AccountByOtherCodeAndTypeRequestDTO accountByOtherCodeAndType){
        AccountInfoDO accountInfoDO = accountInfoService.selectByTypeAndOtherCode(accountByOtherCodeAndType.getTypeList(),accountByOtherCodeAndType.getOtherCode(),accountByOtherCodeAndType.getOrganizeId());
        if(accountInfoDO == null){return WebApiReturnResultModel.ofSuccess();}
        AccountByIdAndTypeVO accountByIdAndTypeVO = dozerBeanMapper.map(accountInfoDO, AccountByIdAndTypeVO.class);
        return WebApiReturnResultModel.ofSuccess(accountByIdAndTypeVO);
    }
}
