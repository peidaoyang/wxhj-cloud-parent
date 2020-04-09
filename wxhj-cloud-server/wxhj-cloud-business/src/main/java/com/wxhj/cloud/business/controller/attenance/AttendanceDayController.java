package com.wxhj.cloud.business.controller.attenance;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.attenance.AttendanceDayRecBO;
import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.business.dto.response.AttendanceDayResponseDTO;
import com.wxhj.cloud.business.service.AttendanceDayRecService;
import com.wxhj.cloud.business.service.AttendanceDayService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.AttendanceDayClient;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.AttendanceDayAllVO;
import com.wxhj.cloud.feignClient.business.vo.ListAttendanceDayVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import io.swagger.annotations.ApiOperation;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.stream.Collectors;

//import com.wxhj.cloud.feignClient.business.request.DeleteAttendanceDayRequestDTO;
//import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;

/**
 * @className AttendanceDayController.java
 * @admin jwl
 * @date 2019年12月13日 下午1:24:31
 */
@RestController
@RequestMapping("/attendanceDay")
public class AttendanceDayController implements AttendanceDayClient {
    @Resource
    DozerBeanMapper dozerBeanMapper;
    @Resource
    AttendanceDayService attendanceDayService;
    @Resource
    AttendanceDayRecService attendanceDayRecService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;

    @ApiOperation("编辑班次")
    @PostMapping("/submitAttendanceDay")
    @Override
    public WebApiReturnResultModel submitAttendanceDay(
            @Validated @RequestBody SubmitAttendanceDayRequestDTO submitAttendanceDay) {
        AttendanceDayDO attendanceDay = dozerBeanMapper.map(submitAttendanceDay, AttendanceDayDO.class);
        List<AttendanceDayRecDO> attendanceDayRecList =
                submitAttendanceDay.getAttendanceDayRec().stream().map(q -> dozerBeanMapper.map(q, AttendanceDayRecDO.class)).collect(Collectors.toList());

        String id;
        if (Strings.isNullOrEmpty(attendanceDay.getId())) {
            id = attendanceDayService.insertCascade(attendanceDay,attendanceDayRecList);
        } else {
            attendanceDayService.updateCascade(attendanceDay,attendanceDayRecList);
            id = attendanceDay.getId();
        }
        return WebApiReturnResultModel.ofSuccess(id);
    }

    @SuppressWarnings("unchecked")
    @ApiOperation("获取班次")
    @PostMapping("/listAttendanceDay")
    @Override
    public WebApiReturnResultModel listAttendanceDay(
            @Validated @RequestBody CommonListPageRequestDTO commonListPageRequest) {
        PageInfo<AttendanceDayDO> attendanceDayList = attendanceDayService.listByFullName(commonListPageRequest,
                commonListPageRequest.getNameValue(), commonListPageRequest.getOrganizeId());
        List<ListAttendanceDayVO> listAttendanceDayList = attendanceDayList.getList().stream()
                .map(q -> dozerBeanMapper.map(q, ListAttendanceDayVO.class)).collect(Collectors.toList());
        try {
            listAttendanceDayList = (List<ListAttendanceDayVO>) accessedRemotelyService
                    .accessedOrganizeList(listAttendanceDayList);
        } catch (WuXiHuaJieFeignError e) {
            // TODO Auto-generated catch block
            return e.getWebApiReturnResultModel();
        }
        PageDefResponseModel pageDefResponseModel = (PageDefResponseModel) PageUtil
                .initPageResponseModel(attendanceDayList, listAttendanceDayList, new PageDefResponseModel());

        return WebApiReturnResultModel.ofSuccess(pageDefResponseModel);
    }

    @PostMapping("/listAllAttendDay")
    @ApiOperation("按组织编号获取班次")
    @Override
    public WebApiReturnResultModel listAllAttendDay(@RequestBody CommonOrganizeRequestDTO commonOrganizeRequest) {
        List<AttendanceDayDO> listAttendanceDay = attendanceDayService
                .listByOrganizeId(commonOrganizeRequest.getOrganizeId());
        List<AttendanceDayAllVO> listAll = listAttendanceDay.stream()
                .map(q -> new AttendanceDayAllVO(q.getId(), q.getFullName(), q.getTimeDescribe()))
                .collect(Collectors.toList());
        return WebApiReturnResultModel.ofSuccess(listAll);
    }

    @ApiOperation("删除选中班次")
    @PostMapping("/deleteAllAttendanceDay")
    @Override
    public WebApiReturnResultModel deleteAllAttendanceDay(
            @Validated @RequestBody CommonIdListRequestDTO commonIdListRequest) {
        attendanceDayService.delete(commonIdListRequest.getIdList());
        return WebApiReturnResultModel.ofSuccess();
    }

    @ApiOperation("根据编号获取班次")
    @PostMapping("/selectAttendanceDayById")
    @Override
    public WebApiReturnResultModel selectAttendanceDayById(@Validated @RequestBody CommonIdRequestDTO commonIdRequest) {
        AttendanceDayDO attendanceDay = attendanceDayService.selectById(commonIdRequest.getId());
        List<AttendanceDayRecDO> attendanceList = attendanceDayRecService
                .listAttendanceDayRecByAttendanceId(commonIdRequest.getId());
        AttendanceDayResponseDTO attendanceResponse = new AttendanceDayResponseDTO();
        attendanceResponse = dozerBeanMapper.map(attendanceDay, AttendanceDayResponseDTO.class);
        attendanceResponse.setListAttendanceDayRec(attendanceList);
        return WebApiReturnResultModel.ofSuccess(attendanceResponse);
    }
}
