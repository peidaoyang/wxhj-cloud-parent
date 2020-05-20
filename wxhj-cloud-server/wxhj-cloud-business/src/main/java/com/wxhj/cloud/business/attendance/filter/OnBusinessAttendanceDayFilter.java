package com.wxhj.cloud.business.attendance.filter;

import com.wxhj.cloud.business.attendance.helper.AttendanceDayFilterHelper;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.business.service.OnBusinessService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.enums.DayWorkTypeEnum;
import com.wxhj.cloud.feignClient.business.dto.AttendanceDoFilterDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.util.Arrays;

import java.util.List;

/**
 * @author daxiong
 * @date 2020-04-10 09:57
 */
@Component
public class OnBusinessAttendanceDayFilter extends AbstractAttendanceDayFilter {

    @Resource
    OnBusinessService onBusinessService;

    @Override
    public void doFilter(String accountId, LocalDateTime beginTime, LocalDateTime endTime) {
        AttendanceDayFilterHelper attendanceDayFilterHelper = getAttendanceDayFilterHelper();

        List<OnBusinessDO> onBusinesses =
                onBusinessService.listByAccountIdAndStatusLimitTime(accountId,
                Arrays.asList(ApproveStatusEnum.APPROVE_SUCCESS.getCode()),
                beginTime, endTime);

        // 解析并过滤出差时间
        onBusinesses.forEach(item -> {
            LocalDateTime sTime = item.getStartTime();
            LocalDateTime eTime = item.getEndTime();
            AttendanceDoFilterDTO attendanceDoFilterDTO = new AttendanceDoFilterDTO(item.getId(),
                    item.getCreateTime(), sTime, eTime, DayWorkTypeEnum.ON_BUSINESS);
            attendanceDayFilterHelper.updateWorkDayStatusByTime(attendanceDoFilterDTO);
        });
    }



    @Override
    void customPriority() {
        setPriority(LOW);
    }
}
