package com.wxhj.cloud.business.config;

import com.wxhj.cloud.business.attendance.filter.AbstractAttendanceDayFilter;
import com.wxhj.cloud.business.attendance.helper.AttendanceDayFilterHelper;
import com.wxhj.cloud.core.statics.SystemStaticClass;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 获取考勤规则filter及其子类，设置到考勤规则helper中
 * @author daxiong
 * @date 2020-04-09 22:31
 */
@Configuration
public class AttendanceDayFilterConfiguration {
    @Autowired
    ApplicationContext applicationContext;
    @Resource
    AttendanceDayFilterHelper attendanceDayFilterHelper;

    @Bean
    public String setToHelper() {
        Map<String, AbstractAttendanceDayFilter> beansOfType = applicationContext.getBeansOfType(AbstractAttendanceDayFilter.class);
        beansOfType.forEach((k, v) -> {
            List<AbstractAttendanceDayFilter> filterList = attendanceDayFilterHelper.getMap().get(v.getPriority());
            filterList = filterList == null ? new ArrayList<>(SystemStaticClass.INIT_CAPACITY) : filterList;
            filterList.add(v);
            attendanceDayFilterHelper.getMap().put(v.getPriority(), filterList);
        });
        return null;
    }
}
