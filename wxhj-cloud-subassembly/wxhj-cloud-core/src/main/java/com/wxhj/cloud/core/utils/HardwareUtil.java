/** 
 * @fileName: HardwareUtil.java  
 * @author: pjf
 * @date: 2019年10月28日 下午3:42:55 
 */

package com.wxhj.cloud.core.utils;

import java.net.Inet6Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.util.Enumeration;

import lombok.extern.slf4j.Slf4j;

/**
 * @className HardwareUtil.java
 * @author pjf
 * @date 2019年10月28日 下午3:42:55   
*/
/**
 * @className HardwareUtil.java
 * @author pjf
 * @date 2019年10月28日 下午3:42:55
 */
@Slf4j
public class HardwareUtil {
	public static String getMac() {
		String mac = "";
		try {
			Enumeration<NetworkInterface> enumeration = NetworkInterface.getNetworkInterfaces();
			while (enumeration.hasMoreElements()) {
				StringBuffer stringBuffer = new StringBuffer();
				NetworkInterface networkInterface = enumeration.nextElement();
				if (networkInterface != null) {
					byte[] bytes = networkInterface.getHardwareAddress();
					if (bytes != null) {
						for (int i = 0; i < bytes.length; i++) {
							if (i != 0) {
								stringBuffer.append("-");
							}
							// 字节转换为整数
							int tmp = bytes[i] & 0xff; 
							String str = Integer.toHexString(tmp);
							if (str.length() == 1) {
								stringBuffer.append("0" + str);
							} else {
								stringBuffer.append(str);
							}
						}
						mac = stringBuffer.toString().toUpperCase();

					}
				}
			}
		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return mac;
	}
}
