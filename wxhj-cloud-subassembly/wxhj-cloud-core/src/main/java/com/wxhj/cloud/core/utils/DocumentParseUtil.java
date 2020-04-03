/**
 * 
 */
package com.wxhj.cloud.core.utils;

import java.io.BufferedReader;
import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSON;

import lombok.extern.slf4j.Slf4j;

/**
 * @ClassName: DocumentParseUtil.java
 * @author: cya
 * @Date: 2019年12月6日 上午9:08:58
 */
@Slf4j
public class DocumentParseUtil {

	public static String moduleJSON(List<String> contentList, List<String> fieldOrder) {
		List<Map<String, String>> jsonList = new ArrayList<Map<String, String>>();
		int size = fieldOrder.size();
		for (String content : contentList) {
			String[] contentArray = content.split(",");
			Map<String, String> contentMap = new HashMap<String, String>();
			for (int i = 0; i < size; i++) {
				contentMap.put(fieldOrder.get(i), contentArray[i]);
			}
			jsonList.add(contentMap);
		}
		return JSON.toJSONString(jsonList);
	}

	public static List<String> byteToContentList(byte[] buffer, String splitStr, String encodeFormat) {
		List<String> contentList = new ArrayList<String>();
		try {
			InputStream bais = new ByteArrayInputStream(buffer);
			BufferedReader reader = new BufferedReader(new InputStreamReader(bais, encodeFormat));
			if (splitStr.equals("\n")) {
				while (true) {
					String str = reader.readLine();
					if (str != null) {
						contentList.add(str);
					} else {
						break;
					}
				}
			} else {
				String str = reader.readLine();
				if (str != null) {
					contentList = Arrays.asList(str.split(splitStr));
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return contentList;
	}

	public static List<String> byteToContentList(byte[] buffer, String encodeFormat) {
		return byteToContentList(buffer, "\n", encodeFormat);
	}

	public static List<String> byteToContentList(byte[] buffer) {
		return byteToContentList(buffer, "\n", "UTF-8");
	}
}
