/** 
 * @fileName: AttendanceDayMapper.java  
 * @author: pjf
 * @date: 2019年12月12日 上午10:57:29 
 */

package com.wxhj.cloud.business.mapper;

import com.wxhj.cloud.business.domain.AttendanceSummaryDO;
import com.wxhj.cloud.driud.common.BaseMapper;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Param;

/**
 * @className AttendanceDayMapper.java
 * @author pjf
 * @date 2019年12月12日 上午10:57:29   
*/
public interface AttendanceSummaryMapper extends BaseMapper<AttendanceSummaryDO> {

    /**
     * 通过日期主键删除attendance_summary表中的记录
     * @author daxiong
     * @date 2020/4/20 3:36 下午
     * @param datetime  日期的字符串表示
     * @return void
     */
    @Delete("DELETE FROM attendance_summary WHERE datetime = STR_TO_DATE(#{datetime}, '%Y-%m-%d')")
    void deleteByDate(@Param("datetime") String datetime);
}
