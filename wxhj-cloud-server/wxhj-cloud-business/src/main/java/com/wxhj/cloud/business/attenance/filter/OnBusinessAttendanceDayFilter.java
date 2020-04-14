package com.wxhj.cloud.business.attenance.filter;

import com.wxhj.cloud.business.attenance.helper.AttendanceDayFilterHelper;
import com.wxhj.cloud.business.domain.OnBusinessDO;
import com.wxhj.cloud.business.service.OnBusinessService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.enums.DayWorkTypeEnum;
import com.wxhj.cloud.feignClient.business.dto.AttendanceDoFilterDTO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
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
    public void doFilter(String accountId, Date beginTime, Date endTime) {
        AttendanceDayFilterHelper attendanceDayFilterHelper = getAttendanceDayFilterHelper();

        List<OnBusinessDO> onBusinesses = onBusinessService.listByAccountIdAndStatusLimitTime(accountId,
                ApproveStatusEnum.APPROVE_SUCCESS.getCode(), beginTime, endTime);

        // 解析并过滤出差时间
        onBusinesses.forEach(item -> {
            Date sTime = item.getStartTime();
            Date eTime = item.getEndTime();
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
