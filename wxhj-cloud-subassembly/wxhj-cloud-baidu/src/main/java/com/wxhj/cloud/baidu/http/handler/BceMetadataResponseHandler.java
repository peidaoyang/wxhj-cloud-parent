/** 
 * @fileName: BceMetadataResponseHandler.java  
 * @author: pjf
 * @date: 2020年2月28日 上午9:34:20 
 */

package com.wxhj.cloud.baidu.http.handler;

import com.google.common.base.CharMatcher;
import com.wxhj.cloud.baidu.BceResponseMetadata;
import com.wxhj.cloud.baidu.http.BceHttpResponse;
import com.wxhj.cloud.baidu.http.Headers;
import com.wxhj.cloud.baidu.model.AbstractBceResponse;

/**
 * @className BceMetadataResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午9:34:20   
*/
/** 
 * @className BceMetadataResponseHandler.java
 * @author pjf
 * @date 2020年2月28日 上午9:34:20 
*/

public class BceMetadataResponseHandler implements HttpResponseHandler {
    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        BceResponseMetadata metadata = response.getMetadata();
        metadata.setBceRequestId(httpResponse.getHeader(Headers.BCE_REQUEST_ID));
        metadata.setBceContentSha256(httpResponse.getHeader(Headers.BCE_CONTENT_SHA256));
        metadata.setContentDisposition(httpResponse.getHeader(Headers.CONTENT_DISPOSITION));
        metadata.setContentEncoding(httpResponse.getHeader(Headers.CONTENT_ENCODING));
        metadata.setContentLength(httpResponse.getHeaderAsLong(Headers.CONTENT_LENGTH));
        metadata.setContentMd5(httpResponse.getHeader(Headers.CONTENT_MD5));
        metadata.setContentRange(httpResponse.getHeader(Headers.CONTENT_RANGE));
        metadata.setContentType(httpResponse.getHeader(Headers.CONTENT_TYPE));
        metadata.setDate(httpResponse.getHeaderAsRfc822Date(Headers.DATE));
        metadata.setTransferEncoding(httpResponse.getHeader(Headers.TRANSFER_ENCODING));
        String eTag = httpResponse.getHeader(Headers.ETAG);
        if (eTag != null) {
            metadata.setETag(CharMatcher.is('"').trimFrom(eTag));
        }
        metadata.setExpires(httpResponse.getHeaderAsRfc822Date(Headers.EXPIRES));
        metadata.setLastModified(httpResponse.getHeaderAsRfc822Date(Headers.LAST_MODIFIED));
        metadata.setServer(httpResponse.getHeader(Headers.SERVER));
        return false;
    }
}
