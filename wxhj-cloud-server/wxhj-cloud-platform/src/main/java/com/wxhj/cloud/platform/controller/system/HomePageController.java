package com.wxhj.cloud.platform.controller.system;

import com.codingapi.txlcn.tc.annotation.LcnTransaction;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.utils.FeignUtil;
import com.wxhj.cloud.feignClient.account.AccountClient;
import com.wxhj.cloud.feignClient.account.AccountConsumeClient;
import com.wxhj.cloud.feignClient.account.response.AccountTotalResponseDTO;
import com.wxhj.cloud.feignClient.account.response.TodayConsumeResponseDTO;
import com.wxhj.cloud.feignClient.business.EntranceDataClient;
import com.wxhj.cloud.feignClient.device.DeviceStateClient;
import com.wxhj.cloud.feignClient.device.response.DeviceStateTotalResponseDTO;
import com.wxhj.cloud.feignClient.device.response.DeviceTypeTotalResponseDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/homePage")
@Api(tags="主页信息展示")
public class HomePageController {
    @Resource
    DeviceStateClient deviceStateClient;
    @Resource
    AccountClient accountClient;
    @Resource
    EntranceDataClient entranceDataClient;
    @Resource
    AccountConsumeClient accountConsumeClient;

    @ApiOperation(value = "设备状态统计",response = DeviceStateTotalResponseDTO.class)
    @PostMapping("/deviceStateTotal")
    @LcnTransaction
    public WebApiReturnResultModel deviceStateTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        return deviceStateClient.deviceStateTotal(commonIdRequest);
    }

    @ApiOperation(value = "账户信息统计",response = AccountTotalResponseDTO.class)
    @PostMapping("/accountTotal")
    @LcnTransaction
    public WebApiReturnResultModel accountTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        WebApiReturnResultModel webApiReturnResultModel;
        webApiReturnResultModel = accountClient.accountTotal(commonIdRequest);
        AccountTotalResponseDTO accountTotal = null;
        try {
            accountTotal = FeignUtil.formatClass(webApiReturnResultModel, AccountTotalResponseDTO.class);
            int totayEntranceCount = FeignUtil.formatClass(entranceDataClient.totayEntrance(commonIdRequest),Integer.class);
            accountTotal.setEntranceTotal(totayEntranceCount);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }

        return WebApiReturnResultModel.ofSuccess(accountTotal);
    }

    @ApiOperation(value = "充值/消费 统计",response = TodayConsumeResponseDTO.class)
    @PostMapping("/consumeTotal")
    public WebApiReturnResultModel consumeTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        WebApiReturnResultModel webApiReturnResultModel = accountConsumeClient.todayConsume(commonIdRequest);
        TodayConsumeResponseDTO todayConsumeResponse = null;
        try {
            todayConsumeResponse =FeignUtil.formatClass(webApiReturnResultModel,TodayConsumeResponseDTO.class);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess(todayConsumeResponse);
    }

    @ApiOperation(value = "设备类型 统计",response = DeviceTypeTotalResponseDTO.class)
    @PostMapping("/deviceTypeTotal")
    public WebApiReturnResultModel deviceTypeTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest){
        return deviceStateClient.deviceTypeTotal(commonIdRequest);
    }


}
