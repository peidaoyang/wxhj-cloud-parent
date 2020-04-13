package com.wxhj.cloud.core.utils;

import org.springframework.cglib.beans.BeanMap;

import java.util.HashMap;
import java.util.Map;

public class BeanMapUtil {
    public static Map beanToMap(Object object, boolean isHump) {
        Map<String, Object> map = new HashMap<String, Object>();
        if (object != null) {
            BeanMap beanMap = BeanMap.create(object);
            for (Object key : beanMap.keySet()) {
                String keyStr = key + "";
                if (isHump) {
                    keyStr = HumpUtil.humpToLine(keyStr);
                }

                map.put(HumpUtil.humpToLine(key + ""), beanMap.get(key));
            }
        }
        return map;
    }

    public static <T> T mapToBean(Map<String, ? extends Object> map, Class<T> beanClass, boolean isHump) throws IllegalAccessException, InstantiationException {
        if (isHump) {
            HashMap newMap = new HashMap();
            for (Map.Entry<String, ? extends Object> entryTemp : map.entrySet()
            ) {
                newMap.put(HumpUtil.lineToHump(entryTemp.getKey()), entryTemp.getValue());
            }
            map = newMap;
        }
        T bean = beanClass.newInstance();
        BeanMap beanMap = BeanMap.create(bean);
        beanMap.putAll(map);
        return bean;
    }

}
