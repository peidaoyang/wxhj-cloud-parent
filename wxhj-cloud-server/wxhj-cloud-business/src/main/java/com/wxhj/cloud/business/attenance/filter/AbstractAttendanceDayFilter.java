package com.wxhj.cloud.business.attenance.filter;

import com.wxhj.cloud.business.attenance.helper.AttendanceDayFilterHelper;
import com.wxhj.cloud.feignClient.vo.GetAttendanceDaysVO;
import lombok.Data;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * 对用户考勤规则进行筛选过滤
 * @author daxiong
 * @date 2020-04-10 08:50
 */
@Data
public abstract class AbstractAttendanceDayFilter {
    public static final Integer LOW = 5;
    public static final Integer LOW_MIDDLE = 4;
    public static final Integer MIDDLE = 3;
    public static final Integer MIDDLE_HIGH = 2;
    public static final Integer HIGH = 1;

    @Resource
    private AttendanceDayFilterHelper attendanceDayFilterHelper;
    private int priority;

    /**
     * 过滤用户考勤规则
     * @param accountId     账户id
     * @param beginTime     开始时间
     * @param endTime       结束时间
     */
    public abstract void doFilter(String accountId, Date beginTime, Date endTime);

    /**
     * 自定义优先级，默认为MIDDLE
     * @author daxiong
     * @date 2020-04-10 10:09
     * @return void
     */
    void customPriority() {
        setPriority(MIDDLE);
    }

}
