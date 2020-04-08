/** 
 * @fileName: WebErrorExceptionController.java  
 * @author: pjf
 * @date: 2019年10月11日 下午3:33:38 
 */

package com.wxhj.cloud.core.exception;

import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;

/**
 * @className WebErrorExceptionController.java
 * @author pjf
 * @date 2019年10月11日 下午3:33:38
 */

public class WebErrorExceptionController implements ErrorController {
	public static final String ERROR_PATH = "/error";

	@Resource
	private ErrorAttributes errorAttributes;

	@Override
	public String getErrorPath() {
		return ERROR_PATH;
	}
	/**
	 * 非web错误信息处理方法
	 * 
	 * @param request
	 * @return
	 */
	@RequestMapping(ERROR_PATH)
	@ResponseBody
	public WebApiReturnResultModel errorHandler(HttpServletRequest request) {
		ServletWebRequest webRequest = new ServletWebRequest(request);
		//Map<String, Object> errorAttributesMap = this.errorAttributes.getErrorAttributes(webRequest, false);
		
		Map<String, Object> errorAttributesMap = this.errorAttributes.getErrorAttributes(webRequest, true);
		
		int statusCode = getStatus(request);
		if(errorAttributesMap.get("trace") != null && errorAttributesMap.get("trace").toString().contains("DuplicateKeyException")) {
			System.out.println("测试");
			return WebApiReturnResultModel.ofStatus(WebResponseState.DATA_REPEAT);
		}
		
		return WebApiReturnResultModel.ofMessage(statusCode,
				String.valueOf(errorAttributesMap.getOrDefault("message", "error")));
	}
	
	
	private int getStatus(HttpServletRequest request) {
		Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
		if (status != null) {
			return status;
		}

		return WebResponseState.OTHER_ERROR.getCode();
	}
}
