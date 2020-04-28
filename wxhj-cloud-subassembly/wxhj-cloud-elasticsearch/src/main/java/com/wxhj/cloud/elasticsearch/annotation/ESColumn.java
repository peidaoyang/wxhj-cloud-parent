package com.wxhj.cloud.elasticsearch.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @author wxpjf
 * @date 2020/4/27 10:44
 */
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
@Inherited
public @interface ESColumn {
    ESColumnTypeEnum  columnType() default ESColumnTypeEnum.TEXT;
    boolean index() default true;
}
