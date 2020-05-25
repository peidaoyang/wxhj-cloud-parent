package com.wxhj.cloud.business.mapper;

import com.wxhj.cloud.business.domain.OrganizeYearScheduleRecDO;
import com.wxhj.cloud.driud.common.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
 * @author daxiong
 * @date 2020-04-07 13:59
 */
public interface OrganizeYearScheduleRecMapper extends BaseMapper<OrganizeYearScheduleRecDO> {
    @Select("SELECT status FROM organize_year_schedule_rec WHERE organize_year_schedule_id = #{organizeYearScheduleId}" +
            "GROUP BY status")
    List<Integer> organizeYearScheduleMapper(String organizeYearScheduleId);
}
