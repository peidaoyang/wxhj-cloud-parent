/** 
 * @fileName: PhoneShortMessageService.java  
 * @author: pjf
 * @date: 2020年2月17日 上午11:41:01 
 */

package com.wxhj.cloud.component.service;

import java.util.Map;

/**
 * @className PhoneShortMessageService.java
 * @author pjf
 * @date 2020年2月17日 上午11:41:01
 */

public interface PhoneShortMessageService {
	boolean sendMessage(String phoneNumber, Map<String, String> messageMap);
}
