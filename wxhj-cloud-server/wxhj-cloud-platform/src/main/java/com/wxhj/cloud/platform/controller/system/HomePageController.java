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
import com.wxhj.cloud.feignClient.device.DeviceRecordClient;
import com.wxhj.cloud.feignClient.device.DeviceStateClient;
import com.wxhj.cloud.feignClient.device.response.DeviceStateTotalResponseDTO;
import com.wxhj.cloud.feignClient.device.response.DeviceTypeTotalVO;
import com.wxhj.cloud.feignClient.device.vo.DeviceInWeekVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.platform.domain.EnumManageDO;
import com.wxhj.cloud.platform.service.EnumManageService;
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

@RestController
@RequestMapping("/homePage")
@Api(tags = "主页信息展示")
public class HomePageController {
    @Resource
    DeviceStateClient deviceStateClient;
    @Resource
    AccountClient accountClient;
    @Resource
    EntranceDataClient entranceDataClient;
    @Resource
    AccountConsumeClient accountConsumeClient;
    @Resource
    DeviceRecordClient deviceRecordClient;
    @Resource
    EnumManageService enumManageService;

    @ApiOperation(value = "设备状态统计", response = DeviceStateTotalResponseDTO.class)
    @PostMapping("/deviceStateTotal")
    @LcnTransaction
    public WebApiReturnResultModel deviceStateTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        return deviceStateClient.deviceStateTotal(commonIdRequest);
    }

    @ApiOperation(value = "账户信息统计", response = AccountTotalResponseDTO.class)
    @PostMapping("/accountTotal")
    @LcnTransaction
    public WebApiReturnResultModel accountTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        WebApiReturnResultModel webApiReturnResultModel;
        webApiReturnResultModel = accountClient.accountTotal(commonIdRequest);
        AccountTotalResponseDTO accountTotal = null;
        try {
            accountTotal = FeignUtil.formatClass(webApiReturnResultModel, AccountTotalResponseDTO.class);
            int totayEntranceCount = FeignUtil.formatClass(entranceDataClient.totayEntrance(commonIdRequest), Integer.class);
            accountTotal.setEntranceTotal(totayEntranceCount);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }

        return WebApiReturnResultModel.ofSuccess(accountTotal);
    }

    @ApiOperation(value = "充值/消费 统计", response = TodayConsumeResponseDTO.class)
    @PostMapping("/consumeTotal")
    public WebApiReturnResultModel consumeTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        WebApiReturnResultModel webApiReturnResultModel = accountConsumeClient.todayConsume(commonIdRequest);
        TodayConsumeResponseDTO todayConsumeResponse = null;
        try {
            todayConsumeResponse = FeignUtil.formatClass(webApiReturnResultModel, TodayConsumeResponseDTO.class);
        } catch (WuXiHuaJieFeignError wuXiHuaJieFeignError) {
            return wuXiHuaJieFeignError.getWebApiReturnResultModel();
        }
        return WebApiReturnResultModel.ofSuccess(todayConsumeResponse);
    }

    @ApiOperation(value = "设备类型 统计", response = DeviceTypeTotalVO.class)
    @PostMapping("/deviceTypeTotal")
    public WebApiReturnResultModel deviceTypeTotal(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) throws WuXiHuaJieFeignError {
        WebApiReturnResultModel webApiReturnResultModel = deviceStateClient.deviceTypeTotal(commonIdRequest);
        List<DeviceTypeTotalVO> voList = FeignUtil.formatArrayClass(webApiReturnResultModel, DeviceTypeTotalVO.class);
        List<EnumManageDO> enumList = enumManageService.selectByEnumCode(1);

        List<Integer> deviceTypeList = voList.stream().map(q -> q.getDeviceType()).collect(Collectors.toList());
        List<EnumManageDO> enumListTemp = enumList.stream().filter(item -> !deviceTypeList.contains(item.getEnumType())).collect(Collectors.toList());

        enumList.forEach(q -> {
            voList.forEach(j -> {
                if (q.getEnumType().equals(j.getDeviceType())) {
                    j.setDeviceTypeStr(q.getEnumTypeName());
                }
            });
        });

        enumListTemp.forEach(q -> {
            voList.add(new DeviceTypeTotalVO(commonIdRequest.getId(), 0, q.getEnumType(), q.getEnumTypeName()));
        });
        return WebApiReturnResultModel.ofSuccess(voList);
    }

    @ApiOperation(value = "返回一周内设备数据", response = DeviceInWeekVO.class)
    @PostMapping("/deviceInWeek")
    public WebApiReturnResultModel deviceInWeek(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        return deviceRecordClient.deviceInWeek(commonIdRequest);
    }
}
