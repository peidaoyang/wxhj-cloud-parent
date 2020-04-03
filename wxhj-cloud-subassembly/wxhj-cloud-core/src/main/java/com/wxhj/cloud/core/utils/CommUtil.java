/** 
 * @fileName: CommUtil.java  
 * @author: pjf
 * @date: 2019年10月30日 下午4:52:26 
 */

package com.wxhj.cloud.core.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import lombok.extern.slf4j.Slf4j;

/**
 * @className CommUtil.java
 * @author pjf
 * @date 2019年10月30日 下午4:52:26
 */
@Slf4j
public class CommUtil {
	/**
	 * byte数组转 HexString
	 * 
	 * @param src byte数组
	 * @return HexString字符串
	 */
	public static String bytesToHexString(byte[] src) {
		StringBuilder stringBuilder = new StringBuilder("");
		if (src == null || src.length <= 0) {
			return null;
		}
		for (int i = 0; i < src.length; i++) {
			int v = src[i] & 0xFF;
			String hv = Integer.toHexString(v);
			if (hv.length() < 2) {
				stringBuilder.append(0);
			}
			stringBuilder.append(hv);
		}
		return stringBuilder.toString();
	}

	/**
	 * hexString 转btye[]
	 * 
	 * @param hexString
	 * @return btye[]
	 */

	public static byte[] hexStringToBytes(String hexString) {
		if (hexString == null || hexString.equals("")) {
			return null;
		}
		hexString = hexString.toUpperCase();
		int length = hexString.length() / 2;
		char[] hexChars = hexString.toCharArray();
		byte[] d = new byte[length];
		for (int i = 0; i < length; i++) {
			int pos = i * 2;
			d[i] = (byte) (charToByte(hexChars[pos]) << 4 | charToByte(hexChars[pos + 1]));

		}
		return d;
	}

	private static byte charToByte(char c) {
		return (byte) "0123456789ABCDEF".indexOf(c);
	}

	/*
	 * 字符串 左补0
	 */
	public static String padLeftStr(String yourStr, int size) {
		if (yourStr == null) {
			yourStr = "";
		}
		int strLen = yourStr.length();
		if (strLen < size) {
			while (strLen < size) {
				StringBuffer sb = new StringBuffer();
				sb.append("0").append(yourStr);// 左补0
				// sb.append(str).append("0");//右补0
				yourStr = sb.toString();
				strLen = yourStr.length();
			}
		}

		return yourStr;
	}

	/*
	 * 将list<String> 转成逗号分隔的字符串
	 */
	public static String listToString(List<String> strList) {
		return String.join(",", strList);
	}

	public static Date dayDate(Date date) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		String s = sdf.format(date);
		Date dateReturn = null;
		try {
			dateReturn = sdf.parse(s);
		} catch (ParseException e) {
			log.error(e.getMessage());
		}
		return dateReturn;
	}

	public static byte[] readFileBytes(InputStream is) {
//		byte[] data = null;
//		try {
//			if (is.available() == 0) {// 严谨起见,一定要加上这个判断,不要返回data[]长度为0的数组指针
//				return data;
//			}
//			data = new byte[is.available()];
//			is.read(data);
//			is.close();
//			return data;
//		} catch (IOException e) {
//			log.error("readFileBytes, err", e);
//			return data;
//		}
		
		try {
			ByteArrayOutputStream baos = new ByteArrayOutputStream();
			byte[] buffer = new byte[1024];
			int len = 0;
			while ((len = is.read(buffer)) != -1) {
				baos.write(buffer, 0, len);
			}
			byte[] b = baos.toByteArray();
			is.close();
			baos.close();
			return b;
		} catch (IOException e) {
			log.error("readFileBytes, err", e);
			return null;
		}

	}

}
