package com.wxhj.cloud.elasticsearch.annotation;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

/**
 * @author wxpjf
 * @date 2020/4/27 10:45
 */
@AllArgsConstructor
@NoArgsConstructor

public enum ESColumnTypeEnum {
    //
    TEXT("text"),
    KEYWORD("keyword"),
    LONG("long"), 
    INTEGER("integer"),
    SHORT("short"),
    BYTE("byte"),
    DOUBLE("double"),
    FLOAT("float"),
    HALF_FLOAT("half_float"),
    SCALED_FLOAT("scaled_float"),
    DATE("date"),
    BOOLEAN("boolean"),
    BINARY("binary"),
    INTEGER_RANGE("integer_range"),
    FLOAT_RANGE("float_range"),
    LONG_RANGE("long_range"),
    DOUBLE_RANGE("double_range"),
    DATE_RANGE("date_range"),
    IP_RANGE("ip_range"),
    OBJECT("object"),
    NESTED("nested"),
    GEO_POINT("geo_point"),
    GEO_SHAPE("geo_shape"),
    IP("ip"),
    ;
    private String columnType;

    public String getColumnType() {
        return columnType;
    }
}
