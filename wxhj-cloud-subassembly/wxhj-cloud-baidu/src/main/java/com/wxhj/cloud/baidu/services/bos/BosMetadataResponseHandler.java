/*
 * Copyright 2014 Baidu, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package com.wxhj.cloud.baidu.services.bos;

import com.wxhj.cloud.baidu.http.BceHttpResponse;
import com.wxhj.cloud.baidu.http.Headers;
import com.wxhj.cloud.baidu.http.handler.HttpResponseHandler;
import com.wxhj.cloud.baidu.model.AbstractBceResponse;
import com.wxhj.cloud.baidu.services.bos.model.BosResponseMetadata;

public class BosMetadataResponseHandler implements HttpResponseHandler {

    @Override
    public boolean handle(BceHttpResponse httpResponse, AbstractBceResponse response) throws Exception {
        if (response.getMetadata() instanceof BosResponseMetadata) {
            BosResponseMetadata metadata = (BosResponseMetadata) response.getMetadata();
            metadata.setBosDebugId(httpResponse.getHeader(Headers.BCE_DEBUG_ID));
            if (httpResponse.getHeader(Headers.BCE_NEXT_APPEND_OFFSET) != null) {
                metadata.setNextAppendOffset(
                        Long.parseLong(httpResponse.getHeader(Headers.BCE_NEXT_APPEND_OFFSET)));
            }
            if (httpResponse.getHeader(Headers.LOCATION) != null) {
                metadata.setLocation(httpResponse.getHeader(Headers.LOCATION));
            }

        }
        return false;
    }

}
