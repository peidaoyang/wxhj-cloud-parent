package com.wxhj.cloud.core.statics;

import com.google.common.base.CaseFormat;
import com.google.common.base.Converter;

/**
 * @author wxpjf
 * @date 2020/4/15 17:54
 */
public class CaseFormatStaticClass {
    /**
     * 驼峰转下划线
     */
    public static Converter<String, String> CAMEL_TO_UNDERSCORE = CaseFormat.LOWER_CAMEL.converterTo(CaseFormat.LOWER_UNDERSCORE);

    /**
     * 下划线转驼峰
     */
    public static Converter<String, String> UNDERSCORE_TO_CAMEL = CaseFormat.LOWER_UNDERSCORE.converterTo(CaseFormat.LOWER_CAMEL);
}
