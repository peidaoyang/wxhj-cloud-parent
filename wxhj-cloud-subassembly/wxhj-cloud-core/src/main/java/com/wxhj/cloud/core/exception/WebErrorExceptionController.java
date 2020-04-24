/**
 * @fileName: WebErrorExceptionController.java
 * @author: pjf
 * @date: 2019年10月11日 下午3:33:38
 */

package com.wxhj.cloud.core.exception;

import com.wxhj.cloud.core.enums.WebResponseState;
import com.wxhj.cloud.core.model.WebApiReturnResultModel;
import org.apache.http.HttpStatus;
import org.springframework.boot.web.servlet.error.ErrorAttributes;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.context.request.ServletWebRequest;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * @author pjf
 * @className WebErrorExceptionController.java
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
    public WebApiReturnResultModel errorHandler(HttpServletRequest request,
                                                HttpServletResponse response) {

        response.setStatus(HttpStatus.SC_OK);
        ServletWebRequest webRequest = new ServletWebRequest(request);
        Map<String, Object> errorAttributesMap = this.errorAttributes.getErrorAttributes(webRequest, true);
        if (errorAttributesMap.get("trace") != null &&
                errorAttributesMap.get("trace").toString().contains("DuplicateKeyException")) {
            //System.out.println("测试");

            return WebApiReturnResultModel.ofStatus(WebResponseState.DATA_REPEAT);
        }

        int statusCode = getStatus(request);

        return WebApiReturnResultModel.ofMessage(statusCode,
                String.valueOf(errorAttributesMap.getOrDefault("message", "error")));
    }
//
//    @RequestMapping(ERROR_PATH)
//    @ResponseBody
//    @ExceptionHandler(value = DuplicateKeyException.class)
//    public WebApiReturnResultModel duplicateKeyErrorHandler() {
//        return WebApiReturnResultModel.ofStatus(WebResponseState.DATA_REPEAT);
//    }
//

    private int getStatus(HttpServletRequest request) {
        Integer status = (Integer) request.getAttribute("javax.servlet.error.status_code");
        if (status != null) {
            return status;
        }

        return WebResponseState.OTHER_ERROR.getCode();
    }
}
