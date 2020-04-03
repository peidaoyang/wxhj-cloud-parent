/** 
 * @fileName: IdNunmberUtil.java  
 * @author: pjf
 * @date: 2019年11月7日 上午8:47:10 
 */

package com.wxhj.cloud.core.utils;

import java.util.Arrays;

/**
 * @className IdNunmberUtil.java
 * @author pjf
 * @date 2019年11月7日 上午8:47:10   
*/


public class IdNumberUtil {
	public static boolean checkIdNumber(String num) {
		if (null == num)
			return false;
		String reg = "^\\d{15}$|^\\d{17}[0-9Xx]$";
		if (!num.matches(reg)) {
			return false;
		}

		char[] id = {};
		for (int i = 0; i < num.length(); i++) {
			id = Arrays.copyOf(id, id.length + 1);
			id[id.length - 1] = num.charAt(i);
		}
		int sum = 0;
		int w[] = { 7, 9, 10, 5, 8, 4, 2, 1, 6, 3, 7, 9, 10, 5, 8, 4, 2 };
		char[] ch = { '1', '0', 'X', '9', '8', '7', '6', '5', '4', '3', '2' };
		for (int i = 0; i < id.length - 1; i++) {
			sum += (id[i] - '0') * w[i];
		}
		int c = sum % 11;
		char code = ch[c];
		char last = id[id.length - 1];
		last = last == 'x' ? 'X' : last;
		if (last == code) {
			return true;
		}
		return false;
	}
}
