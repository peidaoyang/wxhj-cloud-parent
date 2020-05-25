package com.wxhj.cloud.school.controller.schoolManage;


import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountTypeClient;
import com.wxhj.cloud.feignClient.account.request.AccountByOtherCodeAndTypeRequestDTO;
import com.wxhj.cloud.feignClient.account.vo.AccountByIdAndTypeVO;
import com.wxhj.cloud.feignClient.school.StudentAccountClient;
import com.wxhj.cloud.school.domain.RoomRecDO;
import com.wxhj.cloud.school.service.RoomRecService;
import io.swagger.annotations.Api;

import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;


/**
 * @Author cya
 * @Date 2020/5/21
 * @Version V1.0
 **/
@RestController
@RequestMapping("/studentAccount")
@Api(tags = "人员管理")
public class StudentAccountController implements StudentAccountClient {
//    @Resource
//    AccountClient accountClient;
    @Resource
    AccountTypeClient accountTypeClient;
    @Resource
    RoomRecService roomRecService;

    @ApiOperation("根据账户类型和学号查询账户入住状态")
    @PostMapping("/accountByOtherCodeAndType")
    public WebApiReturnResultModel accountByIdAndType(@RequestBody @Validated AccountByOtherCodeAndTypeRequestDTO accountByOtherCodeAndType) {
        RoomRecDO roomRecDO = roomRecService.selectByOtherCodeAndOrganizeId(accountByOtherCodeAndType.getOtherCode(),accountByOtherCodeAndType.getOrganizeId());
        if(roomRecDO!=null && roomRecDO.getStatus() == 1){
            return WebApiReturnResultModel.ofStatus(WebResponseState.SCHOOL_ROOM_ACCOUNT_EXCEED);
        }
        WebApiReturnResultModel webApiReturnResultModel = accountTypeClient.accountByOtherCodeAndType(accountByOtherCodeAndType);
        AccountByIdAndTypeVO accountByIdAndTypeVO = null;
        try {
            accountByIdAndTypeVO = FeignUtil.formatClass(webApiReturnResultModel, AccountByIdAndTypeVO.class);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess(accountByIdAndTypeVO);
    }
}
