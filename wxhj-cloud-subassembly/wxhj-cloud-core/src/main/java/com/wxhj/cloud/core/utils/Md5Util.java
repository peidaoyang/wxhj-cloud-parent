/** 
 * @fileName: Md5Util.java  
 * @author: pjf
 * @date: 2019年10月11日 下午5:10:21 
 */

package com.wxhj.cloud.core.utils;

import java.security.MessageDigest;

import lombok.extern.slf4j.Slf4j;

/**
 * @className Md5Util.java
 * @author pjf
 * @date 2019年10月11日 下午5:10:21   
*/

@Slf4j
public class Md5Util {
	/** 16进制的字符数组 */
	private final static String[] HEX_DIGITS = { "0", "1", "2", "3", "4", "5", "6", "7", "8", "9", "a", "b", "c", "d",
			"e", "f" };

	/**
	 * 
	 * 
	 * @param source    需要加密的原字符串
	 * @param encoding  指定编码类型
	 * @param uppercase 是否转为大写字符串
	 * @return 加密后的字符串
	 */
	public static String md5Encode(String source, String encoding, boolean uppercase) {
		String result = null;
		try {

			result = source;
			// 获得MD5摘要对象
			MessageDigest messageDigest = MessageDigest.getInstance("MD5");
			// 使用指定的字节数组更新摘要信息
			messageDigest.update(result.getBytes(encoding));
			// messageDigest.digest()获得16位长度
			result = byteArrayToHexString(messageDigest.digest());

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return uppercase ? result.toUpperCase() : result;
	}

	/**
	 * 转换字节数组为16进制字符串
	 * 
	 * @param bytes 字节数组
	 * @return 16进制字符串
	 */
	private static String byteArrayToHexString(byte[] bytes) {
		StringBuilder stringBuilder = new StringBuilder();
		for (byte tem : bytes) {
			stringBuilder.append(byteToHexString(tem));
		}
		return stringBuilder.toString();
	}

	/**
	 * 转换byte到16进制
	 * 
	 * @param b 要转换的byte
	 * @return 16进制对应的字符
	 */
	private static String byteToHexString(byte b) {
		int n = b;
		if (n < 0) {
			n = 256 + n;
		}
		int d1 = n / 16;
		int d2 = n % 16;
		return HEX_DIGITS[d1] + HEX_DIGITS[d2];
	}

	/**
	 * 对字符串md5加密(大写+数字)
	 *
	 * @param s 传入要加密的字符串
	 * @return MD5加密后的字符串
	 */

	public static String md5(String s) {
		char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

		try {
			byte[] btInput = s.getBytes();
			// 获得MD5摘要算法的 MessageDigest 对象
			MessageDigest mdInst = MessageDigest.getInstance("MD5");
			// 使用指定的字节更新摘要
			mdInst.update(btInput);
			// 获得密文
			byte[] md = mdInst.digest();
			// 把密文转换成十六进制的字符串形式
			int j = md.length;
			char str[] = new char[j * 2];
			int k = 0;
			for (int i = 0; i < j; i++) {
				byte byte0 = md[i];
				str[k++] = hexDigits[byte0 >>> 4 & 0xf];
				str[k++] = hexDigits[byte0 & 0xf];
			}
			return new String(str);
		} catch (Exception e) {
			log.error(e.getMessage());
			return null;
		}
	}
}
