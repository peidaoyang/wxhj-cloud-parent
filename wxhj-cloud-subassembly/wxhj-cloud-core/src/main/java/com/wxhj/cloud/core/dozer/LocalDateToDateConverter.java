package com.wxhj.cloud.core.dozer;

import com.github.dozermapper.core.DozerConverter;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

/**
 * @author wxpjf
 * @date 2020/5/12 14:10
 */
public class LocalDateToDateConverter extends DozerConverter<LocalDate, Date> {

    public LocalDateToDateConverter() {
        super(LocalDate.class, Date.class);
    }

    @Override
    public Date convertTo(LocalDate localDate, Date date) {
        Date convertToDate = Date.from(localDate.atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
        return convertToDate;
    }

    @Override
    public LocalDate convertFrom(Date date, LocalDate localDate) {
        return LocalDateTime.ofInstant(date.toInstant(), ZoneId.systemDefault()).toLocalDate();
    }
}
