package com.wxhj.cloud.business.controller;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.business.service.OnBusinessService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.business.OnBusinessClient;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.business.dto.OnBusinessDTO;
import com.wxhj.cloud.feignClient.business.request.CheckOnBusinessRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListOnBusinessByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.OnBusinessVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 出差controller
 *
 * @author daxiong
 * @date 2020-04-07 14:29
 */
@RestController
@RequestMapping("/onBusiness")
@Api(tags = "出差管理")
public class OnBusinessController implements OnBusinessClient {
    @Resource
    AccountClient accountClient;
    @Resource
    OnBusinessService onBusinessService;
    @Resource
    Mapper dozerBeanMapper;

    @Override
    @PostMapping("/submitOnBusiness")
    @ApiOperation(value = "编辑出差记录")
    public WebApiReturnResultModel submitOnBusiness(@RequestBody @Validated OnBusinessDTO onBusiness) {
        OnBusinessDO onBusinessDO = dozerBeanMapper.map(onBusiness, OnBusinessDO.class);
        if(Strings.isNullOrEmpty(onBusinessDO.getAccountName())){
            //app端无accountName，需要从数据库查询到accountName
            WebApiReturnResultModel webApiReturnResultModel = accountClient.accountOne(new CommonIdRequestDTO(onBusinessDO.getAccountId()));
            try {
                AccountInfoVO accountInfoVO = FeignUtil.formatClass(webApiReturnResultModel,AccountInfoVO.class);
                onBusinessDO.setAccountName(accountInfoVO.getName());
            } catch (WuXiHuaJieFeignError e) {
                return e.getWebApiReturnResultModel();
            }
        }

        String id;
        if (Strings.isNullOrEmpty(onBusinessDO.getId())) {
            // 判断是否有交差出差的情况
            if (onBusinessService.validateBeforeInsert(onBusinessDO)) {
                id = onBusinessService.insert(onBusinessDO);
            } else {
                return WebApiReturnResultModel.ofStatus(WebResponseState.CONFLICT_ON_BUSINESS);
            }
        } else {
            onBusinessService.update(onBusinessDO);
            id = onBusinessDO.getId();
        }
        return WebApiReturnResultModel.ofSuccess(id);
    }

    @Override
    @PostMapping("/listOnBusiness")
    @ApiOperation(value = "获取出差记录列表", response = OnBusinessVO.class)
    public WebApiReturnResultModel listOnBusiness(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        // 获取参数
        Integer status = listAskForLeaveRequest.getStatus();
        String nameValue = listAskForLeaveRequest.getNameValue();
        String organizeId = listAskForLeaveRequest.getOrganizeId();

        // 获取分页查询的信息
        PageInfo<OnBusinessDO> onBusinessDOPageInfo = onBusinessService.listPageByOrgIdAndStatusAndName(listAskForLeaveRequest, organizeId, nameValue, status);

        // 将分页信息中的data转成要返回的类型
        List<OnBusinessVO> onBusinessList = onBusinessDOPageInfo.getList().stream().map(m -> {
            OnBusinessVO onBusinessVO = dozerBeanMapper.map(m, OnBusinessVO.class);
            // 设置审核状态的中文描述
            onBusinessVO.setStatusName(ApproveStatusEnum.getByCode(onBusinessVO.getStatus()).getDesc());
            return onBusinessVO;
        }).collect(Collectors.toList());
        // 构造分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(onBusinessDOPageInfo,
                onBusinessList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @ApiOperation("根据账户id查询出差记录")
    @PostMapping("/listOnBusinessByAccountId")
    @Override
    public WebApiReturnResultModel listOnBusinessByAccountId(@RequestBody @Validated ListOnBusinessByAccountIdRequestDTO listOnBusinessByAccountId){
        // 获取分页查询的信息
        PageInfo<OnBusinessDO> onBusinessDOPageInfo = onBusinessService.listPageByAccountIdAndStatus(listOnBusinessByAccountId, listOnBusinessByAccountId.getAccountId(),listOnBusinessByAccountId.getStatus());

        // 将分页信息中的data转成要返回的类型
        List<OnBusinessVO> onBusinessList = onBusinessDOPageInfo.getList().stream().map(m -> {
            OnBusinessVO onBusinessVO = dozerBeanMapper.map(m, OnBusinessVO.class);
            // 设置审核状态的中文描述
            onBusinessVO.setStatusName(ApproveStatusEnum.getByCode(onBusinessVO.getStatus()).getDesc());
            return onBusinessVO;
        }).collect(Collectors.toList());
        // 构造分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(onBusinessDOPageInfo,
                onBusinessList, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @Override
    @ApiOperation("根据id查询出差记录")
    @PostMapping("/onBusinessById")
    public WebApiReturnResultModel onBusinessById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest)
    {
        OnBusinessVO onBusinessVO =dozerBeanMapper.map(onBusinessService.selectById(commonIdRequest.getId()),OnBusinessVO.class);
        onBusinessVO.setStatusName(ApproveStatusEnum.getByCode(onBusinessVO.getStatus()).getDesc());
        return WebApiReturnResultModel.ofSuccess(onBusinessVO);
    }


    @Override
    @PostMapping("/deleteOnBusiness")
    @ApiOperation("删除出差记录")
    public WebApiReturnResultModel deleteOnBusiness(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        onBusinessService.deleteByIdList(commonIdListRequestDTO.getIdList());
        return WebApiReturnResultModel.ofSuccess();
    }

    @PostMapping("/checkOnBusiness")
    @ApiOperation("审核出差")
    @Override
    public WebApiReturnResultModel checkOnBusiness(@RequestBody @Validated CheckOnBusinessRequestDTO checkOnBusinessRequest){
        onBusinessService.check(checkOnBusinessRequest.getType(),checkOnBusinessRequest.getId());
        return WebApiReturnResultModel.ofSuccess();
    }

}
