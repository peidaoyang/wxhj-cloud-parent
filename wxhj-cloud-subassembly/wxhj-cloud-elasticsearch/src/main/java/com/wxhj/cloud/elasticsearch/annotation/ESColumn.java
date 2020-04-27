package com.wxhj.cloud.elasticsearch.annotation;

/**
 * @author wxpjf
 * @date 2020/4/27 10:44
 */
public @interface ESColumn {
    ESColumnTypeEnum  columnType();
    boolean index() default false;
}
