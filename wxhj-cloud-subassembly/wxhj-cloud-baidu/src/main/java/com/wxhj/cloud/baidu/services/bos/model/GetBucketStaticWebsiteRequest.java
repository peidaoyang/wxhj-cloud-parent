/*
 * Copyright 2014-2019 Baidu, Inc.
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
package com.wxhj.cloud.baidu.services.bos.model;

import com.wxhj.cloud.baidu.auth.BceCredentials;

/**
 *  Request for get Bucket StaticWebsite.
 */
public class GetBucketStaticWebsiteRequest extends GenericBucketRequest {

    /**
     * Constructs a void Constructor for GetBucketStaticWebsiteRequest.
     */
    public GetBucketStaticWebsiteRequest() {

    }

    /**
     * Constructs a new GetBucketStaticWebsiteRequest to get a specified Bucket Lifecycle.
     * @param bucketName
     */
    public GetBucketStaticWebsiteRequest(String bucketName) {
        super(bucketName);
    }

    @Override
    public GetBucketStaticWebsiteRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetBucketStaticWebsiteRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }
}
