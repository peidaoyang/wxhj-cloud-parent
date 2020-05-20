package com.wxhj.cloud.device.controller;

import com.github.dozermapper.core.Mapper;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.device.config.DeviceServiceConfig;
import com.wxhj.cloud.device.service.ViewDeviceRecordService;
import com.wxhj.cloud.feignClient.device.DeviceRecordClient;
import com.wxhj.cloud.feignClient.device.vo.DeviceInWeekVO;
import com.wxhj.cloud.feignClient.device.vo.ViewDeviceRecordVO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Api(tags = "设备录入信息控制器")
@RestController
@RequestMapping("/deviceRecord")
public class DeviceRecordController implements DeviceRecordClient {
    @Resource
    ViewDeviceRecordService viewDeviceRecordService;
    @Resource
    Mapper dozerBeanMapper;
    @Resource
    DeviceServiceConfig deviceServiceConfig;

    @PostMapping("/deviceInWeek")
    @ApiOperation("返回一周内设备数据")
    @Override
    public WebApiReturnResultModel deviceInWeek(@RequestBody @Validated CommonIdRequestDTO commonIdRequest) {
        List<ViewDeviceRecordVO> listVO = viewDeviceRecordService.list(commonIdRequest.getId()).stream().map(q -> dozerBeanMapper.map(q, ViewDeviceRecordVO.class)).collect(Collectors.toList());

        //获取设备类型map
        Map<Integer, String> recordTopicMap = deviceServiceConfig.getRecordTopicMap();
        List<Integer> typeList = new ArrayList<>();
        recordTopicMap.forEach((key, value) -> {
            typeList.add(key);
        });
        //默认一周
        List<DeviceInWeekVO> deviceResponseList = new ArrayList<>();
        for (int i = 0; i > -7; i--) {
            LocalDate dateTemp = LocalDate.now().plusDays(i);
            //DateUtil.growDateIgnoreHMS(new Date(),i);
            List<ViewDeviceRecordVO> listVOTemp = listVO.stream().filter(q ->
                    //DateUtil.getStringDate(q.getReceivedDate(),"yyyy-MM-dd").equals(DateUtil.getStringDate(dateTemp,"yyyy-MM-dd"))
                    q.getReceivedDate().equals(dateTemp)
            ).collect(Collectors.toList());
            listVOTemp.forEach(q -> q.setRecordTypeStr(recordTopicMap.get(1)));
            if (listVOTemp.size() < typeList.size()) {
                //如果当天的记录数量小于map的大小，那么不在列表里设备类型的记录数量就默认为0
                List<Integer> typeListTemp = typeList.stream().filter(item -> !listVOTemp.contains(item)).collect(Collectors.toList());
                typeListTemp.forEach(j -> {
                    listVOTemp.add(new ViewDeviceRecordVO(commonIdRequest.getId(), j, recordTopicMap.get(j), 0, dateTemp));
                });
            }
            deviceResponseList.add(new DeviceInWeekVO(dateTemp, listVOTemp));
        }

        return WebApiReturnResultModel.ofSuccess(deviceResponseList);
    }
}
