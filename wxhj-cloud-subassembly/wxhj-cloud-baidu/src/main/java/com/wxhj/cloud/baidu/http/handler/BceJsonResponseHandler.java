/** 
 * @fileName: BceJsonResponseHandler.java  
 * @author: pjf
 * @date: 2020年2月28日 上午10:33:06 
 */

package com.wxhj.cloud.baidu.http.handler;

import java.io.InputStream;

import com.wxhj.cloud.baidu.http.BceHttpResponse;
import com.wxhj.cloud.baidu.model.AbstractBceResponse;
import com.wxhj.cloud.baidu.util.JsonUtils;

/**
 * @className BceJsonResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午10:33:06   
*/
/** 
 * @className BceJsonResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午10:33:06 
*/


/**
 * HTTP body json response handler for Baidu BCE responses.
 */
public class BceJsonResponseHandler implements HttpResponseHandler {
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        InputStream content = httpResponse.getContent();
        if (content != null) {
            if (response.getMetadata().getContentLength() > 0
                    || "chunked".equalsIgnoreCase(response.getMetadata().getTransferEncoding())) {
                JsonUtils.load(content, response);
            }
            content.close();
        }
        return true;
    }
}