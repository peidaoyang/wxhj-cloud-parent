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

public class GetObjectAclRequest extends GenericObjectRequest {

    /**
     * Constructs a void Constructor for GetObjectAclRequest.
     */
    public GetObjectAclRequest() {

    }

    /**
     * Constructs a new GetObjectAclRequest to get a specified object acl.
     * @param bucketName
     * @param key
     */
    public GetObjectAclRequest(String bucketName, String key) {
        super(bucketName, key);
    }

    @Override
    public GetObjectAclRequest withRequestCredentials(BceCredentials credentials) {
        this.setRequestCredentials(credentials);
        return this;
    }

    @Override
    public GetObjectAclRequest withBucketName(String bucketName) {
        this.setBucketName(bucketName);
        return this;
    }

    @Override
    public GetObjectAclRequest withKey(String key) {
        this.setKey(key);
        return this;
    }
}