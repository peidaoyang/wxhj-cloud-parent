package com.wxhj.cloud.business.controller;

import com.github.dozermapper.core.Mapper;
import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.business.service.AskForLeaveService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.enums.AskForLeaveTypeEnum;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.vo.AccountInfoVO;
import com.wxhj.cloud.feignClient.business.AskForLeaveClient;
import com.wxhj.cloud.feignClient.business.dto.AskForLeaveDTO;
import com.wxhj.cloud.feignClient.business.dto.ListAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.business.request.CheckAskForLeaveRequestDTO;
import com.wxhj.cloud.feignClient.business.request.ListAskForLeaveByAccountIdRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.AskForLeaveVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.Api;
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
 * 请假controller
 *
 * @author daxiong
 * @date 2020-04-07 14:29
 */
@RestController
@RequestMapping("/askForLeave")
@Api(tags = "请假管理")
public class AskForLeaveController implements AskForLeaveClient {
    @Resource
    AccountClient accountClient;
    @Resource
    AskForLeaveService askForLeaveService;
    @Resource
    Mapper dozerBeanMapper;

    @Override
    @PostMapping("/submitAskForLeave")
    @ApiOperation("编辑请假记录")
    public WebApiReturnResultModel submitAskForLeave(@RequestBody @Validated AskForLeaveDTO askForLeave) {
        AskForLeaveDO askForLeaveDO = dozerBeanMapper.map(askForLeave, AskForLeaveDO.class);
        if(Strings.isNullOrEmpty(askForLeaveDO.getAccountName())){
            //app端无accountName，需要从数据库查询到accountName
            WebApiReturnResultModel webApiReturnResultModel = accountClient.accountOne(new CommonIdRequestDTO(askForLeaveDO.getAccountId()));
            try {
                AccountInfoVO accountInfoVO = FeignUtil.formatClass(webApiReturnResultModel,AccountInfoVO.class);
                askForLeaveDO.setAccountName(accountInfoVO.getName());
            } catch (WuXiHuaJieFeignError e) {
                return e.getWebApiReturnResultModel();
            }
        }
        String id;
        if (Strings.isNullOrEmpty(askForLeaveDO.getId())) {
            // 判断是否有交差请假的情况
            if (askForLeaveService.validateBeforeInsert(askForLeaveDO)) {
                id = askForLeaveService.insert(askForLeaveDO);
            } else {
                return WebApiReturnResultModel.ofStatus(WebResponseState.CONFLICT_ASK_FOR_LEAVE);
            }
        } else {
            askForLeaveService.update(askForLeaveDO);
            id = askForLeaveDO.getId();
        }
        return WebApiReturnResultModel.ofSuccess(id);
    }

    @Override
    @PostMapping("/listAskForLeave")
    @ApiOperation(value = "获取请假记录列表", response = AskForLeaveVO.class)
    public WebApiReturnResultModel listAskForLeave(@RequestBody @Validated ListAskForLeaveRequestDTO listAskForLeaveRequest) {
        // 获取参数
        Integer status = listAskForLeaveRequest.getStatus();
        String nameValue = listAskForLeaveRequest.getNameValue();
        String organizeId = listAskForLeaveRequest.getOrganizeId();

        // 获取分页查询的信息
        PageInfo<AskForLeaveDO> askForLeaveDOPageInfo = askForLeaveService.listPageByOrgIdAndStatusAndName(listAskForLeaveRequest, organizeId, nameValue, status);

        // 将分页信息中的data转成要返回的类型
        List<AskForLeaveVO> askForLeaves = askForLeaveDOPageInfo.getList().stream().map(m -> {
            AskForLeaveVO askForLeaveVO = dozerBeanMapper.map(m, AskForLeaveVO.class);
            // 设置请假类型和审核状态的中文描述
            askForLeaveVO.setTypeName(AskForLeaveTypeEnum.getByCode(askForLeaveVO.getType()).getDesc());
            askForLeaveVO.setStatusName(ApproveStatusEnum.getByCode(askForLeaveVO.getStatus()).getDesc());
            return askForLeaveVO;
        }).collect(Collectors.toList());
        // 构造分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(askForLeaveDOPageInfo,
                askForLeaves, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @Override
    @PostMapping("/listAskForLeaveByAccountId")
    @ApiOperation(value = "获取请假记录列表", response = AskForLeaveVO.class)
    public WebApiReturnResultModel listAskForLeaveByAccountId(@RequestBody @Validated ListAskForLeaveByAccountIdRequestDTO listAskForLeaveByAccountId) {
        // 获取分页查询的信息
        PageInfo<AskForLeaveDO> askForLeaveDOPageInfo = askForLeaveService.listPageByAccountIdAndStatus(listAskForLeaveByAccountId,listAskForLeaveByAccountId.getAccountId(),listAskForLeaveByAccountId.getStatus());

        // 将分页信息中的data转成要返回的类型
        List<AskForLeaveVO> askForLeaves = askForLeaveDOPageInfo.getList().stream().map(m -> {
            AskForLeaveVO askForLeaveVO = dozerBeanMapper.map(m, AskForLeaveVO.class);
            // 设置请假类型和审核状态的中文描述
            askForLeaveVO.setTypeName(AskForLeaveTypeEnum.getByCode(askForLeaveVO.getType()).getDesc());
            askForLeaveVO.setStatusName(ApproveStatusEnum.getByCode(askForLeaveVO.getStatus()).getDesc());
            return askForLeaveVO;
        }).collect(Collectors.toList());
        // 构造分页信息返回实体
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil.initPageResponseModel(askForLeaveDOPageInfo,
                askForLeaves, new PageDefResponseModel());
        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @Override
    @ApiOperation("根据id查询请求记录")
    @PostMapping("/askForLeaveById")
    public WebApiReturnResultModel askForLeaveById(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        AskForLeaveVO askForLeaveVO = dozerBeanMapper.map(askForLeaveService.selectById(commonIdRequest.getId()), AskForLeaveVO.class);
        askForLeaveVO.setTypeName(AskForLeaveTypeEnum.getByCode(askForLeaveVO.getType()).getDesc());
        askForLeaveVO.setStatusName(ApproveStatusEnum.getByCode(askForLeaveVO.getStatus()).getDesc());
        return WebApiReturnResultModel.ofSuccess(askForLeaveVO);
    }


    @Override
    @PostMapping("/deleteAskForLeave")
    @ApiOperation("删除请假记录")
    public WebApiReturnResultModel deleteAskForLeave(@RequestBody @Validated CommonIdListRequestDTO commonIdListRequestDTO) {
        askForLeaveService.deleteByIdList(commonIdListRequestDTO.getIdList());
        return WebApiReturnResultModel.ofSuccess();
    }

    @Override
    @PostMapping("/checkAskForLeave")
    @ApiOperation("请假审核")
    public WebApiReturnResultModel checkAskForLeave(@RequestBody @Validated CheckAskForLeaveRequestDTO checkAskForLeaveRequest){
        askForLeaveService.check(checkAskForLeaveRequest.getId(),checkAskForLeaveRequest.getType());
        return WebApiReturnResultModel.ofSuccess();
    }
}
