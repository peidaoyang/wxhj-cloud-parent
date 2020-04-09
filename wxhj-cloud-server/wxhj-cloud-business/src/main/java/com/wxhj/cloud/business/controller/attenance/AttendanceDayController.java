package com.wxhj.cloud.business.controller.attenance;

import java.text.Format;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import com.wxhj.cloud.business.domain.CurrentAccountAuthorityDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.business.service.CurrentAccountAuthorityService;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupRecService;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupService;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.dto.GetAttendanceDaysDTO;
import com.wxhj.cloud.feignClient.vo.GetAttendanceDaysVO;
import org.dozer.DozerBeanMapper;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.github.pagehelper.PageInfo;
import com.google.common.base.Strings;
import com.wxhj.cloud.business.attenance.AttendanceDayRecBO;
import com.wxhj.cloud.business.bo.AttendanceDayBO;
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
//import com.wxhj.cloud.feignClient.business.request.DeleteAttendanceDayRequestDTO;
//import com.wxhj.cloud.feignClient.business.request.ListAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.request.SubmitAttendanceDayRequestDTO;
import com.wxhj.cloud.feignClient.business.vo.AttendanceDayAllVO;
import com.wxhj.cloud.feignClient.business.vo.ListAttendanceDayVO;
import com.wxhj.cloud.feignClient.dto.CommonIdListRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonIdRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonListPageRequestDTO;
import com.wxhj.cloud.feignClient.dto.CommonOrganizeRequestDTO;

import io.swagger.annotations.ApiOperation;

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

    @Resource
    CurrentAccountAuthorityService currentAccountAuthorityService;
    @Resource
    CurrentAttendanceGroupService currentAttendanceGroupService;
    @Resource
    CurrentAttendanceGroupRecService currentAttendanceGroupRecService;

    @ApiOperation("编辑班次")
    @PostMapping("/submitAttendanceDay")
    @Override
    public WebApiReturnResultModel submitAttendanceDay(
            @Validated @RequestBody SubmitAttendanceDayRequestDTO submitAttendanceDay) {
        AttendanceDayBO attendanceDay = dozerBeanMapper.map(submitAttendanceDay, AttendanceDayBO.class);
        List<AttendanceDayRecBO> listAttendanceDayRec = new ArrayList<AttendanceDayRecBO>();
        submitAttendanceDay.getAttendanceDayRec().forEach(q -> {
            AttendanceDayRecBO attendanceDayRec = dozerBeanMapper.map(q, AttendanceDayRecBO.class);
            listAttendanceDayRec.add(attendanceDayRec);
        });
        attendanceDay.setAttendanceDayRec(listAttendanceDayRec);
        String id;
        if (Strings.isNullOrEmpty(attendanceDay.getId())) {
            id = attendanceDayService.insertCascade(attendanceDay);
        } else {
            attendanceDayService.updateCascade(attendanceDay);
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

    @ApiOperation(value = "根据账户id获取时间段内考勤规则")
    @PostMapping("/getAttendanceDays")
    public WebApiReturnResultModel getAttendanceDays(@RequestBody @Validated GetAttendanceDaysDTO getAttendanceDaysDTO) {
        // 根据账户id获取权限组id
        CurrentAccountAuthorityDO currentAccountAuthority = currentAccountAuthorityService.selectByAccountId(getAttendanceDaysDTO.getAccountId());
        // 根据权限组id获取权限组类型
        CurrentAttendanceGroupDO currentAttendanceGroup = currentAttendanceGroupService.selectById(currentAccountAuthority.getAuthorityGroupId());
        Integer groupType = currentAttendanceGroup.getGroupType();
        // 根据权限组获取权限组考勤规则
        List<CurrentAttendanceGroupRecDO> currentAttendanceGroupRecs = currentAttendanceGroupRecService.selectByAttendanceGroupId(currentAttendanceGroup.getId());
        // 根据考勤规则获取考勤班次，判断是否上班
        Set<String> attendanceIds = new HashSet<>(SystemStaticClass.INIT_CAPACITY);
        currentAttendanceGroupRecs.forEach(item -> attendanceIds.add(item.getAttendanceDayId()));
        List<AttendanceDayDO> attendanceDays = attendanceDayService.listById(attendanceIds);
        Map<String, AttendanceDayDO> attendanceDayMap = new HashMap<>(SystemStaticClass.INIT_CAPACITY);
        attendanceDays.forEach(item -> attendanceDayMap.put(item.getId(), item));

        // 计算需要返回多少条数据
        Date beginTime = getAttendanceDaysDTO.getBeginTime();
        Date endTime = getAttendanceDaysDTO.getEndTime();
        int termDays = getTermDays(beginTime, endTime);
        if (termDays > 60) {
            // 选择天数太多
        }
        if (groupType == 0) {
            DateUtil.getDateNumber(beginTime, Calendar.DAY_OF_MONTH);
        } else {

        }
        // 构造返回VO
        List<GetAttendanceDaysVO> attendanceDaysList = new ArrayList<>(termDays);


        // 增加请假和出差状态，根据账户id和时间限制
        return null;
    }

    public static void main(String[] args) throws Exception {        int dateNumber = DateUtil.getDateNumber(new Date(), Calendar.MONTH);
        System.out.println(dateNumber);
//        growDate(new Date(), 3);
        growDate("2020-04-13", 2);
    }

    private static int getTermDays(Date d1, Date d2) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            d1 = sdf.parse(sdf.format(d1));
            d2 = sdf.parse(sdf.format(d2));
        } catch (ParseException e) {
            e.printStackTrace();
        }

        long days = (d2.getTime() - d1.getTime()) / (24 * 3600 * 1000);

        return (int) days;
    }

    public static Date growDate(String dateStr) {
        return growDate(dateStr, 1);
    }
    public static Date growDate(String dateStr, int growth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date date = sdf.parse(dateStr);
            return growDate(date, growth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return null;
    }
    public static Date growDate(Date date) {
        return growDate(date, 1);
    }
    public static Date growDate(Date date, int growth) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar c = Calendar.getInstance();
        c.setTime(date);
        c.add(Calendar.DAY_OF_MONTH, growth);

        date = c.getTime();
        System.out.println("Date结束日期+growth " + sdf.format(date));
        return date;
    }

}
