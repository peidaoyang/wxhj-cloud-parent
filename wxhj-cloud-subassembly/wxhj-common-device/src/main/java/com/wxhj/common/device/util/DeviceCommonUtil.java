package com.wxhj.common.device.util;

import lombok.extern.slf4j.Slf4j;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author daxiong
 * @date 2020/4/30 11:48 上午
 */
@Slf4j
public class DeviceCommonUtil {

    public static final String BACK_DOOR_STR="xhsgxxn";
    /**
     * 对需要验签加签的对象排序,只有sgin参数不参与验签
     *
     * @param obj 对象
     * @return 排序后的String
     */
    @SuppressWarnings({ "rawtypes", "unchecked" })
    public static String putPairsSequenceAndTogether(Object obj) {
        // 将对象转换成Map
        Map info = toMap(obj);
        return createLinkString(paraFilter(info));
    }

    public static Map<Object, Object> toMap(Object bean) {
        Class<? extends Object> clazz = bean.getClass();
        Map<Object, Object> returnMap = new HashMap<Object, Object>(16);
        BeanInfo beanInfo = null;
        try {
            beanInfo = Introspector.getBeanInfo(clazz);
            PropertyDescriptor[] propertyDescriptors = beanInfo.getPropertyDescriptors();
            for (int i = 0; i < propertyDescriptors.length; i++) {
                PropertyDescriptor descriptor = propertyDescriptors[i];
                String propertyName = descriptor.getName();
                if (!propertyName.equals("class")) {
                    Method readMethod = descriptor.getReadMethod();
                    Object result = null;
                    result = readMethod.invoke(bean, new Object[0]);
                    if (null != propertyName) {
                        propertyName = propertyName.toString();
                    }
                    if (null != result) {
                        result = result.toString();
                    }
                    returnMap.put(propertyName, result);
                }
            }
        } catch (IntrospectionException e) {
            log.error("分析类属性失败");
        } catch (IllegalAccessException e) {
            log.error("实例化 JavaBean 失败");
        } catch (IllegalArgumentException e) {
            log.error("映射错误");
        } catch (InvocationTargetException e) {
            log.error("调用属性的 setter 方法失败");
        }
        return returnMap;
    }

    /**
     * 把数组所有元素排序，并按照“参数=参数值”的模式用“&”字符拼接成字符串
     *
     * @param params 需要排序并参与字符拼接的参数组
     * @return 拼接后字符串
     */
    public static String createLinkString(Map<String, String> params) {

        List<String> keys = new ArrayList<String>(params.keySet());
        Collections.sort(keys);

        String prestr = "";

        for (int i = 0; i < keys.size(); i++) {
            String key = keys.get(i);
            String value = params.get(key);
            // 拼接时，不包括最后一个&字符
            if (i == keys.size() - 1) {
                prestr = prestr.concat(key).concat("=").concat(value);
                // prestr = prestr + key + "=" + value;
            } else {
                prestr = prestr.concat(key).concat("=").concat(value).concat("&");
                // prestr = prestr + key + "=" + value + "&";
            }
        }

        return prestr;
    }

    /**
     * 除去数组中的空值和签名参数
     *
     * @param sArray 签名参数组
     * @return 去掉空值与签名参数后的新签名参数组
     */
    public static Map<String, String> paraFilter(Map<String, String> sArray) {

        Map<String, String> result = new HashMap<String, String>(16);

        if (sArray == null || sArray.size() <= 0) {
            return result;
        }

        for (String key : sArray.keySet()) {
            String value = sArray.get(key);
            if (value == null || value.equals("") || key.equalsIgnoreCase("sign")) {
                continue;
            }
            result.put(key, value);
        }

        return result;
    }
}
