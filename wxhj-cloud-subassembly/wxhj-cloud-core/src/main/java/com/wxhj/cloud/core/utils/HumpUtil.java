/** 
 * @fileName: HumpUtil.java  
 * @author: pjf
 * @date: 2019年12月3日 上午11:57:38 
 */

package com.wxhj.cloud.core.utils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * @className HumpUtil.java
 * @author pjf
 * @date 2019年12月3日 上午11:57:38   
*/
/**
 * @className HumpUtil.java
 * @author pjf
 * @date 2019年12月3日 上午11:57:38
 */

public class HumpUtil {
	private static Pattern humpPattern = Pattern.compile("[A-Z]");
	private static Pattern linePattern = Pattern.compile("_(\\w)");

	/*
	 * 下划线转驼峰
	 */
	public static String lineToHump(String str) {
		str = str.toLowerCase();
		Matcher matcher = linePattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, matcher.group(1).toUpperCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}

	/*
	 * 驼峰转下划线
	 */
	public static String humpToLine(String str) {
		Matcher matcher = humpPattern.matcher(str);
		StringBuffer sb = new StringBuffer();
		while (matcher.find()) {
			matcher.appendReplacement(sb, "_" + matcher.group(0).toLowerCase());
		}
		matcher.appendTail(sb);
		return sb.toString();
	}
}
