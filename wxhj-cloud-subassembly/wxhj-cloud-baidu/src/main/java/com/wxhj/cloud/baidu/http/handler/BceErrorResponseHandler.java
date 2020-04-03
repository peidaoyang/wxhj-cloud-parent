/** 
 * @fileName: BceErrorResponseHandler.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:23:21 
 */

package com.wxhj.cloud.baidu.http.handler;

import java.io.InputStream;

import org.apache.http.HttpStatus;

import com.wxhj.cloud.baidu.BceErrorResponse;
import com.wxhj.cloud.baidu.BceServiceException;
import com.wxhj.cloud.baidu.BceServiceException.ErrorType;
import com.wxhj.cloud.baidu.http.BceHttpResponse;
import com.wxhj.cloud.baidu.model.AbstractBceResponse;
import com.wxhj.cloud.baidu.util.JsonUtils;

/**
 * @className BceErrorResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午9:23:21   
*/
/** 
 * @className BceErrorResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午9:23:21 
*/

public class BceErrorResponseHandler implements HttpResponseHandler {
    
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (httpResponse.getStatusCode() / 100 == HttpStatus.SC_OK / 100) {
            // not an error
            return false;
        }
        BceServiceException bse = null;
        InputStream content = httpResponse.getContent();
        if (content != null) {
            /*
             * content-length is not set in the error respond message of the media service
             */
//            if (response.getMetadata().getContentLength() > 0) {
//            BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(content));
//            String line;
//            while ((line = bufferedReader.readLine()) != null){
//                System.out.println(line);
//            }
            BceErrorResponse bceErrorResponse = JsonUtils.loadFrom(content, BceErrorResponse.class);
            if (bceErrorResponse.getMessage() != null) {
                bse = new BceServiceException(bceErrorResponse.getMessage());
                bse.setErrorCode(bceErrorResponse.getCode());
                bse.setRequestId(bceErrorResponse.getRequestId());
            }
            //            }
            content.close();
        }
        if (bse == null) {
            bse = new BceServiceException(httpResponse.getStatusText());
            bse.setRequestId(response.getMetadata().getBceRequestId());
        }
        bse.setStatusCode(httpResponse.getStatusCode());
        if (bse.getStatusCode() >= 500) {
            bse.setErrorType(ErrorType.Service);
        } else {
            bse.setErrorType(ErrorType.Client);
        }
        throw bse;
    }

}
