package com.wxhj.cloud.business.controller.attendance;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.attendance.helper.AttendanceDayFilterHelper;
import com.wxhj.cloud.business.domain.AttendanceDayDO;
import com.wxhj.cloud.business.domain.AttendanceDayRecDO;
import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceDayRecDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.business.dto.response.AttendanceDayResponseDTO;
import com.wxhj.cloud.business.service.AttendanceDayRecService;
import com.wxhj.cloud.business.service.AttendanceDayService;
import com.wxhj.cloud.business.service.CurrentAccountAuthorityService;
import com.wxhj.cloud.business.service.CurrentAttendanceDayRecService;
import com.wxhj.cloud.business.service.CurrentAttendanceDayService;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupRecService;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupService;
import com.wxhj.cloud.component.service.AccessedRemotelyService;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import com.wxhj.cloud.core.model.pagination.PageDefResponseModel;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.driud.pagination.PageUtil;
import com.wxhj.cloud.feignClient.business.AttendanceDayClient;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.AttendanceDayAllVO;
import com.wxhj.cloud.feignClient.business.vo.ListAttendanceDayVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;
import com.wxhj.cloud.feignClient.business.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO;
import io.swagger.annotations.ApiOperation;
import com.github.dozermapper.core.Mapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
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
    Mapper dozerBeanMapper;
    @Resource
    AttendanceDayService attendanceDayService;
    @Resource
    AttendanceDayRecService attendanceDayRecService;
    @Resource
    AccessedRemotelyService accessedRemotelyService;

    @Resource
    CurrentAttendanceGroupService currentAttendanceGroupService;
    @Resource
    CurrentAccountAuthorityService currentAccountAuthorityService;
    @Resource
    CurrentAttendanceGroupRecService currentAttendanceGroupRecService;
    @Resource
    CurrentAttendanceDayService currentAttendanceDayService;
    @Resource
    CurrentAttendanceDayRecService currentAttendanceDayRecService;
    @Resource
    AttendanceDayFilterHelper attendanceDayFilterHelper;

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
            id = attendanceDayService.insertCascade(attendanceDay, attendanceDayRecList);
        } else {
            attendanceDayService.updateCascade(attendanceDay, attendanceDayRecList);
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
                .listById(commonIdRequest.getId());
        AttendanceDayResponseDTO attendanceResponse = new AttendanceDayResponseDTO();
        attendanceResponse = dozerBeanMapper.map(attendanceDay, AttendanceDayResponseDTO.class);
        attendanceResponse.setListAttendanceDayRec(attendanceList);
        return WebApiReturnResultModel.ofSuccess(attendanceResponse);
    }

    @Override
    @ApiOperation(value = "根据账户id获取时间段内考勤规则", response = GetAttendanceDaysVO.class)
    @PostMapping("/getAttendanceDays")
    public WebApiReturnResultModel getAttendanceDays(@RequestBody @Validated GetAttendanceDaysDTO getAttendanceDaysDTO) {
        // 获取参数
        String accountId = getAttendanceDaysDTO.getAccountId();
        Date beginTime = getAttendanceDaysDTO.getBeginTime();
        Date endTime = getAttendanceDaysDTO.getEndTime();
        // 计算需要返回多少条数据
        int termDays = DateUtil.getTermDays(beginTime, endTime);
        if (termDays > 62) {
            // 选择天数太多
            return WebApiReturnResultModel.ofStatus(WebResponseState.TOO_MANY_SELECT_DAYS);
        }

        // 根据账户id获取权限组id
        CurrentAccountAuthorityDO currentAccountAuthority = currentAccountAuthorityService.selectByAccountId(accountId);
        if (currentAccountAuthority == null) {
            return WebApiReturnResultModel.ofStatus(WebResponseState.ACCOUNT_NO_ATTENDANCE_GROUP);
        }
        List<GetAttendanceDaysVO> attendanceDaysList = getGetAttendanceDaysVOS(getAttendanceDaysDTO, currentAccountAuthority);

        return WebApiReturnResultModel.ofSuccess(attendanceDaysList);
    }

    /**
     * 获取用户考勤规则
     * @author daxiong
     * @date 2020/4/15 11:11 上午
     * @param getAttendanceDaysDTO
     * @param currentAccountAuthority
     * @return java.util.List<com.wxhj.cloud.feignClient.business.vo.GetAttendanceDaysVO>
     */
    public List<GetAttendanceDaysVO> getGetAttendanceDaysVOS(GetAttendanceDaysDTO getAttendanceDaysDTO, CurrentAccountAuthorityDO currentAccountAuthority) {
        // 获取参数
        String groupId = currentAccountAuthority.getAuthorityGroupId();
        // 根据权限组id获取权限组类型
        CurrentAttendanceGroupDO currentAttendanceGroup = currentAttendanceGroupService.selectById(groupId);
        // 根据权限组获取权限组考勤规则
        List<CurrentAttendanceGroupRecDO> currentAttendanceGroupRecs = currentAttendanceGroupRecService.selectByAttendanceGroupId(currentAttendanceGroup.getId());
        // 根据考勤规则获取当前应用的考勤班次，判断是否上班
        Set<String> attendanceIds = new HashSet<>(SystemStaticClass.INIT_CAPACITY);
        Map<Integer, CurrentAttendanceGroupRecDO> currentAttendanceGroupRecMap = new HashMap<>(currentAttendanceGroupRecs.size());
        currentAttendanceGroupRecs.forEach(item -> {
            attendanceIds.add(item.getAttendanceDayId());
            currentAttendanceGroupRecMap.put(item.getSerialNumber(), item);
        });
        List<CurrentAttendanceDayDO> currentAttendanceDays = currentAttendanceDayService.listByGroupIdAndDayId(groupId, new ArrayList<>(attendanceIds));

        Map<String, CurrentAttendanceDayDO> attendanceDayMap = new HashMap<>(SystemStaticClass.INIT_CAPACITY);
        currentAttendanceDays.forEach(item -> attendanceDayMap.put(item.getDayId(), item));
        // 获取具体的考勤规则
        List<CurrentAttendanceDayRecDO> currentAttendanceDayRecs = currentAttendanceDayRecService.listByGroupIdAndDayIdList(groupId, new ArrayList<>(attendanceIds));
        Map<String, List<CurrentAttendanceDayRecDO>> currentAttendanceDayRecMap = new HashMap<>(SystemStaticClass.INIT_CAPACITY);
        currentAttendanceDayRecs.forEach(item -> {
            List<CurrentAttendanceDayRecDO> list = currentAttendanceDayRecMap.get(item.getDayId());
            list = list == null ? new ArrayList<>(SystemStaticClass.INIT_CAPACITY) : list;
            list.add(item);
            currentAttendanceDayRecMap.put(item.getDayId(), list);
        });

        // 构造返回
        attendanceDayFilterHelper.setCurrentAttendanceDayRecMap(currentAttendanceDayRecMap);
        attendanceDayFilterHelper.setCurrentAttendanceGroupRecMap(currentAttendanceGroupRecMap);
        attendanceDayFilterHelper.setAccountId(getAttendanceDaysDTO.getAccountId());
        attendanceDayFilterHelper.setCurrentAttendanceDayMap(attendanceDayMap);
        attendanceDayFilterHelper.setBeginTime(getAttendanceDaysDTO.getBeginTime());
        attendanceDayFilterHelper.setEndTime(getAttendanceDaysDTO.getEndTime());
        attendanceDayFilterHelper.setCurrentAttendanceGroup(currentAttendanceGroup);
        attendanceDayFilterHelper.setCurrentAccountAuthorityDO(currentAccountAuthority);
        return attendanceDayFilterHelper.initAndFilter();
    }

}
