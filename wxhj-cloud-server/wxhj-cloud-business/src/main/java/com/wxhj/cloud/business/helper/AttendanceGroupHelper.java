package com.wxhj.cloud.business.helper;

import com.google.common.base.Strings;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupDO;
import com.wxhj.cloud.business.domain.CurrentAttendanceGroupRecDO;
import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.business.service.CurrentAttendanceGroupRecService;
import com.wxhj.cloud.business.service.OrganizeYearScheduleRecService;
import com.wxhj.cloud.business.service.OrganizeYearScheduleService;
import com.wxhj.cloud.core.enums.AttendanceGroupTypeEnum;
import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.exception.WuXiHuaJieCommonException;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import com.wxhj.cloud.core.utils.DateFormat;
import lombok.Data;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author daxiong
 * @date 2020/5/19 2:03 下午
 */
@Component
@Data
public class AttendanceGroupHelper {
    @Resource
    OrganizeYearScheduleService organizeYearScheduleService;
    @Resource
    OrganizeYearScheduleRecService organizeYearScheduleRecService;
    @Resource
    CurrentAttendanceGroupRecService currentAttendanceGroupRecService;

    private ThreadLocal<List<OrganizeYearScheduleRecDO>> organizeYearScheduleRecs = new ThreadLocal<>();
    private ThreadLocal<List<CurrentAttendanceGroupRecDO>> currentAttendanceGroupRecs = new ThreadLocal<>();
    private ThreadLocal<Map<String, OrganizeYearScheduleRecDO>> organizeYearScheduleRecMap = new ThreadLocal<>();
    private ThreadLocal<Map<Integer, CurrentAttendanceGroupRecDO>> currentAttendanceGroupRecMap = new ThreadLocal<>();

    /**
     * 根据考勤组id和日期获取当天的考勤规则id，并存到缓存中
     *
     * @param currentAttendanceGroup
     * @param dateStr
     * @return java.lang.String
     * @author daxiong
     * @date 2020/5/19 11:42 上午
     */
    @Cacheable(value = "current_attendance_group_rec", cacheManager = "oneDayCacheManager",
            key = "#currentAttendanceGroup.getId() + ':' + args[1]", unless = "#result eq null")
    public String getCacheAttendanceId(CurrentAttendanceGroupDO currentAttendanceGroup, String dateStr) {
        LocalDate date = DateFormat.parseDate(dateStr);
        // 格式化参数
        formatParam(currentAttendanceGroup);
        Integer groupType = currentAttendanceGroup.getGroupType();
        int serialNumber = 0;
        if (groupType == AttendanceGroupTypeEnum.BY_WEEK.getCode()) {
            serialNumber = date.getDayOfWeek().getValue();
        } else if (groupType == AttendanceGroupTypeEnum.BY_MONTH.getCode()) {
            serialNumber = date.getDayOfMonth();
        } else if (groupType == AttendanceGroupTypeEnum.BY_YEAR.getCode()) {
            serialNumber = organizeYearScheduleService.selectCacheByIdAndDate(currentAttendanceGroup.getOrganizeYearScheduleId(),
                    organizeYearScheduleRecMap.get(), DateFormat.getStringDate(date));
            if (serialNumber == 3) {
                // 法定节假日视为正常休息日
                serialNumber = 1;
            } else if (serialNumber == 2) {
                // 法定调休日视为正常工作日
                serialNumber = 0;
            }
        }
        return currentAttendanceGroupRecMap.get().get(serialNumber).getAttendanceDayId();
    }

    @CacheEvict(value = "current_attendance_group_rec", key = "#currentAttendanceGroup.getId() + ':' + '*'", beforeInvocation = true)
    public void deleteCacheAttendanceId(CurrentAttendanceGroupDO currentAttendanceGroup, String dateStr) {

    }

    /**
     * 格式化list为map，减少循环次数，方便后面读取操作
     *
     * @param
     * @return void
     * @author daxiong
     * @date 2020/5/19 2:44 下午
     */
    private void formatParam(CurrentAttendanceGroupDO currentAttendanceGroup) {
        Integer groupType = currentAttendanceGroup.getGroupType();
        if (groupType == AttendanceGroupTypeEnum.BY_YEAR.getCode()) {
            // 按年考勤
            if (organizeYearScheduleRecs.get() == null && organizeYearScheduleRecMap.get() == null
                    && Strings.isNullOrEmpty(currentAttendanceGroup.getOrganizeYearScheduleId())) {
                throw new WuXiHuaJieCommonException(WebResponseState.PARAMETER_MUST_NOT_NULL);
            } else {
                if (organizeYearScheduleRecMap.get() == null) {
                    if (organizeYearScheduleRecs.get() == null) {
                        organizeYearScheduleRecs.set(organizeYearScheduleRecService.listByRefId(currentAttendanceGroup.getOrganizeYearScheduleId()));
                    }
                    Map<String, OrganizeYearScheduleRecDO> map = new HashMap<>(SystemStaticClass.INIT_CAPACITY);
                    organizeYearScheduleRecs.get().forEach(q -> map.put(DateFormat.getStringDate(q.getDay()), q));
                    organizeYearScheduleRecMap.set(map);
                }
            }
        }
        if (currentAttendanceGroupRecs.get() == null && currentAttendanceGroupRecMap.get() == null
                && Strings.isNullOrEmpty(currentAttendanceGroup.getId())) {
            throw new WuXiHuaJieCommonException(WebResponseState.PARAMETER_MUST_NOT_NULL);
        } else {
            if (currentAttendanceGroupRecMap.get() == null) {
                if (currentAttendanceGroupRecs.get() == null) {
                    currentAttendanceGroupRecs.set(currentAttendanceGroupRecService.selectByAttendanceGroupId(currentAttendanceGroup.getId()));
                }
                Map<Integer, CurrentAttendanceGroupRecDO> collect = currentAttendanceGroupRecs.get().stream().collect(Collectors.toMap(CurrentAttendanceGroupRecDO::getSerialNumber, v -> v));
                currentAttendanceGroupRecMap.set(collect);
            }
        }

    }

    public void setOrganizeYearScheduleRecs(List<OrganizeYearScheduleRecDO> organizeYearScheduleRecs) {
        this.organizeYearScheduleRecs.set(organizeYearScheduleRecs);
    }

    public void setCurrentAttendanceGroupRecs(List<CurrentAttendanceGroupRecDO> currentAttendanceGroupRecs) {
        this.currentAttendanceGroupRecs.set(currentAttendanceGroupRecs);
    }

    public void setOrganizeYearScheduleRecMap(Map<String, OrganizeYearScheduleRecDO> organizeYearScheduleRecMap) {
        this.organizeYearScheduleRecMap.set(organizeYearScheduleRecMap);
    }

    public void setCurrentAttendanceGroupRecMap(Map<Integer, CurrentAttendanceGroupRecDO> currentAttendanceGroupRecMap) {
        this.currentAttendanceGroupRecMap.set(currentAttendanceGroupRecMap);
    }

}
