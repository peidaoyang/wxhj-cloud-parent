package com.wxhj.cloud.core.model.nation.vocation;

import com.wxhj.cloud.core.utils.DateFormat;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

/**
 * @author daxiong
 * @date 2020/5/8 3:54 下午
 */
@Data
public class HolidayModel {
    private String desc;
    private LocalDate festival;
    private List<HolidayInfoModel> list;
    private String name;

    public void setFestival(String festival) {
        this.festival = DateFormat.parseDate(festival);
    }
}
