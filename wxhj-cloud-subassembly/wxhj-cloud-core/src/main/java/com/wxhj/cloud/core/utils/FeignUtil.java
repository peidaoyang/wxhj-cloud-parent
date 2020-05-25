/** 
 * @fileName: FeignUtil.java  
 * @author: pjf
 * @date: 2020年3月2日 下午3:52:30 
 */

package com.wxhj.cloud.core.utils;

import java.util.List;

import com.wxhj.cloud.core.utils.JSON;
import com.google.common.base.Strings;
import com.wxhj.cloud.core.exception.WuXiHuaJieFeignError;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;

/**
 * @className FeignUtil.java
 * @author pjf
 * @date 2020年3月2日 下午3:52:30   
*/
/**
 * @className FeignUtil.java
 * @author pjf
 * @date 2020年3月2日 下午3:52:30
 */

public class FeignUtil {
	public static <T> T formatClass(WebApiReturnResultModel webApiReturnResultModel, Class<T> tClass)
			throws WuXiHuaJieFeignError {
		String jsonString = checkWebApiReturnResultModel(webApiReturnResultModel);
		if (Strings.isNullOrEmpty(jsonString)) {
			return null;
		}
		return JSON.parseObject(jsonString, tClass);
	}

	private static String checkWebApiReturnResultModel(WebApiReturnResultModel webApiReturnResultModel)
			throws WuXiHuaJieFeignError {
		if (!webApiReturnResultModel.resultSuccess()) {
			throw new WuXiHuaJieFeignError(webApiReturnResultModel);
		}
		String jsonString = JSON.toJSONString(webApiReturnResultModel.getData());
		return jsonString;
	}

	public static <T> List<T> formatArrayClass(WebApiReturnResultModel webApiReturnResultModel, Class<T> tClass)
			throws WuXiHuaJieFeignError {
		String jsonString = checkWebApiReturnResultModel(webApiReturnResultModel);
		if (Strings.isNullOrEmpty(jsonString)) {
			return null;
		}
		return JSON.parseArray(jsonString, tClass);
	}
}
