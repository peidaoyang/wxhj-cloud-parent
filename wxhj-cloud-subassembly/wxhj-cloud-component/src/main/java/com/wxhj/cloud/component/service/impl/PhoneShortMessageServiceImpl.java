/** 
 * @fileName: PhoneShortMessageServiceImpl.java  
 * @author: pjf
 * @date: 2020年2月17日 下午1:04:24 
 */

package com.wxhj.cloud.component.service.impl;

import java.util.Map;

import org.springframework.stereotype.Service;

import com.wxhj.cloud.baidu.auth.DefaultBceCredentials;
import com.wxhj.cloud.baidu.services.sms.SmsClient;
import com.wxhj.cloud.baidu.services.sms.SmsClientConfiguration;
import com.wxhj.cloud.baidu.services.sms.model.SendMessageV2Request;
import com.wxhj.cloud.baidu.services.sms.model.SendMessageV2Response;
import com.wxhj.cloud.component.service.PhoneShortMessageService;

/**
 * @className PhoneShortMessageServiceImpl.java
 * @author pjf
 * @date 2020年2月17日 下午1:04:24   
*/
/**
 * @className PhoneShortMessageServiceImpl.java
 * @author pjf
 * @date 2020年2月17日 下午1:04:24
 */
@Service
public class PhoneShortMessageServiceImpl implements PhoneShortMessageService {
	// 发送网址
	private static final String SP_CODE = "http://sms.bj.baidubce.com";
	// 发送账号安全认证的Access Key ID
	private static final String ACCESS_KEY_ID = "bb742bddd47649438db028a61d369670";
	// 发送账号安全认证的Secret Access Key
	private static final String SECRET_ACCESS_KY = "5d616e0e384f465bb0127c965eeaa022";
	private static final String INVOKE_ID = "S2VxGnCG-rb8f-TjTy"; // 发送使用签名的调用ID
	private static final String TEMPLATE_CODE = "smsTpl:e7476122a1c24e37b3b0de19d04ae900"; // 本次发送使用的模板Code

	@Override
	public boolean sendMessage(String phoneNumber, Map<String, String> messageMap) {
		SmsClientConfiguration config = new SmsClientConfiguration();
		// 实例化发送客户端
		config.setCredentials(new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KY));
		config.setEndpoint(SP_CODE);
		// 若模板内容为：您的验证码是${code},在${time}分钟内输入有效
		SmsClient smsClient = new SmsClient(config);
		// 实例化请求对象
		SendMessageV2Request request = new SendMessageV2Request();
		request.withInvokeId(INVOKE_ID).withPhoneNumber(phoneNumber).withTemplateCode(TEMPLATE_CODE)
				.withContentVar(messageMap);
		// 解析请求响应
		SendMessageV2Response response = smsClient.sendMessage(request);
		if (response != null && response.isSuccess()) {
			return true;
		} else {
			return false;
		}
		// return true;
	}

}
