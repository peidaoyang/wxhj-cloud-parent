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
package com.wxhj.cloud.baidu.services.bos.model;

import com.wxhj.cloud.baidu.auth.BceCredentials;

/**
 * Request object containing all the options for requesting a bucket's Access Control List (ACL).
 */
public class GetBucketAclRequest extends GenericBucketRequest {

    /**
     * Constructs a new GetBucketAclRequest object, ready to retrieve the ACL
     * for the specified bucket when executed.
     *
     * @param bucketName The name of the bucket whose ACL will be retrieved by this
     *                   request when executed.
     */
    public GetBucketAclRequest(String bucketName) {
        super(bucketName);
    }

    public GetBucketAclRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    public GetBucketAclRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }
}
