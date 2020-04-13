package com.wxhj.cloud.business.attenance.filter;

import com.wxhj.cloud.business.attenance.helper.AttendanceDayFilterHelper;
import com.wxhj.cloud.business.domain.AskForLeaveDO;
import com.wxhj.cloud.business.service.AskForLeaveService;
import com.wxhj.cloud.core.enums.ApproveStatusEnum;
import com.wxhj.cloud.core.enums.DayWorkTypeEnum;
import com.wxhj.cloud.core.utils.DateUtil;
import com.wxhj.cloud.feignClient.dto.DurationDTO;
import com.wxhj.cloud.feignClient.vo.GetAttendanceDaysVO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * @author daxiong
 * @date 2020-04-10 09:57
 */
@Component
public class AskForLeaveAttendanceDayFilter extends AbstractAttendanceDayFilter {

    @Resource
    AskForLeaveService askForLeaveService;

    @Override
    public void doFilter(String accountId, Date beginTime, Date endTime) {
        AttendanceDayFilterHelper attendanceDayFilterHelper = getAttendanceDayFilterHelper();

        List<AskForLeaveDO> askForLeaves = askForLeaveService.listByAccountIdAndStatusLimitTime(accountId,
                ApproveStatusEnum.APPROVE_SUCCESS.getCode(), beginTime, endTime);

        // 解析并过滤请假时间
        askForLeaves.forEach(item -> {
            Date sTime = item.getStartTime();
            Date eTime = item.getEndTime();

            attendanceDayFilterHelper.updateWorkDayStatusByTime(sTime, eTime, DayWorkTypeEnum.ASK_FOR_LEAVE);
        });
    }



    @Override
    void customPriority() {
        setPriority(LOW);
    }
}
