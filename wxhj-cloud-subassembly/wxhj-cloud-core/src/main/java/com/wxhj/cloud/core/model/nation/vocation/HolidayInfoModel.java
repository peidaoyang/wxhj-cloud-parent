package com.wxhj.cloud.core.model.nation.vocation;

import com.wxhj.cloud.core.utils.DateFormat;
import lombok.Data;

import java.time.LocalDate;

/**
 * @author daxiong
 * @date 2020/5/8 3:56 下午
 */
@Data
public class HolidayInfoModel {
    private Integer status;
    private LocalDate date;

    public void setDate(String date) {
        this.date = DateFormat.parseDate(date);
    }
}
