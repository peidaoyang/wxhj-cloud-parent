/** 
 * @fileName: HttpResponseHandler.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:23:46 
 */

package com.wxhj.cloud.baidu.http.handler;

import com.wxhj.cloud.baidu.http.BceHttpResponse;
import com.wxhj.cloud.baidu.model.AbstractBceResponse;

/**
 * @className HttpResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午9:23:46   
*/
/** 
 * @className HttpResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午9:23:46 
*/

public interface HttpResponseHandler {

    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception;
}